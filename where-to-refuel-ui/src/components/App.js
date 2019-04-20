import React, {Component} from 'react';
import './App.css';
import {getNearestPetrolStations} from "../service/BackendService";

class App extends Component {

  componentDidMount() {
    getNearestPetrolStations({latitude: 42.6567825, longitude: 23.2857181}, "LPG")
      .then(res => console.log(res));
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }
}

export default App;
