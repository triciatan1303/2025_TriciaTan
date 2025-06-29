import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [target, setTarget] = useState('');
  const [denominations, setDenominations] = useState('');
  const [result, setResult] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const denomArray = denominations
      .split(',')
      .map(str => parseFloat(str.trim()))
      .filter(d => !isNaN(d));

    try {
      const response = await axios.post('http://20.123.88.146:8081/api/coin-change', {
        targetAmount: parseFloat(target),
        denominations: denomArray
      });

      setResult(response.data.coins);
    } catch (error) {
      console.error(error);
      alert('Error calculating coin change.');
    }
  };

  return (
    <div className="App">
      <h1>Coin Change Calculator</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Target Amount: </label>
          <input
            type="number"
            step="0.01"
            value={target}
            onChange={e => setTarget(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Denominations (comma-separated): </label>
          <input
            type="text"
            value={denominations}
            onChange={e => setDenominations(e.target.value)}
            required
          />
        </div>
        <button type="submit">Calculate</button>
      </form>

      {result.length > 0 && (
        <div>
          <h3>Result</h3>
          <ul>
            {result.map((coin, index) => (
              <li key={index}>{coin.toFixed(2)}</li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default App;
