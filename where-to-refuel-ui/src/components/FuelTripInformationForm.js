import React, { Component } from 'react';
import { Col, Form, Input, InputGroup, InputGroupAddon, Row } from "reactstrap";
import fuelTypes from "../model/FuelTypes";

export class FuelTripInformationForm extends Component {
  constructor(props) {
    super(props);
    this.handleConsumptionChange = this.handleConsumptionChange.bind(this);
    this.handleFuelAmountChange = this.handleFuelAmountChange.bind(this);
    this.handleSelectedFuelChange = this.handleSelectedFuelChange.bind(this);
  }

  handleFuelAmountChange({ target: { value } }) {
    const { onFormDataChange } = this.props;
    onFormDataChange({ fuelAmount: Number.parseFloat(value) });
  }

  handleConsumptionChange({ target: { value } }) {
    const { onFormDataChange } = this.props;
    onFormDataChange({ fuelConsumption: Number.parseFloat(value) });
  }

  handleSelectedFuelChange({ target: { value } }) {
    const { onFormDataChange } = this.props;
    onFormDataChange({ selectedFuel: value });
  }

  render() {
    const { fuelAmount, fuelConsumption } = this.props;

    return (
      <Form>
        <Row form>
          <Col md={5}>
            <InputGroup>
              <InputGroupAddon addonType="append">Разход за 100 км в литри</InputGroupAddon>
              <Input min={0} max={100} type="number" step="0.5" value={fuelConsumption}
                     onChange={this.handleConsumptionChange}/>
            </InputGroup>
          </Col>
          <Col md={5}>
            <InputGroup>
              <InputGroupAddon addonType="append">Литри гориво които ще заредите</InputGroupAddon>
              <Input min={0} max={100} type="number" step="1" value={fuelAmount}
                     onChange={this.handleFuelAmountChange}/>
            </InputGroup>
          </Col>
          <Col md={2}>
            <Input type="select" name="select" onChange={this.handleSelectedFuelChange}>
              {fuelTypes.map(fuelType => <option value={fuelType.key} key={fuelType.key}>{fuelType.name}</option>)}
            </Input>
          </Col>
        </Row>
      </Form>
    );
  }
}
