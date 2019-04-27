import fuelTypes from "./FuelTypes";

function extractBrands(petrolStations) {
  const set = new Set();

  for (const { brand } of petrolStations) {
    set.add(brand.name);
  }

  return Array.from(set);
}

export function applyDiscount(petrolStations, discounts, selectedFuel) {
  let discountMap = new Map();

  const fuelIndex = fuelTypes.findIndex(value => value.key === selectedFuel);

  discounts.forEach(d => discountMap.set(d.brand, d.discounts));

  for (const petrolStation of petrolStations) {
    const brand = discountMap.get(petrolStation.brand.name);
    if (brand) {
      const { discount } = brand[fuelIndex];
      petrolStation.priceInformation.discount = discount;
    }
  }

  return petrolStations;
}

export function mergeDiscountModel(petrolStations, discounts) {
  const brands = extractBrands(petrolStations);
  const defaultDiscounts = fuelTypes.map(function (fuel) {
    return { fuelKey: fuel.key, discount: 0 }
  });

  const newDiscountBrands = [];

  for (const brand of brands) {
    if (discounts.findIndex(d => d.brand === brand) === -1) {
      newDiscountBrands.push({ brand, discounts: defaultDiscounts })
    }
  }

  return discounts.concat(newDiscountBrands);
}

export function updateDiscountModel({ brand, discountValue, fuelKey }, discountModels) {
  const brandToUpdate = discountModels.find(val => val.brand === brand);

  if (brandToUpdate === null) {
    console.warn(`No such ${brand} is found in existing records, this shouldn't really happen.`);
    return discountModels;
  }

  const discountByFuelKey = brandToUpdate.discounts.find(d => d.fuelKey === fuelKey);

  discountByFuelKey.discount = discountValue;

  return discountModels;
}
