import React, { Component } from 'react';
import { Col, Form, Input, InputGroup, InputGroupAddon, InputGroupText, Row } from "reactstrap";
import fuelTypes from "../model/FuelTypes";

export class FuelTripInformationForm extends Component {
  constructor(props) {
    super(props);
    this.handleConsumptionChange = this.handleConsumptionChange.bind(this);
    this.handleFuelAmountChange = this.handleFuelAmountChange.bind(this);
    this.handleSelectedFuelChange = this.handleSelectedFuelChange.bind(this);
    this.handleTripMode = this.handleTripMode.bind(this);
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

  handleTripMode({ target: { checked } }) {
    const { onFormDataChange } = this.props;
    onFormDataChange({ twoWayTrip: checked });
  }

  render() {
    const { fuelAmount, fuelConsumption, selectedFuel, twoWayTrip } = this.props;

    return (
      <Form>
        <Row form>
          <Col lg={3} md={12}>
            <InputGroup>
              <InputGroupAddon addonType="append">Разход за 100 км</InputGroupAddon>
              <Input min={1} max={100} type="number" step={0.5} value={fuelConsumption}
                     onChange={this.handleConsumptionChange}/>
            </InputGroup>
          </Col>
          <Col lg={3} md={12}>
            <InputGroup>
              <InputGroupAddon addonType="append">Количество гориво</InputGroupAddon>
              <Input min={1} max={100} type="number" step={1} value={fuelAmount}
                     onChange={this.handleFuelAmountChange}/>
            </InputGroup>
          </Col>
          <Col lg={4} md={12}>
            <InputGroup>
              <InputGroupText>
                <Input addon type="checkbox" checked={twoWayTrip} onChange={this.handleTripMode}/>
              </InputGroupText>
              <Input value="Пътуване в двете посоки" disabled/>
            </InputGroup>
          </Col>
          <Col lg={2} md={12}>
            <Input value={selectedFuel} type="select" name="select" onChange={this.handleSelectedFuelChange}>
              {fuelTypes.map(fuelType => <option value={fuelType.key} key={fuelType.key}>{fuelType.name}</option>)}
            </Input>
          </Col>
        </Row>
      </Form>
    );
  }
}
