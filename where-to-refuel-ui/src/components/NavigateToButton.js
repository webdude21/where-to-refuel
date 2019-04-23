import React from "react";
import { getNavigationLink } from "../model/service/LocationService";

export function NavigateToButton({ latitude, longitude }) {
  return (
    <a href={getNavigationLink({ latitude, longitude })} className="btn" target="_blank" rel="noopener noreferrer">
      <img src={"googlemaps-16x16.png"} alt="Google Maps"/>
    </a>
  );
}
