/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { findFeedbacksByBook } from '../fn/feedback/find-feedbacks-by-book';
import { FindFeedbacksByBook$Params } from '../fn/feedback/find-feedbacks-by-book';
import { PageResponseFeedbackResponse } from '../models/page-response-feedback-response';
import { saveFeedback } from '../fn/feedback/save-feedback';
import { SaveFeedback$Params } from '../fn/feedback/save-feedback';


/**
 * Feedback API
 */
@Injectable({ providedIn: 'root' })
export class FeedbackService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `saveFeedback()` */
  static readonly SaveFeedbackPath = '/feedbacks';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveFeedback()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveFeedback$Response(params: SaveFeedback$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return saveFeedback(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveFeedback$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveFeedback(params: SaveFeedback$Params, context?: HttpContext): Observable<string> {
    return this.saveFeedback$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `findFeedbacksByBook()` */
  static readonly FindFeedbacksByBookPath = '/feedbacks/book/{bookUuid}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findFeedbacksByBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFeedbacksByBook$Response(params: FindFeedbacksByBook$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedbackResponse>> {
    return findFeedbacksByBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findFeedbacksByBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFeedbacksByBook(params: FindFeedbacksByBook$Params, context?: HttpContext): Observable<PageResponseFeedbackResponse> {
    return this.findFeedbacksByBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFeedbackResponse>): PageResponseFeedbackResponse => r.body)
    );
  }

}
