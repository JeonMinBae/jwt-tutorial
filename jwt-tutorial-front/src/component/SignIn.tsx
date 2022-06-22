import React from 'react';
import {useNavigate} from "react-router-dom";
import axiosInstance from "../module/axiosInstance";
import {ISignInDto} from "../module/auth/dto/ISignInDto";
import {SubmitHandler, useForm} from "react-hook-form";


const SignIn = () => {

    const navigate = useNavigate();

    const defaultValues = {
        userId: '',
        userPassword: '',
    }

    const {
        handleSubmit,
        register,
    } = useForm({defaultValues});


    const signInSubmit: SubmitHandler<ISignInDto> = (formData) => {

        axiosInstance.post('/api/auth/sign-in', {userId: formData.userId, userPassword: formData.userPassword})
            .then((res: any) => {
                console.log("response", JSON.stringify(res));
                localStorage.setItem("token", res.headers.authorization.substring(7))
                navigate('/');
            })
            .catch(error => {
                console.log("error", error);
            });
    }

    return (
        <form onSubmit={handleSubmit(signInSubmit)}>
            <div style={{width: "700px", margin: "0.5rem"}}>
                <p>Sign in</p>
                <input {...register('userId')} />
                <input {...register('userPassword')} />
                <button type={"submit"}>sign in</button>
                <button onClick={() => navigate('/sign-up')}>sign up</button>
            </div>
        </form>
    );
};

export default SignIn;