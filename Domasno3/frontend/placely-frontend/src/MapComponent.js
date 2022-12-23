import 'leaflet/dist/leaflet.css';
import './MapComponent.css'
import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet';

export default function MapComponent() {
   
    return (
        <div className='map'>
        <MapContainer center={[41.996, 21.4310]} zoom={14} scrollWheelZoom={false}>
            <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
            <Marker position={[51.505, -0.09]}>
            <Popup>
              A pretty CSS3 popup. <br /> Easily customizable.
            </Popup>
            </Marker>
        </MapContainer>
        </div>
    )
    
}