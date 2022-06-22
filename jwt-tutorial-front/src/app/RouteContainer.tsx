import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "../component/Home";
import SignIn from "../component/SignIn";
import SignUp from "../component/SignUp";

const RouteContainer = () => {


    return (
        <BrowserRouter>
            <Routes>
                <Route path={'/'} element={<Home/>}/>
                <Route path={'/sign-in'} element={<SignIn/>}/>
                <Route path={'/sign-up'} element={<SignUp/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default RouteContainer;