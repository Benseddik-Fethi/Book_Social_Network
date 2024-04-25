/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface ReturnBorrowBook$Params {
  'book-uuid': string;
}

export function returnBorrowBook(http: HttpClient, rootUrl: string, params: ReturnBorrowBook$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
  const rb = new RequestBuilder(rootUrl, returnBorrowBook.PATH, 'patch');
  if (params) {
    rb.path('book-uuid', params['book-uuid'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<string>;
    })
  );
}

returnBorrowBook.PATH = '/books/borrow/return/{book-uuid}';
