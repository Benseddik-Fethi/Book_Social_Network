import axios, {AxiosError, AxiosInstance, AxiosResponse, InternalAxiosRequestConfig} from 'axios';
import {useMemo} from "react";
import {useErrorBoundary} from "react-error-boundary";
import {toast} from "react-toastify";
import ErrorInterface from "../errors/ErrorInterface.ts";

const useAxios = () => {
    const {showBoundary} = useErrorBoundary();

    return useMemo(() => {
        const axiosInstance: AxiosInstance = axios.create({
            baseURL: import.meta.env.VITE_API_URL,
        });

        axiosInstance.interceptors.request.use((config: InternalAxiosRequestConfig) => {
            return config;
        });

        axiosInstance.interceptors.response.use(
            (response: AxiosResponse) => response,
            (error: AxiosError) => {
                const errorContainer: ErrorInterface = handleError(error);
                if (errorContainer.isToast) {
                    toast.error(errorContainer.message);
                } else {
                    showBoundary(errorContainer);
                }
                return Promise.reject(error);
            }
        );

        return axiosInstance;
    }, [showBoundary]);
}

export default useAxios;

function handleError(error: AxiosError): ErrorInterface {
    const errorContainer: ErrorInterface = {
        message: "",
        isToast: false
    };
    if (error.response?.status) {
        errorContainer.message = `Request failed with status code ${error.response.status}`;
        const status: number = error.response.status;
        switch (status) {
            case 401:
                errorContainer.isToast = true;
                break;
            case 400:
                errorContainer.isToast = true;
                break;
            default:
                errorContainer.isToast = false;
                break;
        }
    } else if (error.code) {
        errorContainer.message = `Request failed with error code ${error.code}`;
    } else {
        errorContainer.message = "Request failed with unknown error";
    }
    return errorContainer;
}
