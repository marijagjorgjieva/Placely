import 'leaflet/dist/leaflet.css';
import './MapComponent.css'
import React, { useRef, useState, useEffect, useMemo } from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMap, ZoomControl } from 'react-leaflet';
import L from 'leaflet';

const icon = L.icon({ iconUrl: "/marker-icon.png" });
const userIcon = L.icon({ iconUrl: "/marker-user-icon.png" });


function MapView({ lat, lon }) {
    const map = useMap()
    if (lat !== undefined && lon !== undefined)
        map.setView([lat, lon], 14)
    return null
}

function DraggableMarker({ position, updatePosition }) {
    const [positionState, setPosition] = useState(position)
    const markerRef = useRef(null)
    const eventHandlers = useMemo(
        () => ({
            dragend() {
                const marker = markerRef.current
                if (marker != null) {
                    setPosition(marker.getLatLng())
                    updatePosition(marker.getLatLng().lat, marker.getLatLng().lng)
                }
            },
        }),
        [],
    )

    return (
        <Marker
            draggable={true}
            eventHandlers={eventHandlers}
            position={positionState}
            ref={markerRef}
            icon={userIcon}
            autoPan={true}
        ></Marker>
    )
}

export default function MapComponent({ results, updateLocation1, updateLocation2, pos1, pos2 }) {
    const { res } = results;
    const [resState, setRes] = useState(res);

    useEffect(() => {
        if (results) {
            setRes(results.res);
        }
    }, [results]);

    const first = (resState != undefined && resState.length > 0) ? resState[0] : { location: { latitude: 41.996, longitude: 21.4310 } };

    return (
        <div className='map'>
            <MapContainer center={[41.996, 21.4310]} zoom={14} scrollWheelZoom={true} zoomControl={false}>
                <ZoomControl position="bottomright" />
                <MapView lat={first.location.latitude} lon={first.location.longitude} />
                <DraggableMarker
                    position={pos1}
                    updatePosition={updateLocation1}
                >
                    <Popup>Location 1</Popup>
                </DraggableMarker>
                <DraggableMarker
                    position={pos2}
                    updatePosition={updateLocation2}
                >
                    <Popup>Location 2</Popup>
                </DraggableMarker>
                <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
                
                {
                    resState != undefined ? resState.map((result) => {
                        return (
                            <Marker position={[result.location.latitude, result.location.longitude]} icon={icon}>
                                <Popup>
                                    {result.name} <br /> {result.street}
                                </Popup>
                            </Marker>
                        )
                    })
                        : null
                }
            </MapContainer>
        </div>
    )
}