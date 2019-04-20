import React from 'react';

export function PetrolStationListRow({ brand, name, drivingInfo: { distance, duration }, priceInformation: { price } }) {
  return (
    <tr>
      <td>{brand.name}</td>
      <td>{name}</td>
      <td>{distance}</td>
      <td>{duration}</td>
      <td>{price}</td>
    </tr>
  )
}
