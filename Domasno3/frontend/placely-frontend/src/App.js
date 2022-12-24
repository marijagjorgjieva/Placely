import React from 'react';
import logo from './logo.svg';
import './App.css';
import MapComponent from './MapComponent.js'
import InputCard from './InputCard.js'

class App extends React.Component {



  constructor(props) {  
    super(props);
    this.state = { results : {} };
    this.handleResults = this.handleResults.bind(this);
    this.render = this.render.bind(this);
  }

  // state = { results : new Array() }

     handleResults (r) {
        this.setState({results: r});
        
    }

 
  render() {
    return (
      <div className="App">
        <header className="app-header">
          <h1 className='logo'>Placely</h1>
        </header>
        <MapComponent results = {this.state.results}/>
        <InputCard onGetResults = {this.handleResults}/>
      </div>
    );
  }
}

export default App;
