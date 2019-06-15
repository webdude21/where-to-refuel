import { Marker, Popup } from "react-leaflet";
import React from "react";

export function LocationMarker({ latitude, longitude, requestedOn, fuelType }) {
  const position = [latitude, longitude];

  return (
    <Marker position={position}>
      <Popup>{`Requested on: ${requestedOn}, fuelType: ${fuelType}`}</Popup>
    </Marker>
  )
}
