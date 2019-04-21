import React from 'react';
import { PetrolStationListRow } from "./PetrolStationListRow";
import { Table } from "reactstrap";

export function PetrolStationList({ petrolStations }) {
  return (
    <Table dark responsive hover size="sm" className="petrol-station-list">
      <thead>
      <tr>
        <th colSpan="2">Бензиностанция</th>
        <th colSpan="2">Информация за пътуването</th>
        <th colSpan="3">Цена в лева за</th>
      </tr>
      <tr>
        <th>марка</th>
        <th>име</th>
        <th>разстояние</th>
        <th>време</th>
        <th>литър</th>
        <th>пътя</th>
        <th>зареждането</th>
      </tr>
      </thead>
      <tbody>
      {petrolStations.map(petrolStation => <PetrolStationListRow key={petrolStation.id} {...petrolStation}/>)}
      </tbody>
    </Table>
  )
}
