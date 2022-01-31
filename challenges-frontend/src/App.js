import './App.css';
import * as ReactRouterDOM from "react-router-dom";
import LoginComponent from "./components/LoginComponent";
import RegistrationOrder from "./components/RegistrationOrder";
import * as React from "react";

const Router = ReactRouterDOM.BrowserRouter;
const Route = ReactRouterDOM.Route;
const Routes = ReactRouterDOM.Routes;


function App() {
    return (
        <div className="App">
            <header className="App-header">
                <Router>
                    <div>
                        <Routes>
                            <Route path="/successful" element={<Successful/>}/>
                            <Route path="/login" element={<LoginComponent/>}/>
                            <Route path="/*" element={<RegistrationOrder/>}/>
                        </Routes>
                    </div>
                </Router>
            </header>
        </div>
    );
}

function Successful() {
    return (
        <div>
            <label>Registration order is successful!</label>
        </div>
    )
}

export default App;
