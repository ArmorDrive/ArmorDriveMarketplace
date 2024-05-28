import {getText} from "./utils/getText";
import {Title} from "./components/text";
import styles from '../src/styles/App.module.scss'
import { AppLayout } from "./components";


function App() {
    // const [message, setMessage] = useState('');

    // useEffect(() => {
    //     async function fetchData() {
    //         try {
    //             const response = await fetch('/api/welcome_message');
    //             if (!response.ok) {
    //                 throw new Error('Network response was not ok');
    //             }
    //             const message = await response.text();
    //             setMessage(message);
    //         } catch (error) {
    //             console.error('Error fetching data:', error);
    //             // Handle error, set default message, etc.
    //         }
    //     }

    //     fetchData();
    // }, []);

    // console.log(message);

    return (
        <div className={styles.App}>
            <div className="App">
                {/* test */}
                <Title text="test"/>
                {`${getText('title')} ${getText('name')}`}
                {`${getText('test')}`}

            </div>
            <div>
                <header>
                    <div>
                        {/* <h2>test : {message}</h2> */}
                        test
                    </div>
                </header>
            </div>
        </div>

    );
}


export default App;
