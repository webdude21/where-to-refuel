import React from 'react';
import { NavigateToButton } from "./NavigateToButton";

export function PetrolStationListRow({ coordinates, brand, name, tripCost, totalCost, distance, duration, price }) {
  return (
    <tr>
      <td>{brand}</td>
      <td>{name}</td>
      <td><NavigateToButton {...coordinates}/></td>
      <td>{distance}</td>
      <td>{duration}</td>
      <td>{price}</td>
      <td>{tripCost}</td>
      <td>{totalCost}</td>
    </tr>
  )
}
