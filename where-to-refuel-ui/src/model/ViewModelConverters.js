export function toPetrolStationViewModel({ id, coordinates, brand, name, tripCost, totalCost, drivingInfo: { distance, duration }, priceInformation: { price } }) {
  return {
    id,
    name,
    coordinates,
    brand: brand.name,
    tripCost: `${tripCost.toFixed(2)} лв.`,
    totalCost: `${totalCost.toFixed(2)} лв.`,
    distance: `${distance.toFixed(2)} км.`,
    duration: `${Number.parseInt(duration)} мин`,
    price: `${price} лв.`,
  }
}
