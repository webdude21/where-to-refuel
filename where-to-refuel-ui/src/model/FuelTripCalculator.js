function calculateFuelTripCostWith(fuelConsumptionConverter, fuelConsumption) {
  return function (distance, fuelPrice, fuelAmount) {
    return calculateFuelTripCost(fuelConsumptionConverter(fuelConsumption), distance, fuelPrice, fuelAmount)
  }
}

export function calculateFuelTripCostInLitersPer100Km(fuelConsumption) {
  return calculateFuelTripCostWith(fc => fc / 100, fuelConsumption);
}

function calculateFuelTripCost(fuelConsumption, distance, fuelPrice, fuelAmount) {
  const tripCost = distance * fuelConsumption * fuelPrice;
  const totalCost = (fuelPrice * fuelAmount) + tripCost;
  return { tripCost, totalCost };
}

