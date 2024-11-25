import React, { useState } from 'react';
import Container from '@mui/material/Container';
import './App.css';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { createTheme, alpha, getContrastRatio } from '@mui/material/styles';
import AddItem from './AddItem';
import Stack from '@mui/material/Stack';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';

function App() {
  const violetBase = '#CB9DF0';
  const violetMain = alpha(violetBase, 0.7);

  const [todos, setTodos] = useState([]);

  const addTodo = (todo) => {
    setTodos([todo, ...todos]);
  }

const theme = createTheme({
  palette: {
    spring: {
      major: violetBase,
      light: '#F0C1E1',
      warm: '#FDDBBB',
      contrastText: getContrastRatio(violetMain, '#fff') > 4.5 ? '#fff' : '#111',
    },
  },
});
  return (
    <Container>
      <AppBar position="sticky" sx={{ backgroundColor: theme.palette.spring.warm }}>
        <Toolbar>
          <Typography variant="h4" color='black'>
            My Bucket list
          </Typography>
        </Toolbar>
      </AppBar>
      <Stack alignItems="center">
      <AddItem addItem={addTodo} />
      <List>
        {
          todos.map((todo, index) => 
            <ListItem key={index} divider>
              <ListItemText primary={todo.to_do} secondary={todo.reason}/>
            </ListItem>
          )
        }
        </List>
      </Stack>
    </Container>
  );
}

export default App;
