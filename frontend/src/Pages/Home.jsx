import Navbar from "../components/Navbar";
import NavFooter from "../components/NavFooter";
import ProductCard from "../components/ProductCard";
import ProductCarousel from "../components/ProductCarousel";

export default function Home() {
  return (
    <>
      <Navbar />
      <NavFooter />
      <ProductCarousel name="emi" />
    </>
  );
}
