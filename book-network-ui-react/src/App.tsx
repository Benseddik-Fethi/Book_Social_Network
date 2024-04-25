import {ErrorBoundary} from "react-error-boundary";
import fallbackRender from "./errors/fallbackRender";
import {ToastContainer} from "react-toastify";
import {RouterProvider} from "react-router-dom";
import router from "./Routing.tsx";

const App = () => {
    return (
        <ErrorBoundary fallbackRender={fallbackRender}>
            <RouterProvider router={router} />
            <ToastContainer
                position="bottom-right"
                autoClose={3000}
                theme={'dark'}
            />
        </ErrorBoundary>
    )
}

export default App;
