import React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';

function AddItem(props){
    const [open, setOpen] = React.useState(false);
    const [todo, setTodo] = React.useState({
        to_do: '', 
        reason:''
    });
  
  const handleOpen = () => {
    setOpen(true);
  }
    
  const handleClose = () => {
    setOpen(false);
  }
  const handleChange = (e) => {
    setTodo({...todo, [e.target.name]: e.target.value})
  }
  const addItem = () => {
    props.addItem(todo);
    setTodo({to_do: '', reason: ''});
    handleClose();
  }    

  return(
    <div>
      <Button onClick={handleOpen}>
        Add Item
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>New Item</DialogTitle>
        <DialogContent>
          <TextField value={todo.to_do} margin="dense"
            onChange={handleChange} name="to_do" label="To_do" fullWidth />
          <TextField value={todo.reason} margin="dense"
            onChange={handleChange} name="reason" label="Reason" fullWidth />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}> 
            Cancel
          </Button>
          <Button onClick={addItem}> 
            Add
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}

export default AddItem;