import React from 'react';
import {AuthType} from "../util/AuthType";
import {useForm} from "react-hook-form";
import {AuthOption} from "../util/AuthOption";
import {ISignUpDto} from "../module/auth/dto/ISignUpDto";
import axiosInstance from "../module/axiosInstance";
import {useNavigate} from "react-router-dom";


const SignUp = () => {

    const navigate = useNavigate();

    const defaultValues: ISignUpDto = {
        userId: '',
        userPassword: '',
        userRole: AuthType.ROLE_ADMIN,
    }

    const {
        handleSubmit,
        register,
    } = useForm({defaultValues})

    const signUpSubmit = (formData: ISignUpDto) => {

        axiosInstance.post("/api/auth/sign-up", formData)
            .then((res: any) => {
                alert("가입되었습니다.");
                navigate('/sign-in');
            })
        // console.log(formData)
    }

    return (
        <form onSubmit={handleSubmit(signUpSubmit)}>
            <div>
                <p>Sign up</p>
                <div style={{width: "230px", margin: "0.5rem"}}>
                    <div style={{display: "flex", justifyContent: "space-between", margin: "0.1rem"}}>
                        <label>아이디: </label>
                        <input {...register('userId')} />
                    </div>
                    <div style={{display: "flex", justifyContent: "space-between", margin: "0.1rem"}}>
                        <label>비밀번호: </label>
                        <input {...register('userPassword')} />
                    </div>
                    <div style={{display: "flex", justifyContent: "space-between", margin: "0.1rem"}}>
                        <label>구분: </label>
                        <select {...register("userRole")}>
                            {
                                AuthOption.map(option => (
                                    <option key={option.value} value={option.value}>{option.label}</option>
                                ))
                            }
                        </select>
                        {/*<input {...register('userRole')} />*/}
                    </div>
                    <button type={"submit"}>sign up</button>
            </div>
            </div>
        </form>
    );
};

export default SignUp;