import React from 'react';
import { NavigateToButton } from "./NavigateToButton";
import { Badge } from "reactstrap";

export function PetrolStationListRow({
                                       coordinates, brand, name, hasDiscount, tripCost, totalCost,
                                       distance, duration, appliedDiscount, price
                                     }) {
  return (
    <tr>
      <td>{brand}</td>
      <td>{name}</td>
      <td><NavigateToButton {...coordinates}/></td>
      <td>{distance}</td>
      <td>{duration}</td>
      {hasDiscount ?
        <td>{price}<Badge title={"Цената е с отстъпка"} pill>{appliedDiscount}</Badge></td>
        : <td>{price}</td>}
      <td>{tripCost}</td>
      <td>{totalCost}</td>
    </tr>
  )
}
