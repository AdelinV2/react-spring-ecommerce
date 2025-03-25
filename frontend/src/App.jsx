import "./App.css";
import Home from "./Pages/Home";
import AddItemsPage from "./Pages/AddItemsPage";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/addItemsPage" element={<AddItemsPage />} />
      </Routes>
    </Router>
  );
}

export default App;
