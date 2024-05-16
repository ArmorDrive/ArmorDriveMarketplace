import logo from './logo.svg';
import './App.css';
import {Component} from "react";

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

class App extends Component {
  state = {
    message: ''
  };

  async componentDidMount() {
    try {
      const response = await fetch('http://localhost:5000/');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const message = await response.text();
      this.setState({ message });
    } catch (error) {
      console.error('Error fetching data:', error);
      // Handle error, set default message, etc.
    }
  }

  render() {
    const {message} = this.state;
    console.log(message)
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>MESSAGE</h2>
              {message}
            </div>
          </header>
        </div>
    );
  }
}

export default App;
