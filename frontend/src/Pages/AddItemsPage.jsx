import { Link } from "react-router-dom";
import { useState } from "react";
import "../css/AddItemsPage.css";

export default function AddItemsPage() {
  const [specifications, setSpecifications] = useState([]);
  const [images, setImages] = useState([]);
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
    images: images,
    specifications: specifications,
  });

  console.log(product);

  function handleSubmit() {}
  function handleImgChange(e) {
    const imgs = e.target.files;
    const imgsObj = Array.from(imgs)
      .filter((img) => {
        return img.type.startsWith("image/");
      })
      .map((img, index) => ({
        orderIndex: index,
        file: img,
      }));

    setImages(imgsObj);
    setProduct((prevProduct) => ({
      ...prevProduct,
      images: imgsObj,
    }));
  }

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
        <input
          type="file"
          className="form-input"
          name="img"
          multiple
          onChange={handleImgChange}
        />
        <button className="form-btn" type="submit">
          Add Product
        </button>
      </form>
    </>
  );
}
