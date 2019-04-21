import React from 'react';
import { PetrolStationListRow } from "./PetrolStationListRow";
import { Table } from "reactstrap";

export function PetrolStationList({ petrolStations }) {
  return (
    <Table dark>
      <thead>
      <tr>
        <th>Марка</th>
        <th>Име</th>
        <th>Разстояние при шофиране</th>
        <th>Време за път</th>
        <th>Цена</th>
        <th>Цена на пътя</th>
        <th>Цена на зареждането</th>
      </tr>
      </thead>
      <tbody>
      {petrolStations.map(petrolStation => <PetrolStationListRow key={petrolStation.id} {...petrolStation}/>)}
      </tbody>
    </Table>
  )
}
