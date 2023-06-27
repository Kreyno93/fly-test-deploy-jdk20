import React from 'react';
import axios from "axios";
import {toast} from "react-toastify";

function AddCar() {

    const [brand, setBrand] = React.useState<string>("");
    const [model, setModel] = React.useState<string>("");

    const onChangeHandlerBrand = (event: React.ChangeEvent<HTMLInputElement>) => {
        setBrand(event.target.value);
    }

    const onChangeHandlerModel = (event: React.ChangeEvent<HTMLInputElement>) => {
        setModel(event.target.value);
    }

    const onSubmitHandler = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        axios.post("/api/cars", {brand, model})
            .then((response) => {
            console.log(response.data);
        }).then(() => {
            setBrand("");
            setModel("");
        })
            .then(() => toast.success("New Car was added"))
            .catch((e) => toast.error(e.message))

    }

    return (
        <div>
            <form onSubmit={onSubmitHandler}>
                <h1>Adding Car</h1>
                <div>
                    <p>BRAND: </p>
                    <input type="text" value={brand} onChange={onChangeHandlerBrand}/>
                </div>
                <div>
                    <p>MODEL: </p>
                    <input type="text" value={model} onChange={onChangeHandlerModel}/>
                </div>
                <button>SEND</button>
            </form>
        </div>
    );
}

export default AddCar;