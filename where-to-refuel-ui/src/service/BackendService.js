export async function getNearestPetrolStations({latitude, longitude}, fuelType) {
  return fetch(`/near-by-petrol-stations?latitude=${latitude}&longitude=${longitude}&fuel=${fuelType}`)
    .then(res => res.json());
}
