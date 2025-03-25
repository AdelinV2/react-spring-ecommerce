import { Link } from "react-router-dom";
import { useState } from "react";
import "../css/AddItemsPage.css";

export default function AddItemsPage() {
  const [product, setProduct] = useState({});

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
        <button className="form-btn" type="submit">
          Add Product
        </button>
      </form>
    </>
  );
}
