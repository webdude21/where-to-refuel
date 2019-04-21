import React from 'react';

export function PetrolStationListRow({ brand, name, tripCost, totalCost, distance, duration, price }) {
  return (
    <tr>
      <td>{brand}</td>
      <td>{name}</td>
      <td>{distance}</td>
      <td>{duration}</td>
      <td>{price}</td>
      <td>{tripCost}</td>
      <td>{totalCost}</td>
    </tr>
  )
}
