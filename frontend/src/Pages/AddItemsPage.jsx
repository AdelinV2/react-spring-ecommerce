import { Link } from "react-router-dom";
import { useState } from "react";
import "../css/AddItemsPage.css";

export default function AddItemsPage() {

  const[specifications,setSpecifications]=useState()
  const [images, setImages] = useState(Array(10).fill(null));
  const [product, setProduct] = useState({
    sellerId: 0,
    name: 0,
    category: "",
    description: "",
    price: 0,
    oldPrice: 0,
    stock: 0,
    weight: 0,
    available: false,
    images: [],
    specifications: [],
  });

  function handleSubmit() {}

  return (
    <>
      <Link to="/">Back</Link>
      <form className="add-product-form" action={handleSubmit}>
        <span className="form-label">Nume:</span>
        <input
          className="form-input"
          type="text"
          name="name"
          placeholder="Parfum 200ml"
        />
        <span className="form-label">Descriere:</span>
        <input
          className="form-input"
          type="text"
          name="description"
          placeholder="Parfum 200ml descriere"
        />
        <span className="form-label">Categorie:</span>
        <input
          className="form-input"
          type="text"
          name="category"
          placeholder="Parfumuri"
        />
        <span className="form-label">Pret:</span>
        <input
          className="form-input"
          type="number"
          name="price"
          placeholder="399.99"
        />
        <span className="form-label">Stock:</span>

        <input
          className="form-input"
          type="number"
          name="stock"
          placeholder="30"
        />
        <span className="form-label">Greutate:</span>

        <input
          className="form-input"
          type="number"
          name="weight"
          placeholder="5"
        />
        <input type="file" className="form-input" name="img" />
        <button className="form-btn" type="submit">
          Add Product
        </button>
      </form>
    </>
  );
}
