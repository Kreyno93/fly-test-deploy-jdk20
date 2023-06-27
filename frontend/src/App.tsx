import React from 'react';
import './App.css';

import {Route, Routes} from "react-router-dom";
import Homepage from "./components/Homepage";
import CarGallery from "./components/CarGallery";
import AddCar from "./components/AddCar";
import LoginPage from "./components/LoginPage";
import useUser from "./components/hooks/useUser";
import ProtectedRoutes from "./components/ProtectedRoutes";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export type Car = {
    id: string,
    brand: string,
    model: string,
}

function App() {

    const {login, user} = useUser()

    return (
        <div className="App">
            <ToastContainer
                position="top-center"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="dark"/>
            <header className="App-header">
                <Routes>
                    <Route path={"/"} element={<Homepage/>}/>
                    <Route element={<ProtectedRoutes user={user}/>}>
                        <Route path={"/add-car"} element={<AddCar/>}/>
                        <Route path={"/cars"} element={<CarGallery/>}/>
                    </Route>
                    <Route path={"/login"} element={<LoginPage login={login}/>}/>
                </Routes>
            </header>
        </div>
    );
}

export default App;
