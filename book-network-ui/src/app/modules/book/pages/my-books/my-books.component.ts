import {Component, OnInit} from '@angular/core';
import {BookCardComponent} from "../../components/book-card/book-card.component";
import {NgForOf, NgIf} from "@angular/common";
import {PageResponseBookResponse} from "../../../../services/models/page-response-book-response";
import {BookService} from "../../../../services/services/book.service";
import {Router, RouterLink} from "@angular/router";
import {BookResponse} from "../../../../services/models/book-response";

@Component({
  selector: 'app-my-books',
  standalone: true,
  imports: [
    BookCardComponent,
    NgForOf,
    NgIf,
    RouterLink
  ],
  templateUrl: './my-books.component.html',
  styleUrl: './my-books.component.scss'
})
export class MyBooksComponent implements OnInit {
  bookResponse: PageResponseBookResponse = {};
  page: number = 0;
  size: number = 5;

  constructor(
    private bookService: BookService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllBooks();
  }

  private findAllBooks() {
    this.bookService.findAllBooksByOwner({
      size: this.size,
      page: this.page
    }).subscribe({
      next: (books) => {
        this.bookResponse = books;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllBooks();
  }

  goToPreviousPage() {
    this.page--;
    this.findAllBooks();
  }

  goToPage(index: number) {
    this.page = index;
    this.findAllBooks();
  }

  goToNextPage() {
    this.page++;
    this.findAllBooks();
  }

  goToLastPage() {
    this.page = this.bookResponse.totalPages as number - 1;
    this.findAllBooks();
  }

  get isLastPage(): boolean {
    return this.page === this.bookResponse.totalPages as number - 1;
  }

  archiveBook(book: BookResponse) {
    this.bookService.updateArchivedStatus({
      'book-uuid': book.uuid as string
    }).subscribe({
      next: () => {
        book.archived = !book.archived;
      }
    })
  }

  editBook(book: BookResponse) {
    this.router.navigate(['books', 'manage', book.uuid]);
  }

  shareBook(book: BookResponse) {
    this.bookService.updateShareableStatus({
      'book-uuid': book.uuid as string
    }).subscribe({
      next: () => {
        book.shareable = !book.shareable;
      }
    })
  }
}
