package com.benseddik.book.book;

import com.benseddik.book.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Book", description = "Book API")
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<String> saveBook(@RequestBody @Valid BookRequest request, Authentication authentication) {
        log.info("Book saved: {}", request);
        return ResponseEntity.ok(bookService.save(request, authentication));
    }

    @GetMapping("{book-uuid}")
    public ResponseEntity<BookResponse> getBook(
            @PathVariable("book-uuid") String uuid) {
        log.info("Get book by uuid: {}", uuid);
        return ResponseEntity.ok(bookService.findBookByUuid(uuid));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.findAllBooks(page, size, authentication));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, authentication));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.findAllBorrowedBooks(page, size, authentication));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication authentication) {
        return ResponseEntity.ok(bookService.findAllReturnedBooks(page, size, authentication));
    }

    @PatchMapping("/shareable/{book-uuid}")
    public ResponseEntity<UUID> updateShareableStatus(
            @PathVariable("book-uuid") String uuid,
            Authentication authentication) {
        log.info("Update shareable status for book: {}", uuid);
        return ResponseEntity.ok(bookService.updateShareableStatus(UUID.fromString(uuid), authentication));
    }

    @PatchMapping("/archived/{book-uuid}")
    public ResponseEntity<UUID> updateArchivedStatus(
            @PathVariable("book-uuid") String uuid,
            Authentication authentication) {
        log.info("Update archived status for book: {}", uuid);
        return ResponseEntity.ok(bookService.updateArchivedStatus(UUID.fromString(uuid), authentication));
    }

    @PostMapping("/borrow/{book-uuid}")
    public ResponseEntity<UUID> borrowBook(
            @PathVariable("book-uuid") String uuid,
            Authentication authentication) {
        log.info("Borrow book: {}", uuid);
        return ResponseEntity.ok(bookService.borrowBook(UUID.fromString(uuid), authentication));
    }

    @PatchMapping("/borrow/return/{book-uuid}")
    public ResponseEntity<UUID> returnBorrowBook(
            @PathVariable("book-uuid") String uuid,
            Authentication authentication) {
        log.info("Return book: {}", uuid);
        return ResponseEntity.ok(bookService.returnBorrowedBook(UUID.fromString(uuid), authentication));
    }

    @PatchMapping("/borrow/return/approve/{book-uuid}")
    public ResponseEntity<UUID> approveReturnBorrowBook(
            @PathVariable("book-uuid") String uuid,
            Authentication authentication) {
        log.info("Return book: {}", uuid);
        return ResponseEntity.ok(bookService.approveReturnBorrowedBook(UUID.fromString(uuid), authentication));
    }

    @PostMapping(value = "/cover/{book-uuid}", consumes = "multipart/form-data")
    public ResponseEntity<Void> uploadBookCoverPicture(
            @PathVariable("book-uuid") String uuid,
            @Parameter(description = "Book cover picture")
            @RequestPart("file")MultipartFile file,
            Authentication authentication) {
        log.info("Upload cover for book: {}", uuid);
        bookService.uploadBookCoverPicture(UUID.fromString(uuid), file, authentication);
        return ResponseEntity.ok().build();
    }

}
