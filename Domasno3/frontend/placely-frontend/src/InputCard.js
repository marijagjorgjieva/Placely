import './InputCard.css';
import React from 'react';
export default class InputCard extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.sendRequest = this.sendRequest.bind(this);
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
        fetch('http://localhost:9090/api/home/userPreferences?'+str, {mode:'cors'})
            .then((response) => response.json())
            .then((data) => {
                this.props.onGetResults({"res" : data});
                // console.log(data);
            });

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
                    <label for='select1' className='form-label'>preferences</label>
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
                    <input type='text' id='location1' name='location1' placeholder='Location' className='form-control'></input>
                </div>

                <div className='mb-3'>
                    <h2 className='h2'>User 2</h2>


                    <label for='select1' className='form-label'>preferences</label>
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
                    <input type='text' id='location2' name='location2' placeholder='Location' className='form-control'></input>
                </div>

                {/* <input type='submit' value='Submit' className='btn btn-success'></input> */}
                <button className='btn btn-success' onClick={this.handleSubmit}>Submit</button>
            </form>
        </div>
    )
    }
}