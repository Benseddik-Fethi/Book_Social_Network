/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { AuthenticationRequest } from '../models/AuthenticationRequest';
import type { AuthenticationResponse } from '../models/AuthenticationResponse';
import type { RegisterRequest } from '../models/RegisterRequest';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class AuthenticationService {
    /**
     * @param requestBody
     * @returns any Created
     * @throws ApiError
     */
    public static register(
        requestBody: RegisterRequest,
    ): CancelablePromise<Record<string, any>> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/auth/register',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * @param requestBody
     * @returns AuthenticationResponse OK
     * @throws ApiError
     */
    public static authenticate(
        requestBody: AuthenticationRequest,
    ): CancelablePromise<AuthenticationResponse> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/auth/authenticate',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * @param token
     * @returns any OK
     * @throws ApiError
     */
    public static confirmAccount(
        token: string,
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/auth/activate-account',
            query: {
                'token': token,
            },
        });
    }
}
