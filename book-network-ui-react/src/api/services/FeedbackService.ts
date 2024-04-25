/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { FeedbackRequest } from '../models/FeedbackRequest';
import type { PageResponseFeedbackResponse } from '../models/PageResponseFeedbackResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class FeedbackService {
    /**
     * @param requestBody
     * @returns string OK
     * @throws ApiError
     */
    public static saveFeedback(
        requestBody: FeedbackRequest,
    ): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/feedbacks',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * @param bookUuid
     * @param page
     * @param size
     * @returns PageResponseFeedbackResponse OK
     * @throws ApiError
     */
    public static findFeedbacksByBook(
        bookUuid: string,
        page?: number,
        size: number = 10,
    ): CancelablePromise<PageResponseFeedbackResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/feedbacks/book/{bookUuid}',
            path: {
                'bookUuid': bookUuid,
            },
            query: {
                'page': page,
                'size': size,
            },
        });
    }
}
