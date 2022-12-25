import 'leaflet/dist/leaflet.css';
import './MapComponent.css'
import React from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet';
import L from 'leaflet';

const icon = L.icon({ iconUrl: "/marker-icon.png" });

// delete L.Icon.Default.prototype._getIconUrl;

// L.Icon.Default.mergeOptions({
//     iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png').default,
//     iconUrl: require('leaflet/dist/images/marker-icon.png').default,
//     shadowUrl: require('leaflet/dist/images/marker-shadow.png').default
// });

function MyComponent(props) {
    const map = useMap()
    if (props.lat !== undefined && props.lon !== undefined)
        map.setView([props.lat,props.lon], 14)
    return null
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
            <MapContainer center={[41.996, 21.4310]} zoom={14} scrollWheelZoom={true}>
                <MyComponent lat={first.location.latitude} lon={first.location.longitude}/>
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