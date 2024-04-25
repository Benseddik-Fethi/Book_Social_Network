
interface FallbackRenderProps {
    error: string;
    resetErrorBoundary: () => void;
}

const fallbackRender = ({error, resetErrorBoundary}: FallbackRenderProps) => {

    return (
        <div className="flex justify-center items-center">
            <div className="rounded border border-gray-200 p-10">
                <div className="text-center">
                    <h1 className="text-red-500">Oops! Une erreur est survenue.</h1>
                    <p className="mt-10 text-sm text-gray-400">{error}</p>
                    <button onClick={resetErrorBoundary} className="mt-10 bg-red-500 text-white px-4 py-2 rounded">
                        Réessayer
                    </button>
                </div>
            </div>
        </div>
    )
}

export default fallbackRender;