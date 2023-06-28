import './App.css';
import ChooseFloder from './pages/chooseFloder';
// import Result from './pages/result';
// import Floder from './components/floder';
import FloderList from './components/folderList';
import Path from './components/path';
import PathShow from './components/pathShow';

function App() {
  return (
    <div>
      <ChooseFloder/>
      {/* <Path pathName="PC"/> */}
      <PathShow/>
      {/* <Floder floderName="Downloads"/> */}
      <FloderList/>
    </div>
  );
}

export default App;
