import './App.css';
import ChooseFloder from './pages/chooseFloder';
import Result from './pages/result';
import File from './components/file';

function App() {
  return (
    <div>
      <ChooseFloder/>
      <File fileName="document.txt"/>
    </div>
  );
}

export default App;
