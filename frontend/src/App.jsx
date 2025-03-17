import "./App.css";
import Navbar from "./components/Navbar";
import NavFooter from "./components/NavFooter";
import ProductCard from "./components/ProductCard";

function App() {
  return (
    <div className="App">
      <Navbar />
      <NavFooter />
      <ProductCard
        name="Parfum HUGO BOSS"
        oot={false}
        img="https://petapixel.com/assets/uploads/2023/06/Boss-Infinite-1536x1023.jpg"
        favourite={false}
        rating={4.5}
        numComms={123}
      />
    </div>
  );
}

export default App;
