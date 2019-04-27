export function toPetrolStationViewModel({
                                           id, coordinates, brand, name, tripCost,
                                           totalCost, drivingInfo: { distance, duration },
                                           priceInformation: { price, discount }
                                         }) {
  return {
    id,
    name,
    coordinates,
    hasDiscount: discount > 0,
    brand: brand.name,
    appliedDiscount: `${discount.toFixed(2)} лв.`,
    tripCost: `${tripCost.toFixed(2)} лв.`,
    totalCost: `${totalCost.toFixed(2)} лв.`,
    distance: `${distance.toFixed(2)} км.`,
    duration: `${Number.parseInt(duration)} мин`,
    price: `${(price - discount).toFixed(2)} лв.`,
  }
}
