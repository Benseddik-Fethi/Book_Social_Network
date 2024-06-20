package com.benseddik.book.book;

import com.benseddik.book.common.PageResponse;
import com.benseddik.book.exception.OperationNotPermittedException;
import com.benseddik.book.feign.PicsurSuccessResponse;
import com.benseddik.book.file.IPicsurApiService;
import com.benseddik.book.history.BookTransactionHistory;
import com.benseddik.book.history.IBookTransactionHistoryMapper;
import com.benseddik.book.history.IBookTransactionHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.benseddik.book.book.BookSpecification.withOwnerUuid;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final IBookTransactionHistoryMapper iBookTransactionHistoryMapper;

    private final IBookRepository bookRepository;
    private final IBookTransactionHistoryRepository bookTransactionHistoryRepository;
    private final IPicsurApiService picsurApiService;
    private final IBookMapper bookMapper;

    public SavedBookResponse save(BookRequest request, Authentication authentication) {
        Book book = bookMapper.toEntity(request);
        book = bookRepository.save(book);
        log.info("Book saved: {}", book.getUuid());
        return new SavedBookResponse(String.valueOf(book.getUuid()));
    }

    public SavedBookResponse update(UUID bookUuid, BookRequest request, Authentication authentication) {

        Book book = bookRepository.findByUuid(bookUuid)
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + bookUuid.toString()));
        if (!Objects.equals(book.getCreatedBy(), authentication.getName())) {
            throw new OperationNotPermittedException("You cannot update book");
        }
        bookMapper.partialUpdate(request, book);
        book = bookRepository.save(book);
        log.info("Book updated: {}", book.getUuid());
        return new SavedBookResponse(String.valueOf(book.getUuid()));
    }

    public BookResponse findBookByUuid(String uuid) {
        return bookRepository.findByUuid(UUID.fromString(uuid))
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }


    public PageResponse<BookResponse> findAllBooks(int page, int size, Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, authentication.getName());
        List<BookResponse> bookResponses = books.map(bookMapper::toBookResponse).getContent();
        return new PageResponse<>(bookResponses, books.getNumber(), books.getSize(), books.getTotalElements(),
                books.getTotalPages(), books.isFirst(), books.isLast());
    }

    public PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAll(withOwnerUuid(authentication.getName()), pageable);
        List<BookResponse> bookResponses = books.map(bookMapper::toBookResponse).getContent();
        return new PageResponse<>(bookResponses, books.getNumber(), books.getSize(), books.getTotalElements(),
                books.getTotalPages(), books.isFirst(), books.isLast());
    }

    public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page, int size, Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookTransactionHistory> allBorrowedBooks =
                bookTransactionHistoryRepository.findAllBorrowedBooks(pageable, authentication.getName());
        List<BorrowedBookResponse> bookResponses =
                allBorrowedBooks.map(iBookTransactionHistoryMapper::toDto).getContent();
        return new PageResponse<>(bookResponses, allBorrowedBooks.getNumber(), allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(), allBorrowedBooks.isFirst(), allBorrowedBooks.isLast());
    }

    public PageResponse<BorrowedBookResponse> findAllReturnedBooks(int page, int size, Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookTransactionHistory> allBorrowedBooks =
                bookTransactionHistoryRepository.findAllReturnedBooks(pageable, authentication.getName());
        List<BorrowedBookResponse> bookResponses =
                allBorrowedBooks.map(iBookTransactionHistoryMapper::toDto).getContent();
        return new PageResponse<>(bookResponses, allBorrowedBooks.getNumber(), allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(), allBorrowedBooks.isFirst(), allBorrowedBooks.isLast());
    }

    public UUID updateShareableStatus(UUID uuid, Authentication authentication) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + uuid.toString()));
        if (!Objects.equals(book.getCreatedBy(), authentication.getName())) {
            throw new OperationNotPermittedException("You cannot update book shareable status");
        }
        book.setShareable(!book.isShareable());
        bookRepository.save(book);
        return book.getUuid();
    }

    public UUID updateArchivedStatus(UUID uuid, Authentication authentication) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + uuid.toString()));
        if (!Objects.equals(book.getCreatedBy(), authentication.getName())) {
            throw new OperationNotPermittedException("You cannot update book archived status");
        }
        book.setArchived(!book.isArchived());
        bookRepository.save(book);
        return book.getUuid();
    }

    public UUID borrowBook(UUID uuid, Authentication authentication) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + uuid.toString()));
        if (!book.isShareable() || book.isArchived()) {
            throw new OperationNotPermittedException("You cannot borrow this book");
        }

        if (Objects.equals(book.getCreatedBy(), authentication.getName())) {
            throw new OperationNotPermittedException("You cannot borrow your own book");
        }
        final boolean isAlreadyBorrowed =
                bookTransactionHistoryRepository.isAlreadyBorrowedByUser(authentication.getName(), uuid);
        if (isAlreadyBorrowed) {
            throw new OperationNotPermittedException("You already borrowed this book");
        }
        BookTransactionHistory bookTransactionHistory = BookTransactionHistory.builder()
                .book(book)
                .userId(authentication.getName())
                .returned(false)
                .returnApproved(false)
                .build();
        return bookTransactionHistoryRepository.save(bookTransactionHistory).getUuid();
    }

    public UUID returnBorrowedBook(UUID bookUuid, Authentication authentication) {
        Book book = bookRepository.findByUuid(bookUuid)
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + bookUuid.toString()));
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot return this book");
        }
        if (Objects.equals(book.getCreatedBy(), authentication.getName())) {
            throw new OperationNotPermittedException("You cannot return or borrow your own book");
        }
        BookTransactionHistory bookTransactionHistory = bookTransactionHistoryRepository
                .findByBookUuidAndUserUuid(authentication.getName(), bookUuid)
                .orElseThrow(() -> new OperationNotPermittedException("You did not borrow this book"));
        bookTransactionHistory.setReturned(true);
        return bookTransactionHistoryRepository.save(bookTransactionHistory).getUuid();
    }

    public UUID approveReturnBorrowedBook(UUID uuid, Authentication authentication) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + uuid.toString()));
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot return this book");
        }
        if (!Objects.equals(book.getCreatedBy(), authentication.getName())) {
            throw new OperationNotPermittedException("You cannot return a book that you did not borrow");
        }
        BookTransactionHistory bookTransactionHistory = bookTransactionHistoryRepository
                .findByBookUuidAndOwnerUuid(uuid, authentication.getName())
                .orElseThrow(() -> new OperationNotPermittedException(
                        "The book is not returned. You cannot approve its return"));
        bookTransactionHistory.setReturnApproved(true);
        return bookTransactionHistoryRepository.save(bookTransactionHistory).getUuid();
    }

    public void uploadBookCoverPicture(UUID uuid, MultipartFile file, Authentication authentication) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Book not found : " + uuid.toString()));
        if (!Objects.equals(book.getCreatedBy(), authentication.getName())) {
            throw new OperationNotPermittedException("You cannot update book cover picture");
        }
        if (file.isEmpty()) {
            book.setBookCover("851dfd89-6ef2-4e9c-8c52-f0783bd1edeb");
        } else {
            PicsurSuccessResponse picsurSuccessResponse = picsurApiService.uploadImage(file).getBody();
            if (picsurSuccessResponse == null) {
                throw new OperationNotPermittedException("Failed to upload book cover picture");
            }
            book.setBookCover(picsurSuccessResponse.getData().getId());
        }
        bookRepository.save(book);
    }
}
