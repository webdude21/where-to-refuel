import React, { Component } from 'react';
import { Table } from "reactstrap";
import fuelTypes from "../model/FuelTypes";
import { DiscountTableRow } from "./DiscountTableRow";

export class DiscountTable extends Component {

  constructor(props) {
    super(props);
    this.handleDiscountChange = this.handleDiscountChange.bind(this);
  }

  handleDiscountChange({ target }) {
    const { discountChange } = this.props;
    const fuelKey = target.getAttribute("data-key-fuel");
    const brand = target.getAttribute("data-brand-key");
    const discountValue = Number.parseFloat(target.value);
    discountChange({ brand, discountValue, fuelKey })
  }

  render() {
    const { discounts } = this.props;
    return (
      <Table dark responsive hover size="sm" className="discounts-edit-table">
        <thead>
        <tr>
          <th>Верига бензиностанции</th>
          {fuelTypes.map(ft => (<th key={ft.key}>{ft.name}</th>))}
        </tr>
        </thead>
        <tbody>
        {discounts.map(discount => <DiscountTableRow{...discount} key={discount.brand}
                                                    onDiscountChange={this.handleDiscountChange}/>)}
        </tbody>
      </Table>
    )
  }
}
