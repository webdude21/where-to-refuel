import React, { Component } from 'react';
import './App.css';
import { getNearestPetrolStations } from "../model/service/BackendService";
import { PetrolStationList } from "./PetrolStationList";
import { FuelTripInformationForm } from "./FuelTripInformationForm"
import { calculateFuelTripCostInLitersPer100Km } from "../model/FuelTripCalculator";
import { toPetrolStationViewModel } from "../model/ViewModelConverters";
import { defaultPetrolStationSorter } from "../model/PetrolStations";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = { nearByPetrolStations: [], fuelAmount: 40, fuelConsumption: 10 };
    this.handleFuelTripInfoChanged = this.handleFuelTripInfoChanged.bind(this);
  }

  async componentDidMount() {
    const nearByPetrolStations = await getNearestPetrolStations({ latitude: 42.6567825, longitude: 23.2857181 }, "LPG");
    this.setState({ nearByPetrolStations });
  }

  handleFuelTripInfoChanged(val) {
    this.setState(val);
  }

  render() {
    const { fuelAmount, fuelConsumption, nearByPetrolStations } = this.state;
    const fuelTripPriceCalculator = calculateFuelTripCostInLitersPer100Km(fuelConsumption);

    const nearByPetrolStationsViewModel = nearByPetrolStations
      .map(p => Object.assign(fuelTripPriceCalculator(p.drivingInfo.distance, p.priceInformation.price, fuelAmount), p))
      .sort(defaultPetrolStationSorter)
      .map(toPetrolStationViewModel);

    return (
      <div>
        <FuelTripInformationForm onFormDataChange={this.handleFuelTripInfoChanged} fuelAmount={fuelAmount}
                                 fuelConsumption={fuelConsumption}/>
        <PetrolStationList petrolStations={nearByPetrolStationsViewModel}/>
      </div>
    );
  }
}

export default App;
