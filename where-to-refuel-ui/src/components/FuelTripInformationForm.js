import React, { Component } from 'react';
import { Col, Form, Input, InputGroup, InputGroupAddon, Row } from "reactstrap";
import { FuelSelector } from "./FuelSelector";

export class FuelTripInformationForm extends Component {
  constructor(props) {
    super(props);
    this.handleConsumptionChange = this.handleConsumptionChange.bind(this);
    this.handleFuelAmountChange = this.handleFuelAmountChange.bind(this);
  }

  handleFuelAmountChange({ target: { value } }) {
    const { onFormDataChange } = this.props;
    onFormDataChange({ fuelAmount: Number.parseFloat(value) });
  }

  handleConsumptionChange({ target: { value } }) {
    const { onFormDataChange } = this.props;
    onFormDataChange({ fuelConsumption: Number.parseFloat(value) });
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
            <FuelSelector/>
          </Col>
        </Row>
      </Form>
    );
  }
}
