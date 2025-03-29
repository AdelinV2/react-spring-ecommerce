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

  function addSpecification(e) {
    e.preventDefault();
    const formData = new FormData(e.target.form);
    const specTitle = formData.get("spec-title");
    const specDesc = formData.get("spec-description");
    const specValue = formData.get("spec-value");

    if (specTitle && specDesc && specValue) {
      const specs = [...specifications];
      const subSpec = {
        orderIndex:
          specs.find((spec) => spec.title === specTitle)?.specs.length || 0,
        description: specDesc,
        value: specValue,
      };

      if (specs.length === 0) {
        specs.push({ orderIndex: 0, title: specTitle, specs: [subSpec] });
      } else if (specs.length > 0) {
        if (specs.find((spec) => spec.title === specTitle)) {
          const index = specs.findIndex((spec) => spec.title === specTitle);
          specs[index].specs.push(subSpec);
        } else {
          specs.push({
            orderIndex: specs.length,
            title: specTitle,
            specs: [subSpec],
          });
        }
      }
      setSpecifications(specs);

      e.target.form.reset();
    } else {
      alert("Please fill in all fields.");
    }
  }

  function handleSubmit(e) {
    e.preventDefault();
    const formData = new FormData(e.target);

    if (
      formData.get("name") ||
      formData.get("description") ||
      formData.get("category") ||
      formData.get("price") ||
      formData.get("stock") ||
      formData.get("weight")
    ) {
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
    } else {
      alert("Please fill in all fields.");
    }
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

      if (imgsObj.length === imgs.length) {
        setImages(imgsObj);
      }
    });
  }

  return (
    <>
      <Link to="/">Back</Link>
      <div className="form-specs">
        <form className="add-product-form" onSubmit={handleSubmit}>
          <h3 className="form-label">Nume:</h3>
          <input
            className="form-input"
            type="text"
            name="name"
            placeholder="Parfum 200ml"
          />
          <h3 className="form-label">Descriere:</h3>
          <input
            className="form-input"
            type="text"
            name="description"
            placeholder="Parfum 200ml descriere"
          />
          <h3 className="form-label">Categorie:</h3>
          <input
            className="form-input"
            type="text"
            name="category"
            placeholder="Parfumuri"
          />
          <h3 className="form-label">Titlu specificatie:</h3>
          <input
            className="form-input"
            type="text"
            name="spec-title"
            placeholder="Aroma"
          />
          <h3 className="form-label">Specificatii:</h3>
          <h5>Descriere:</h5>
          <input
            className="form-input"
            type="text"
            name="spec-description"
            placeholder="Parfum floral"
          />
          <h5>Valoare:</h5>

          <input
            className="form-input"
            type="text"
            name="spec-value"
            placeholder="Parfum floral"
          />
          <button className="form-btn" onClick={addSpecification}>
            Add Spec
          </button>
          <h3 className="form-label">Pret:</h3>
          <input
            className="form-input"
            type="number"
            name="price"
            step="0.01"
            placeholder="399.99"
          />
          <h3 className="form-label">Stock:</h3>

          <input
            className="form-input"
            type="number"
            name="stock"
            placeholder="30"
          />
          <h3 className="form-label">Greutate:</h3>

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
        <div className="specifications-container">
          {specifications.map((spec, index) => {
            return (
              <div className="specification">
                <h3 key={spec.title}>{spec.title}</h3>
                {spec.specs.map((subSpec, index) => {
                  return (
                    <div key={index} className="sub-specification">
                      <h4>{subSpec.description}</h4>
                      <p>{subSpec.value}</p>
                    </div>
                  );
                })}
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
}
