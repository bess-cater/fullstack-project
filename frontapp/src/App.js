import './App.css';
import React, { useState } from 'react'

function App() {
  const [search, setSearch] = useState('');
  const [desc, setDesc] = useState([]);

  const fetchData = () => {
    const API_KEY = 'AIzaSyBvmvBf9scW6TAEMaBjccoHMIEhCs7DgiA';
    fetch(`https://www.googleapis.com/youtube/v3/search?part=snippet&q=${search}&key=${API_KEY}`)
    .then(response => response.json())
    .then(desc => {
      console.log(desc);
      if (desc.items) {
        setDesc(desc.items);  // Set the state with the items array
      } else {
        console.log("No items found in the response.");
      }
    })
    .catch(err => console.error(err))
  }


  return (
    <div className="App">
      <input value={search} 
        onChange={e => setSearch(e.target.value)} />
      <button onClick={fetchData}>Fetch</button>
      <ul className="list">
          {  desc.map(repo => 
              <li className={`video`} key={repo.id.videoId}>
                <img src={repo.snippet.thumbnails.high.url} alt="Weather icon" />
                <div className={`desc`}>  {repo.snippet.title}</div>
              </li>  
            )
          }
      </ul>

    </div>
  );
}

export default App;
