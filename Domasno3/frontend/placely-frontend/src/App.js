import logo from './logo.svg';
import './App.css';
import MapComponent from './MapComponent.js'
import InputCard from './InputCard.js'

function App() {
 
  return (
    <div className="App">
      <header className="app-header">
        <h1 className='logo'>Placely</h1>
      </header>
      <MapComponent/>
      <InputCard/>
    </div>
  );
}

export default App;
