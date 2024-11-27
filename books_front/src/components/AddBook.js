import React, { useState, useEffect } from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";

import { SERVER_URL } from "../constants";

function AddBook(props) {
  const [open, setOpen] = useState(false);
  const [book, setBook] = useState({
    title: "",
    genre: "",
    pubYear: "",
    rating: "",
    author: null, // Store the full author object here
  });
//   const [authorMode, setAuthorMode] = useState("select"); // toggle between 'select' or 'add'
//   const [authorDetails, setAuthorDetails] = useState({
//     firstname: "",
//     lastname: "",
//     birthYear: "",
//   });

  // Open the dialog and fetch authors from the server
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  // Fetch authors from the server
  // Handle book input changes
  const handleChange = (event) => {
    setBook({ ...book, [event.target.name]: event.target.value });
  };

  const handleSave = () => {
    props.addBook(book);
    handleClose();
  }

  // Handle author selection (for existing authors)
//   const handleAuthorSelection = (event) => {
//     const selectedValue = event.target.value;

//     if (selectedValue === "new") {
//       setAuthorMode("add");
//     } else {
//       setAuthorMode("select");
//       const selectedAuthorUrl = selectedValue;
//       fetch(selectedAuthorUrl)
//         .then((response) => response.json())
//         .then((data) => {
//           setBook({ ...book, author: data }); // Set the full author object
//         })
//         .catch((error) => console.error("Error fetching author:", error));
//     }
//   };

  // Handle new author input changes
//   const handleAuthorChange = (event) => {
//     setAuthorDetails({ ...authorDetails, [event.target.name]: event.target.value });
//   };

  // Add new author to the server and set the new author to the book
//   const addNewAuthor = async () => {
//     try {
//       const response = await fetch(SERVER_URL + "liza/authors", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(authorDetails),
//       });

//       const newAuthor = await response.json();
//       return newAuthor; // Return the full author object
//     } catch (error) {
//       console.error("Error adding author:", error);
//       return null;
//     }
//   };

  // Submit the book data
//   const handleSubmit = async () => {
//     try {
//       let authorData = book.author;

//       // If we're adding a new author, we need to create the author first
//       if (authorMode === "add") {
//         authorData = await addNewAuthor();
//         if (!authorData) {
//           throw new Error("Failed to add new author");
//         }
//       }

//       const bookData = {
//         ...book,
//         pubYear: parseInt(book.pubYear, 10), // Ensure pubYear is an integer
//         rating: parseFloat(book.rating), // Ensure rating is a float
//         author: authorData, // Add the full author object
//       };

//       console.log(bookData);

//       // Submit the book data to the server
//       const response = await fetch(SERVER_URL + "liza/books", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(bookData),
//       });

//       if (!response.ok) {
//         throw new Error("Failed to create book");
//       }

//       console.log("Book successfully added");
//       handleClose();
//     } catch (error) {
//       console.error("Error adding book:", error);
//     }
//   };


return(
    <div>
      <button onClick={handleClickOpen}>Add new book!</button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>New book</DialogTitle>
        <DialogContent>
          <input placeholder="title" name="title" 
            value={book.title} onChange={handleChange}/><br/> 
          <input placeholder="genre" name="genre" 
            value={book.genre} onChange={handleChange}/><br/>
          <input placeholder="pubYear" name="pubYear" 
            value={book.pubYear} onChange={handleChange}/><br/>
          <input placeholder="rating" name="rating" 
             value={book.rating} onChange={handleChange}/><br/>
        </DialogContent>
        <DialogActions>
           <button onClick={handleClose}>Cancel</button>
           <button onClick={handleSave}>Save</button>
        </DialogActions>
      </Dialog>            
    </div>
  );  
}

export default AddBook;