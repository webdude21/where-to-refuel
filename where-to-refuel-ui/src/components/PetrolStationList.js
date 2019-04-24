import React, { Component } from 'react';
import { PetrolStationListRow } from "./PetrolStationListRow";
import { Table } from "reactstrap";

export class PetrolStationList extends Component {

  constructor(props) {
    super(props);
    this.handleTableHeaderClick = this.handleTableHeaderClick.bind(this);
  }

  handleTableHeaderClick(e) {
    const { onSortKeyChanged } = this.props;
    const dataKey = e.target.getAttribute("data-sort");
    onSortKeyChanged(dataKey);
  }

  render() {
    const { petrolStations } = this.props;

    return (
      <Table dark responsive hover size="sm" className="petrol-station-list">
        <thead>
        <tr>
          <th colSpan="3">Бензиностанция</th>
          <th colSpan="2">Информация за пътуването</th>
          <th colSpan="3">Цена за</th>
        </tr>
        <tr onClick={this.handleTableHeaderClick}>
          <th data-sort="brand.name">марка</th>
          <th data-sort="name">име</th>
          <th>навигация</th>
          <th data-sort="drivingInfo.distance">разстояние</th>
          <th data-sort="drivingInfo.duration">време</th>
          <th data-sort="priceInformation.price">литър</th>
          <th data-sort="tripCost">пътя</th>
          <th data-sort="totalCost">всичко</th>
        </tr>
        </thead>
        <tbody>
        {petrolStations.map(petrolStation => <PetrolStationListRow key={petrolStation.id} {...petrolStation}/>)}
        </tbody>
      </Table>
    )
  }
}
