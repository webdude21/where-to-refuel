import React, { Component } from 'react';
import './App.css';
import { getNearestPetrolStations } from "../model/service/BackendService";
import { calculateFuelTripCostInLitersPer100Km } from "../model/FuelTripCalculator";
import { toPetrolStationViewModel } from "../model/ViewModelConverters";
import { getLocation } from "../model/service/LocationService";
import { FuelTripInformationForm } from "./FuelTripInformationForm";
import { PetrolStationList } from "./PetrolStationList";
import { getSorter } from "../model/PetrolStationsSortUtils";
import { LocalStorageService } from "../model/service/LocalStorageService";

const userSettingsService = new LocalStorageService();

class App extends Component {

  constructor(props) {
    super(props);
    this.state = userSettingsService.userSettings;
    this.handleFuelTripInfoChanged = this.handleFuelTripInfoChanged.bind(this);
    this.handleSortKeyChange = this.handleSortKeyChange.bind(this);
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

    userSettingsService.userSettings = this.state;
  }

  handleSortKeyChange(sortKey) {
    let sameSortKey = this.state.sortKey === sortKey;
    if (sameSortKey) {
      this.setState({ sortKey, ascending: !this.state.ascending });
    } else {
      this.setState({ sortKey, ascending: true })
    }
  }

  handleFuelTripInfoChanged(val) {
    this.setState(val);
  }

  render() {
    const { fuelAmount, fuelConsumption, selectedFuel } = this.state;
    const nearByPetrolStationsViewModel = this.getPetrolStations();

    return (
      <>
        <FuelTripInformationForm onFormDataChange={this.handleFuelTripInfoChanged} fuelAmount={fuelAmount}
                                 fuelConsumption={fuelConsumption} selectedFuel={selectedFuel}/>
        <PetrolStationList petrolStations={nearByPetrolStationsViewModel} onSortKeyChanged={this.handleSortKeyChange}/>
      </>
    );
  }

  getPetrolStations() {
    const { fuelAmount, fuelConsumption, nearByPetrolStations, sortKey, ascending } = this.state;

    if (nearByPetrolStations.length === 0) {
      return nearByPetrolStations;
    }

    const fuelTripPriceCalculator = calculateFuelTripCostInLitersPer100Km(fuelConsumption);

    const nearByPetrolWithFuelTripInfo = nearByPetrolStations
      .map(p => Object.assign(fuelTripPriceCalculator(p.drivingInfo.distance, p.priceInformation.price, fuelAmount), p));

    let viewModels = nearByPetrolWithFuelTripInfo
      .sort(getSorter(sortKey, nearByPetrolWithFuelTripInfo[0]))
      .map(toPetrolStationViewModel);

    if (!ascending) {
      viewModels.reverse();
    }

    return viewModels;
  }
}

export default App;
