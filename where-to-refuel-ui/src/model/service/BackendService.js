export async function getNearestPetrolStations({ coords: { latitude, longitude } }, fuelType) {
  let responsePromise = await fetch(`near-by-petrol-stations?latitude=${latitude}&longitude=${longitude}&fuel=${fuelType}`);
  return responsePromise.json();
}
