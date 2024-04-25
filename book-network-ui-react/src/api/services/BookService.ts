/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BookRequest } from '../models/BookRequest';
import type { BookResponse } from '../models/BookResponse';
import type { PageResponseBookResponse } from '../models/PageResponseBookResponse';
import type { PageResponseBorrowedBookResponse } from '../models/PageResponseBorrowedBookResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class BookService {
    /**
     * @param page
     * @param size
     * @returns PageResponseBookResponse OK
     * @throws ApiError
     */
    public static findAllBooks(
        page?: number,
        size: number = 10,
    ): CancelablePromise<PageResponseBookResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/books',
            query: {
                'page': page,
                'size': size,
            },
        });
    }
    /**
     * @param requestBody
     * @returns string OK
     * @throws ApiError
     */
    public static saveBook(
        requestBody: BookRequest,
    ): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/books',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * @param bookUuid
     * @param formData
     * @returns any OK
     * @throws ApiError
     */
    public static uploadBookCoverPicture(
        bookUuid: string,
        formData?: {
            /**
             * Book cover picture
             */
            file: Blob;
        },
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/books/cover/{book-uuid}',
            path: {
                'book-uuid': bookUuid,
            },
            formData: formData,
            mediaType: 'multipart/form-data',
        });
    }
    /**
     * @param bookUuid
     * @returns string OK
     * @throws ApiError
     */
    public static borrowBook(
        bookUuid: string,
    ): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/books/borrow/{book-uuid}',
            path: {
                'book-uuid': bookUuid,
            },
        });
    }
    /**
     * @param bookUuid
     * @returns string OK
     * @throws ApiError
     */
    public static updateShareableStatus(
        bookUuid: string,
    ): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'PATCH',
            url: '/books/shareable/{book-uuid}',
            path: {
                'book-uuid': bookUuid,
            },
        });
    }
    /**
     * @param bookUuid
     * @returns string OK
     * @throws ApiError
     */
    public static returnBorrowBook(
        bookUuid: string,
    ): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'PATCH',
            url: '/books/borrow/return/{book-uuid}',
            path: {
                'book-uuid': bookUuid,
            },
        });
    }
    /**
     * @param bookUuid
     * @returns string OK
     * @throws ApiError
     */
    public static approveReturnBorrowBook(
        bookUuid: string,
    ): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'PATCH',
            url: '/books/borrow/return/approve/{book-uuid}',
            path: {
                'book-uuid': bookUuid,
            },
        });
    }
    /**
     * @param bookUuid
     * @returns string OK
     * @throws ApiError
     */
    public static updateArchivedStatus(
        bookUuid: string,
    ): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'PATCH',
            url: '/books/archived/{book-uuid}',
            path: {
                'book-uuid': bookUuid,
            },
        });
    }
    /**
     * @param bookUuid
     * @returns BookResponse OK
     * @throws ApiError
     */
    public static getBook(
        bookUuid: string,
    ): CancelablePromise<BookResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/books/{book-uuid}',
            path: {
                'book-uuid': bookUuid,
            },
        });
    }
    /**
     * @param page
     * @param size
     * @returns PageResponseBorrowedBookResponse OK
     * @throws ApiError
     */
    public static findAllReturnedBooks(
        page?: number,
        size: number = 10,
    ): CancelablePromise<PageResponseBorrowedBookResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/books/returned',
            query: {
                'page': page,
                'size': size,
            },
        });
    }
    /**
     * @param page
     * @param size
     * @returns PageResponseBookResponse OK
     * @throws ApiError
     */
    public static findAllBooksByOwner(
        page?: number,
        size: number = 10,
    ): CancelablePromise<PageResponseBookResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/books/owner',
            query: {
                'page': page,
                'size': size,
            },
        });
    }
    /**
     * @param page
     * @param size
     * @returns PageResponseBorrowedBookResponse OK
     * @throws ApiError
     */
    public static findAllBorrowedBooks(
        page?: number,
        size: number = 10,
    ): CancelablePromise<PageResponseBorrowedBookResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/books/borrowed',
            query: {
                'page': page,
                'size': size,
            },
        });
    }
}
