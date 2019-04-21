import React, { Component } from 'react';
import './App.css';
import { getNearestPetrolStations } from "../model/service/BackendService";
import { calculateFuelTripCostInLitersPer100Km } from "../model/FuelTripCalculator";
import { toPetrolStationViewModel } from "../model/ViewModelConverters";
import { defaultPetrolStationSorter } from "../model/PetrolStations";
import { getLocation } from "../model/service/LocationService";
import fuelTypes from "../model/FuelTypes";
import { FuelTripInformationForm } from "./FuelTripInformationForm";
import { PetrolStationList } from "./PetrolStationList";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = { nearByPetrolStations: [], fuelAmount: 40, fuelConsumption: 10, selectedFuel: fuelTypes[0].key };
    this.handleFuelTripInfoChanged = this.handleFuelTripInfoChanged.bind(this);
    this.getPetrolStationInformation = this.getPetrolStationInformation.bind(this);
  }

  async componentDidMount() {
    await this.getPetrolStationInformation(this.state.selectedFuel);
  }

  async getPetrolStationInformation(selectedFuel) {
    try {
      let nearByPetrolStations = await getNearestPetrolStations(await getLocation(), selectedFuel);
      this.setState({ nearByPetrolStations })
    } catch (e) {
      console.log(e);
    }
  }

  async componentDidUpdate(prevProps, prevState, snapshot) {
    if (prevState.selectedFuel !== this.state.selectedFuel) {
      await this.getPetrolStationInformation(this.state.selectedFuel);
    }
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
      <>
        <FuelTripInformationForm onFormDataChange={this.handleFuelTripInfoChanged} fuelAmount={fuelAmount}
                                 fuelConsumption={fuelConsumption}/>
        <PetrolStationList petrolStations={nearByPetrolStationsViewModel}/>
      </>
    );
  }
}

export default App;
