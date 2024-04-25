import {createBrowserRouter} from "react-router-dom";
import MainLayout from "./components/layouts/MainLayout.tsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <MainLayout/>,
        children: [
            {
                path: "/",
                lazy: () => import("./views/HomeView"),
            },
        ]
    },
    {
        path: "/authenticate",
        lazy: () => import("./views/LoginView"),
    },

    {
        path: "/authenticate",
        lazy: () => import("./views/NotFoundView"),
    },
])

export default router;
