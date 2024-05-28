import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import {getText} from "./utils/getText";


function App() {
    const [message, setMessage] = useState('');

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await fetch('/api/welcome_message');
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const message = await response.text();
                setMessage(message);
            } catch (error) {
                console.error('Error fetching data:', error);
                // Handle error, set default message, etc.
            }
        }

        fetchData();
    }, []);

    console.log(message);

    return (
        <div>
            <div className="App">
                {/* test */}
                {`${getText('title')} ${getText('name')}`}
                {`${getText('test')}`}
            </div>
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <div className="App-intro">
                        <h2>{message}</h2>
                    </div>
                </header>
            </div>

        </div>

    );
}


export default App;
