import fuelTypes from "../FuelTypes";

const USER_SETTINGS_KEY = "userSettings";

const DEFAULT_USER_SETTINGS = {
  version: 2, nearByPetrolStations: [], fuelAmount: 40, fuelConsumption: 10, discounts: [], isLoading: true,
  selectedFuel: fuelTypes[0].key, sortKey: "totalCost", ascending: true, activeTab: '1'
};

export class LocalStorageService {

  static set userSettings(value) {
    localStorage.setItem(USER_SETTINGS_KEY, JSON.stringify(value));
  }

  static get userSettings() {
    const storedSettings = JSON.parse(localStorage.getItem(USER_SETTINGS_KEY));
    if (storedSettings && storedSettings.version === DEFAULT_USER_SETTINGS.version) {
      return storedSettings;
    } else {
      localStorage.clear();
      return DEFAULT_USER_SETTINGS;
    }
  }
}
