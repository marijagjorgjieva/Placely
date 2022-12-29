import 'leaflet/dist/leaflet.css';
import './MapComponent.css'
import React, { useRef, useState, useMemo } from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMap, ZoomControl } from 'react-leaflet';
import L from 'leaflet';

const icon = L.icon({ iconUrl: "/marker-icon.png" });
const userIcon = L.icon({ iconUrl: "/marker-user-icon.png" });


function MyComponent(props) {
    const map = useMap()
    if (props.lat !== undefined && props.lon !== undefined)
        map.setView([props.lat,props.lon], 14)
    return null
  }

  function DraggableMarker(props) {
    const [position, setPosition] = useState(props.position)
    const markerRef = useRef(null)
    const eventHandlers = useMemo(
        () => ({
            dragend() {
            const marker = markerRef.current
            if (marker != null) {
            setPosition(marker.getLatLng())
            props.updatePosition(marker.getLatLng().lat, marker.getLatLng().lng)
        }
      },
    }),
    [],
  )

    return (
        <Marker
            draggable={true}
            eventHandlers={eventHandlers}
            position={position}
            ref={markerRef}
            icon = {userIcon}
            autoPan = {true}
            ></Marker>
    )
  }

class MapComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { res: [] };
    }

    

    componentDidMount() {
        const { results } = this.props;
        this.setState({ res: results.res });
      }
    
      componentDidUpdate(prevProps) {
        const { results } = this.props;
        if (results !== prevProps.results) {
          this.setState({ res: results.res });
        }
        // var map = useMap();
        // const res  = this.state.res !== undefined ? this.state.res : [];
        // if (res.length > 0) {
        //     map.panTo([res[0].location.latitude, res[0].location.longitude])
        // }
    }
    render() {
        const { res = [] } = this.state;
        const first = res.length > 0 ? res[0] : {location: {latitude: 41.996, longitude: 21.4310}};
    
        return (
            
            <div className='map'>
            <MapContainer center={[41.996, 21.4310]} zoom={14} scrollWheelZoom={true} zoomControl={false}>

                <ZoomControl position="bottomright"  />
                <MyComponent lat={first.location.latitude} lon={first.location.longitude}/>

                
                <DraggableMarker
                    position={this.props.pos1}
                    updatePosition = {this.props.updateLocation1}
                >
                    <Popup>Location 1</Popup>
                </DraggableMarker>
                <DraggableMarker
                    position={this.props.pos2}
                    updatePosition = {this.props.updateLocation2}
                >
                    <Popup>Location 2</Popup>
                </DraggableMarker>

                <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
                {
                    
                    res.map((result) => {
                        return (
                            <Marker position={[result.location.latitude, result.location.longitude]} icon={icon}>
                                <Popup>
                                    {result.name} <br /> {result.street}
                                </Popup>
                            </Marker>
                        )
                    })
                
                } 
                
            </MapContainer>
            </div>
        )
    }
    
}
export default MapComponent;