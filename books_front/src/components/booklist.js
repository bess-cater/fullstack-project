import React, { useEffect, useState} from 'react';
import { SERVER_URL } from '../constants';
import { DataGrid } from '@mui/x-data-grid';
import { Snackbar } from '@mui/material';
import AddBook from './AddBook';

function Booklist(){
    // REST API에서 가져온 데이터 저장할 상태!
    const [books, setBooks ] = useState([]);
    const [open, setOpen] = useState(false);
    useEffect(()  => {
        fetchBooks();        
    }, []);

    const fetchBooks = ()=> {
        fetch(SERVER_URL + 'liza/books')
        .then(response => response.json())
    .then(data => setBooks(data._embedded.books))
    .catch(err => console.error(err));    
  }

    const onDelClick = (url) => {
        fetch(url, {method: 'DELETE'})
        .then(response => 
            { if (response.ok) {
                fetchBooks();
                setOpen(true);}
             else {alert('Could not delete!')}
            })
        .catch(err => console.error(err))
    }

    const addBook = (car) => {
        fetch(SERVER_URL  +  'liza/books',
          { method: 'POST', headers: {
            'Content-Type':'application/json',
          },
          body: JSON.stringify(car)
        })
        .then(response => {
          if (response.ok) {
            fetchBooks();
          }
          else {
            alert('Something went wrong!');
          }
        })
        .catch(err => console.error(err))
      }


    
    const columns = [
        {field: 'title', headerName: 'Title', width: 200},
        {field: 'genre', headerName: 'Genre', width: 200},
        {field: 'pubYear', headerName: 'Published in', width: 200},
        {field: 'rating', headerName: 'Rating', width: 150},
        // {field: 'author',headerName: 'Author', width: 200},
        {
            field: '_links.self.href',
            headerName: '',
            sortable: false,
            filterable: false,
            renderCell: row =>
                <button  style={{ borderRadius: '12px' }} onClick={() => onDelClick(row.id)}>Delete</button>
        }
            
    
    ];
    
    return(
        <React.Fragment>
            <AddBook addBook={addBook} />
            <div style={{ height: 500, width: '100%'}}>
            <DataGrid
            rows={books}
            columns={columns}
            getRowId={row => row._links.self.href}/>
            <Snackbar 
            open={open}
            autoHideDuration={2000}
            onClose={() => setOpen(false)}
            message="Book deleted :("></Snackbar>
            </div>

        </React.Fragment>
        
           
        
    );
}

export default Booklist;