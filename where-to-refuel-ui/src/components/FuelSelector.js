import React from 'react';
import fuelTypes from "../model/FuelTypes";
import { Input } from "reactstrap";

export function FuelSelector() {
  return (
    <Input type="select" name="select">
      {fuelTypes.map(fuelType => <option key={fuelType.key}>{fuelType.name}</option>)}
    </Input>
  )
}
