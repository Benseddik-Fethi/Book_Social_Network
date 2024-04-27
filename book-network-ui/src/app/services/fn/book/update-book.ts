/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BookRequest } from '../../models/book-request';
import { SavedBookResponse } from '../../models/saved-book-response';

export interface UpdateBook$Params {
  'book-uuid': string;
      body: BookRequest
}

export function updateBook(http: HttpClient, rootUrl: string, params: UpdateBook$Params, context?: HttpContext): Observable<StrictHttpResponse<SavedBookResponse>> {
  const rb = new RequestBuilder(rootUrl, updateBook.PATH, 'patch');
  if (params) {
    rb.path('book-uuid', params['book-uuid'], {});
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<SavedBookResponse>;
    })
  );
}

updateBook.PATH = '/books/{book-uuid}';
