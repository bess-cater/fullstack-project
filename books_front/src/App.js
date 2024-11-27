import './App.css';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { createTheme, alpha, getContrastRatio } from '@mui/material/styles';
import Booklist from './components/booklist';
function App() {
  
  const violetBase = '#CB9DF0';
  const violetMain = alpha(violetBase, 0.7);
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
    <div className="App">
      <AppBar position="static" sx={{ backgroundColor: theme.palette.spring.major }}>
        <Toolbar>
          <Typography variant="h6">
            My books
          </Typography>
        </Toolbar>
      </AppBar>
      <Booklist />
    </div>
  );
}

export default App;
