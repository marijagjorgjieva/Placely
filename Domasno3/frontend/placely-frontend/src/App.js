import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';
import MapComponent from './MapComponent.js'
import InputCard from './InputCard.js'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function App()  {
  const [results, setResults] = useState({});
  const [pos1, setPos1] = useState({lat: 42.00115292566058, lng: 21.4174201057616});
  const [pos2, setPos2] = useState({lat: 41.996, lng: 21.4311});

  const handleResults = (r) => {
    setResults(r);
  };

  const updateLocation1 = (lat, lng) => {
    setPos1({lat: lat, lng: lng});
  };

  const updateLocation2 = (lat, lng) => {
    setPos2({lat: lat, lng: lng});
  };

  const makeErrorToast = (message) => {
    toast.error(message);
  };

  return (
    <div className="App">
      <header className="app-header">
        <h1 className='logo'>Placely</h1>
      </header>
      <MapComponent results={results} updateLocation1={updateLocation1} updateLocation2={updateLocation2} pos1={pos1} pos2={pos2} />
      <InputCard onGetResults={handleResults} updateLocation1={updateLocation1} updateLocation2={updateLocation2} pos1={pos1} pos2={pos2} errToast={makeErrorToast} />
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
};
