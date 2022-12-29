import React from 'react';
import logo from './logo.svg';
import './App.css';
import MapComponent from './MapComponent.js'
import InputCard from './InputCard.js'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class App extends React.Component {



  constructor(props) {  
    super(props);
    this.state = { 
      results : {},
      pos1: {lat: 42.00115292566058, lng: 21.4174201057616},
      pos2: {lat: 41.996, lng: 21.4311},
    };
    this.handleResults = this.handleResults.bind(this);
    this.updateLocation1 = this.updateLocation1.bind(this);
    this.updateLocation2 = this.updateLocation2.bind(this);
    this.makeErrorToast = this.makeErrorToast.bind(this);
    this.render = this.render.bind(this);
  }

  // state = { results : new Array() }

     handleResults (r) {
        this.setState({results: r});
        
    }

    updateLocation1 = (lat, lng) => {
        this.setState({pos1: {lat: lat, lng: lng}})
    }

    updateLocation2 = (lat, lng) => {
        this.setState({pos2: {lat: lat, lng: lng}})
    }

    makeErrorToast = (message) => {
      toast.error(message)
    }

 
  render() {
    return (
      <div className="App">
        <header className="app-header">
          <h1 className='logo'>Placely</h1>
        </header>
        <MapComponent results = {this.state.results} updateLocation1 = {this.updateLocation1} updateLocation2={this.updateLocation2} pos1={this.state.pos1} pos2={this.state.pos2}  />
        <InputCard onGetResults = {this.handleResults} 
                  updateLocation1 = {this.updateLocation1} 
                  updateLocation2={this.updateLocation2} 
                  pos1={this.state.pos1} 
                  pos2={this.state.pos2} 
                  errToast={this.makeErrorToast}/>
        <ToastContainer 
            position="top-center"
            autoClose={5000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="dark"
        />

      </div>
    );
  }
}

export default App;
