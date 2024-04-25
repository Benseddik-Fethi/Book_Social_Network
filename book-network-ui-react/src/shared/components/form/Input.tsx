import {ErrorMessage} from "formik";
import CssUtils from "../../utils/ClassNameUtils.ts";

const Input = ({label, field, form, className, ...props}) => {
    return (
        <div className="relative mb-7 w-full">
            {label && <label htmlFor={field.name} className="text-sm text-gray-400">{label}</label>}
            <input
                id={field.name}
                className={CssUtils.classNames(
                    "bg-slate-500 text-slate-50 w-full border border-gray-800 placeholder:text-gray-400 p-2 rounded mt-1 focus:outline-none focus:border-green-400",
                    form.touched[field.name] && form.errors[field.name] ? "border-red-500" : "",
                    className)}
                type={props.type || "text"}
                {...field}
                {...props}
            />
            <ErrorMessage name={field.name} component="small" className="text-red-500 absolute bottom-0 left-0"/>
        </div>
    )
}

export default Input;