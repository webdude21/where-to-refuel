export async function getNearestPetrolStations({ coords: { latitude, longitude } }, fuelType) {
  return (await fetch(`near-by-petrol-stations?latitude=${latitude}&longitude=${longitude}&fuel=${fuelType}`)).json();
}

export async function getUserLogs() {
  return (await fetch("analytics/user-logs")).json();
}
