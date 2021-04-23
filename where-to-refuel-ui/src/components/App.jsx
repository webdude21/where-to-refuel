import React, { Component } from 'react';
import './App.css';
import { getNearestPetrolStations } from "../model/service/BackendService";
import { getFuelTripCostInLitersPer100KmCalculator } from "../model/FuelTripCalculator";
import { toPetrolStationViewModel } from "../model/ViewModelConverters";
import { getLocation } from "../model/service/LocationService";
import { FuelTripInformationForm } from "./FuelTripInformationForm";
import { PetrolStationList } from "./PetrolStationList";
import { getSorter } from "../model/PetrolStationsSortUtils";
import { LocalStorageService } from "../model/service/LocalStorageService";
import { DiscountTable } from "./DiscountTable";
import { Nav, NavItem, NavLink, Spinner, TabContent, TabPane } from "reactstrap";
import classnames from "classnames";
import { applyDiscount, mergeDiscountModel, updateDiscountModel } from "../model/Discount";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = LocalStorageService.userSettings;
    this.handleFuelTripInfoChanged = this.handleFuelTripInfoChanged.bind(this);
    this.handleSortKeyChange = this.handleSortKeyChange.bind(this);
    this.handleDiscountChange = this.handleDiscountChange.bind(this);
    this.toggle = this.toggle.bind(this);
    this.getPetrolStationInformation = this.getPetrolStationInformation.bind(this);
  }

  async componentDidMount() {
    await this.getPetrolStationInformation(this.state.selectedFuel);
  }

  async getPetrolStationInformation(selectedFuel) {
    try {
      this.setState({ isLoading: true });
      let nearByPetrolStations = await getNearestPetrolStations(await getLocation(), selectedFuel);
      this.setState({ isLoading: false });
      const discounts = mergeDiscountModel(nearByPetrolStations, LocalStorageService.userSettings.discounts);
      this.setState({ nearByPetrolStations, discounts })
    } catch (e) {
      console.log(e);
    }
  }

  async componentDidUpdate(prevProps, prevState, snapshot) {
    if (prevState.selectedFuel !== this.state.selectedFuel) {
      await this.getPetrolStationInformation(this.state.selectedFuel);
    }

    LocalStorageService.userSettings = this.state;
  }

  handleDiscountChange(updateInfo) {
    const discounts = updateDiscountModel(updateInfo, LocalStorageService.userSettings.discounts);
    this.setState({ discounts });
  }

  handleSortKeyChange(newSortKey) {
    const { sortKey, ascending } = this.state;
    let sameSortKey = sortKey === newSortKey;
    if (sameSortKey) {
      this.setState({ sortKey: newSortKey, ascending: !ascending });
    } else {
      this.setState({ sortKey: newSortKey, ascending: true })
    }
  }

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({ activeTab: tab });
    }
  }

  handleFuelTripInfoChanged(val) {
    this.setState(val);
  }

  render() {
    const { fuelAmount, twoWayTrip, fuelConsumption, selectedFuel, discounts, isLoading } = this.state;
    const nearByPetrolStationsViewModel = this.getPetrolStations();

    return (
      <>
        <Nav tabs>
          <NavItem>
            <NavLink className={classnames({ active: this.state.activeTab === '1' })}
                     onClick={() => this.toggle('1')}>Бензиностанции</NavLink>
          </NavItem>
          <NavItem>
            <NavLink className={classnames({ active: this.state.activeTab === '2' })}
                     onClick={() => this.toggle('2')}>Отстъпки</NavLink>
          </NavItem>
          {isLoading && <Spinner className="spinner" color="primary"/>}
        </Nav>
        <TabContent activeTab={this.state.activeTab}>
          <TabPane tabId="1">
            <FuelTripInformationForm onFormDataChange={this.handleFuelTripInfoChanged}
                                     twoWayTrip={twoWayTrip}
                                     fuelAmount={fuelAmount}
                                     fuelConsumption={fuelConsumption}
                                     selectedFuel={selectedFuel}/>
            <PetrolStationList petrolStations={nearByPetrolStationsViewModel}
                               isLoading={isLoading}
                               onSortKeyChanged={this.handleSortKeyChange}/>
          </TabPane>
          <TabPane tabId="2">
            <DiscountTable discounts={discounts} discountChange={this.handleDiscountChange}/>
          </TabPane>
        </TabContent>
      </>
    );
  }

  getPetrolStations() {
    const { fuelAmount, twoWayTrip, fuelConsumption, discounts, nearByPetrolStations, sortKey, ascending, selectedFuel } = this.state;

    if (nearByPetrolStations.length === 0) {
      return nearByPetrolStations;
    }

    applyDiscount(nearByPetrolStations, discounts, selectedFuel);

    const fuelConsumptionAdjustedForTripType = twoWayTrip ? fuelConsumption * 2 : fuelConsumption;
    const fuelTripPriceCalculator = getFuelTripCostInLitersPer100KmCalculator(fuelConsumptionAdjustedForTripType);

    const nearByPetrolWithFuelTripInfo = nearByPetrolStations
      .map(p => Object.assign(fuelTripPriceCalculator(p.drivingInfo.distance, p.priceInformation.price - p.priceInformation.discount, fuelAmount), p));

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
