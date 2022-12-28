import './InputCard.css';
import React from 'react';
export default class InputCard extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.sendRequest = this.sendRequest.bind(this);
        this.changedInput1 = this.changedInput1.bind(this);
        this.changedInput2 = this.changedInput2.bind(this);
        const { pos1, pos2 } = this.props;
        this.state = {
            pos1: `${pos1.lat},${pos1.lng}`,
            pos2: `${pos2.lat},${pos2.lng}`
        }
    }
    componentDidMount() {
        const { pos1, pos2 } = this.props;
        this.setState({
            pos1: `${pos1.lat},${pos1.lng}`,
            pos2: `${pos2.lat},${pos2.lng}`
        });
      }
    
      componentDidUpdate(prevProps) {
        const { pos1, pos2 } = this.props;
        if (pos1 !== prevProps.pos1 || pos2 !== prevProps.pos2) {
          this.setState({
            pos1: `${pos1.lat},${pos1.lng}`,
            pos2: `${pos2.lat},${pos2.lng}`
        });
        }
    }
    
      handleSubmit(e) {
        e.preventDefault();
        let data = new FormData(document.getElementById('form')).entries();
        var strarr = [];
        for (const entry of data){
            strarr.push(entry[0]+"="+entry[1]);
        }
        var str = strarr.join("&");
        this.sendRequest(str);
    }

     sendRequest (str)  {
        fetch('https://api.placely.tech/api/home/userPreferences?'+str, {mode:'cors'})
            .then((response) => response.json())
            .then((data) => {
                this.props.onGetResults({"res" : data});
                // console.log(data);
            });

    }

    changedInput1(e){
        var str = e.target.value;
        var arr = str.split(",");
        this.props.updateLocation1(arr[0], arr[1]);
    // console.log(arr);
    }

    changedInput2(e){
        var str = e.target.value;
        var arr = str.split(",");
        this.props.updateLocation2(arr[0], arr[1]);
    // console.log(arr);
    }

    
    render(){
    return (
        <div className='card container'>
            <form id='form'>
                <div className='mb-3'>
                    <label for='city' className='form-label'>City</label>
                    <input type='text' id='city' name='city' placeholder='City' className='form-control'></input>
                </div>
                <div className='mb-3'>
                    <h2 className='h2'>User 1</h2>
                    <label for='select1' className='form-label'>prefered categories</label>
                    <select multiple name='select1' id='select1' className='form-select'>
                        <option value="catering.restaurant">Restaurant</option>
                        <option value="internet_access">Internet access</option>
                        <option value="shopping_mall">Shopping Mall</option>
                        <option value="entertainment">Entertainment</option>
                        <option value="tourism.attraction">Tourism Attraction</option>
                        <option value="wheelchair">Wheelchair Friendly</option>
                        <option value="catering.restaurant.italian">Italian Restaurant</option>
                        <option value="catering.restaurant.mexican">Mexican Restaurant</option>
                        <option value="catering.restaurant.chinese">Chinese Restaurant</option>
                        <option value="catering.restaurant.thai">Thai Restaurant</option>
                        <option value="catering.restaurant.barbecue">Barbecue</option>
                        <option value="vegan">Vegan friendly</option>
                        <option value="vegetarian">Vegetarian</option>
                        <option value="catering.cafe">Cafe</option>
                    </select>
                    <label for='location1' className='form-label'>Location</label>
                    <input type='text' id='location1' name='location1' placeholder='41.989392, 21.452028' className='form-control' onChange={this.changedInput1} value={this.state.pos1} disabled></input>
                </div>

                <div className='mb-3'>
                    <h2 className='h2'>User 2</h2>


                    <label for='select1' className='form-label'>prefered categories</label>
                    <select multiple name='select2' id='select2' className='form-select'>
                        <option value="catering.restaurant">Restaurant</option>
                        <option value="internet_access">Internet access</option>
                        <option value="shopping_mall">Shopping Mall</option>
                        <option value="entertainment">Entertainment</option>
                        <option value="tourism.attraction">Tourism Attraction</option>
                        <option value="wheelchair">Wheelchair Friendly</option>
                        <option value="catering.restaurant.italian">Italian Restaurant</option>
                        <option value="catering.restaurant.mexican">Mexican Restaurant</option>
                        <option value="catering.restaurant.chinese">Chinese Restaurant</option>
                        <option value="catering.restaurant.thai">Thai Restaurant</option>
                        <option value="catering.restaurant.barbecue">Barbecue</option>
                        <option value="vegan">Vegan friendly</option>
                        <option value="vegetarian">Vegetarian</option>
                        <option value="catering.cafe">Cafe</option>
                    </select>
                    <label for='location2' className='form-label'>Location</label>
                    <input type='text' id='location2' name='location2' placeholder='41.989392, 21.452028' className='form-control' onChange={this.changedInput2} value={this.state.pos2} disabled></input>
                </div>

                {/* <input type='submit' value='Submit' className='btn btn-success'></input> */}
                <button className='btn btn-success' onClick={this.handleSubmit}>Submit</button>
            </form>
        </div>
    )
    }
}