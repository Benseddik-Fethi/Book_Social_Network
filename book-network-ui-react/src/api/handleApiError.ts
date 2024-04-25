import {ApiError} from "./core/ApiError.ts";
import {toast} from "react-toastify";

const handleApiError = (error: ApiError) => {
    if (!error.body) return;

    if (error.body.message) {
        toast.error(error.body.message);
        return;
    }

    if (error.body.validationErrors instanceof Array) {
        error.body.validationErrors.forEach((error: string) => {
            toast.error(error);
        });
        return;
    }

    toast.error("Une erreur est survenue");
}

export default handleApiError;
