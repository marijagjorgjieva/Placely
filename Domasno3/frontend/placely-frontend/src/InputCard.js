import React, { useState, useEffect } from 'react';
import './InputCard.css';
import { sendRequest } from './lib/input';

export default function InputCard ({ pos1, pos2, updateLocation1, updateLocation2, errToast, onGetResults })  {
  const [position1, setPosition1] = useState(`${pos1.lat},${pos1.lng}`);
  const [position2, setPosition2] = useState(`${pos2.lat},${pos2.lng}`);

  useEffect(() => {
    setPosition1(`${pos1.lat},${pos1.lng}`);
    setPosition2(`${pos2.lat},${pos2.lng}`);
  }, [pos1, pos2]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    let data = new FormData(document.getElementById('form')).entries();
    console.log(data);
    var strarr = [];
    for (const entry of data) {
      if (entry[1] === '') {
        errToast('Please fill all fields');
        return;
      }
      strarr.push(entry[0] + '=' + entry[1]);
    }
    var str = strarr.join('&');
    console.log(str);
    const results = await sendRequest(str);
    onGetResults(results);
  };

  const handleInput1 = (e) => {
    var str = e.target.value;
    var arr = str.split(',');
    updateLocation1(arr[0], arr[1]);
  };

  const handleInput2 = (e) => {
    var str = e.target.value;
    var arr = str.split(',');
    updateLocation2(arr[0], arr[1]);
  };

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
                    <input type='text' id='location1' name='location1' placeholder='41.989392, 21.452028' className='form-control' onChange={handleInput1} value={position1} ></input>
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
                    <input type='text' id='location2' name='location2' placeholder='41.989392, 21.452028' className='form-control' onChange={handleInput2} value={position2} ></input>
                </div>

                {/* <input type='submit' value='Submit' className='btn btn-success'></input> */}
                <button className='btn btn-success' onClick={handleSubmit}>Submit</button>
            </form>
        </div>
    );
};
