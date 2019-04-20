import React, { Component } from 'react';
import './App.css';
import { getNearestPetrolStations } from "../service/BackendService";
import { PetrolStationList } from "./PetrolStationList";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = { nearByPetrolStations: [] };
  }

  async componentDidMount() {
    const nearByPetrolStations = await getNearestPetrolStations({ latitude: 42.6567825, longitude: 23.2857181 }, "LPG");
    this.setState({ nearByPetrolStations });
  }

  render() {
    const { nearByPetrolStations } = this.state;

    return (
      <PetrolStationList petrolStations={nearByPetrolStations}/>
    );
  }
}

export default App;
