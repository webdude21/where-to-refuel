export async function getNearestPetrolStations({ coords: { latitude, longitude } }, fuelType) {
  const responsePromise = await fetch(`near-by-petrol-stations?latitude=${latitude}&longitude=${longitude}&fuel=${fuelType}`);
  return responsePromise.json();
}

export async function getUserLogs() {
  const resp = await fetch("analytics/user-logs");
  return resp.json();
}
