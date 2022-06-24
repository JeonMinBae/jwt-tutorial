import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import axiosInstance from "../module/axiosInstance";

const Home = () => {

    const navigate = useNavigate();
    const [result, setResult] = useState("");


    useEffect(() => {
        if(!localStorage.getItem('token')){
            navigate('sign-in');
        }

    }, [])

    const requestOnlyAdminUrl = () => {

        axiosInstance.get('/api/users/only-admin')
            .then((res: any) => {
                setResult(() => "Only Admin: " + JSON.stringify(res));
            })
            .catch(error => {
                setResult(() => "Only Admin: " + JSON.stringify(error));
            })
    }

    const requestUserAndAdminUrl = () => {
        axiosInstance.get('/api/users/admin-user')
            .then((res: any) => {
                setResult(() => "User Admin: " + JSON.stringify(res));
            })
            .catch(error => {
                setResult(() => "User Admin: " + JSON.stringify(error));
            })
    }

    const signOut = () =>{
        localStorage.removeItem("token");
        navigate('/sign-in');
    }


    return (
        <div>
            <p>Home</p>
            <button onClick={signOut}>sign out</button>
            <button onClick={requestOnlyAdminUrl}>admin</button>
            <button onClick={requestUserAndAdminUrl}>user</button>
            <div style={{margin: "200px", color: "gray"}}>{result}</div>
        </div>
    );
};

export default Home;