export async function getNearestPetrolStations({ coords: { latitude, longitude } }, fuelType) {
  let responsePromise = await fetch(` https://where-to-refuel.herokuapp.com/near-by-petrol-stations?latitude=${latitude}&longitude=${longitude}&fuel=${fuelType}`);
  return responsePromise.json();
}
