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
        name="Parfum HUGO BOSS sadasda  adsdadasd dsadasdsa adasda"
        oos={true}
        img="https://petapixel.com/assets/uploads/2023/06/Boss-Infinite-1536x1023.jpg"
        favourite={false}
        rating={4.5}
        numComms={123}
        discount={true}
        oldPrice={399.99}
        price={299.99}
      />
    </div>
  );
}

export default App;
