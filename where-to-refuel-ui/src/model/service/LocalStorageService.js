import fuelTypes from "../FuelTypes";

const USER_SETTINGS_KEY = "userSettings";

const DEFAULT_USER_SETTINGS = {
  nearByPetrolStations: [], fuelAmount: 40, fuelConsumption: 10,
  selectedFuel: fuelTypes[0].key, sortKey: "totalCost", ascending: true
};

export class LocalStorageService {

  set userSettings(value) {
    localStorage.setItem(USER_SETTINGS_KEY, JSON.stringify(value));
  }

  get userSettings() {
    return JSON.parse(localStorage.getItem(USER_SETTINGS_KEY)) || DEFAULT_USER_SETTINGS;
  }
}
