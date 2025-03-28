import { Link } from "react-router-dom";
import { useState } from "react";
import "../css/AddItemsPage.css";

export default function AddItemsPage() {
  const [specifications, setSpecifications] = useState([]);
  const [images, setImages] = useState([]);
  const [product, setProduct] = useState({
    sellerId: 0,
    name: "",
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

  function updateSpecifications(category, spec) {
    const specs = [...specifications];
    const specItem = { category: category, specs: spec };
    specs.push(specItem);
    setSpecifications(specs);
  }

  function handleSubmit(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    updateSpecifications(formData.get("category"), formData.get("spec"));
    const newProduct = {
      name: formData.get("name"),
      description: formData.get("description"),
      category: formData.get("category"),
      price: parseFloat(formData.get("price")),
      stock: parseInt(formData.get("stock")),
      weight: parseFloat(formData.get("weight")),
      available: true,
      images: images,
      specifications: specifications,
    };
    setProduct(newProduct);
  }

  function handleImgChange(e) {
    const imgs = Array.from(e.target.files);
    const imgsObj = [];

    imgs.forEach((img) => {
      const reader = new FileReader();
      reader.readAsDataURL(img);
      reader.onload = (e) => {
        imgsObj.push({
          fileName: img.name,
          data: e.target.result.split(",")[1],
          fileType: img.type,
        });
      };

      setImages(imgsObj);
    });
  }

  return (
    <>
      <Link to="/">Back</Link>
      <form className="add-product-form" onSubmit={handleSubmit}>
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
        <span className="form-label">Specificatii:</span>
        <input
          className="form-input"
          type="text"
          name="spec"
          placeholder="Parfumuri"
        />
        <span className="form-label">Pret:</span>
        <input
          className="form-input"
          type="number"
          name="price"
          step="0.01"
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
