import React, { Component } from 'react';
import { Map, TileLayer } from "react-leaflet";
import { getUserLogs } from "../model/service/BackendService";
import { LocationMarker } from "./LocationMarker";

let locationMarkerKey = 1;

export class AdminPanel extends Component {
  state = {
    initialPosition: [42.657382399999996, 25.370956800000002],
    zoom: 7.5,
    userLogs: []
  };

  async componentDidMount() {
    this.setState({ userLogs: await getUserLogs() });
  }

  render() {
    const { userLogs, initialPosition, zoom } = this.state;
    return (
      <Map id="map-container" center={initialPosition} zoom={zoom}>
        <TileLayer
          attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        {userLogs.map(userLog => <LocationMarker {...userLog} key={locationMarkerKey++}/>)}
      </Map>);
  }
}
