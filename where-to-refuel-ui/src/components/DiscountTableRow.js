import React from 'react';
import { Input } from "reactstrap";

export function DiscountTableRow({ brand, discounts, onDiscountChange }) {
  return (
    <tr>
      <td>{brand}</td>
      {discounts.map(({ fuelKey, discount }) => (
        <td key={`${brand}-${fuelKey}`}>
          <Input min={0}
                 max={0.15}
                 step={0.01}
                 type="number"
                 data-brand-key={brand}
                 data-key-fuel={fuelKey}
                 onChange={onDiscountChange}
                 value={discount}/>
        </td>))}
    </tr>
  )
}
