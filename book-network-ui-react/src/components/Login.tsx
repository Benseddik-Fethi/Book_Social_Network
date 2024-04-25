import {Field, Form, Formik} from "formik";
import Input from "../shared/components/form/Input.tsx";
import {AuthenticationRequest, AuthenticationService} from "../api";

const Login = () => {

    const handleSubmit = (values: AuthenticationRequest) => {
        AuthenticationService.authenticate(values)
            .then(() => {
                console.log('Authenticated');
            })
            .catch((error) => {
                console.error(error.body);
            });
    }

    return (
        <Formik initialValues={{email: '', password: ''}} onSubmit={handleSubmit}>
            <Form className={"w-5/6 md:w-1/3 mx-auto pt-20"}>
                <Field name="email" type="email" placeholder="Email" component={Input}/>
                <Field name="password" type="password" placeholder="Password" component={Input}/>
                <button type="submit" className="w-full bg-green-500 text-white p-2 rounded mt-5">Login</button>
            </Form>
        </Formik>
    );

};

export default Login;