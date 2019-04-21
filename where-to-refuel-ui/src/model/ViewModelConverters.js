export function toPetrolStationViewModel({ id, brand, name, tripCost, totalCost, drivingInfo: { distance, duration }, priceInformation: { price } }) {
  return {
    id,
    name,
    brand: brand.name,
    price,
    tripCost: tripCost.toFixed(2),
    totalCost: totalCost.toFixed(2),
    distance: `${distance.toFixed(2)} км`,
    duration: `${Number.parseInt(duration)} мин`,
  }
}
