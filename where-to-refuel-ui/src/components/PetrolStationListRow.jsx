import React from 'react';
import { NavigateToButton } from "./NavigateToButton";
import { Badge } from "reactstrap";

export function PetrolStationListRow({
                                       coordinates, brand, name, hasDiscount, tripCost, totalCost,
                                       distance, duration, appliedDiscount, price, address
                                     }) {
  return (
    <tr>
      <td>{brand}</td>
      <td title={address}>{name}</td>
      <td title={address}><NavigateToButton {...coordinates}/></td>
      <td>{distance}</td>
      <td>{duration}</td>
      <td>{price} {hasDiscount && (<Badge title={"Цената е с отстъпка"} pill>{appliedDiscount}</Badge>)}</td>
      <td>{tripCost}</td>
      <td>{totalCost}</td>
    </tr>
  )
}
