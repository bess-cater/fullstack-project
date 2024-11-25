import './App.css';
import React, { useState } from 'react';
import { AgGridReact} from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-material.css';

function App() {
  const [search, setSearch] = useState('');
  const [desc, setDesc] = useState([]);


  const fetchData = () => {
    const API_KEY = '';
    fetch(`https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=${search}&key=${API_KEY}`)
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
  const columns = [
    {field: 'snippet.thumbnails.high.url', headerName: "Thumbnail",  cellRenderer: params => {return <img style={{width: 170}} src={params.value}/>}, width: 200},
    {field: 'snippet.title', headerName: "Video title", width: 900, cellRenderer: params => {const videoId = params.data.id?.videoId; return <a href={`https://www.youtube.com/watch?v=${videoId}`}>{params.value}</a>}, sortable: true, filter: true}
  ];



  return (
    <div className="App">
      <input value={search} 
        onChange={e => setSearch(e.target.value)} />
      <button onClick={fetchData}>Fetch</button>
      <div className="ag-theme-material" style={{height: 1000, width: '100%'}}>
        <AgGridReact
        rowData={desc}
        columnDefs={columns}
        rowHeight={200}
        pagination={true}
        paginationPageSize={5}/>
      </div>
      {/* <ul className="list">
          {  desc.map(repo => 
              <li className={`video`} key={repo.id.videoId}>
                <img src={repo.snippet.thumbnails.high.url} alt="Weather icon" />
                <div className={`desc`}>  {repo.snippet.title}</div>
              </li>  
            )
          }
      </ul> */}

    </div>
  );
}

export default App;
