import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import "../css/AddItemsPage.css";

export default function AddItemsPage() {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [specifications, setSpecifications] = useState([]);
  const [images, setImages] = useState([]);
  const [product, setProduct] = useState({
    id: 0,
    sellerId: 0,
    name: "",
    category: "",
    description: "",
    oldPrice: 0,
    price: 0,
    stock: 0,
    weight: 0,
    available: false,
    images: [],
    specifications: [],
  });

  useEffect(() => {
    if (isSubmitting === true) {
      console.log("Product state:", product);
      const post = async () => {
        try {
          const response = await fetch("http://localhost:8000/api/product", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(product),
          });

          if (response.ok) {
            const result = await response.json();
            console.log("Product added successfully:", result);
          }
        } catch (error) {
          console.error("Error adding product:", error);
        } finally {
          setIsSubmitting(false);
        }
      };
      post();
    }
  }, [isSubmitting]);

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
        specs.push({
          orderIndex: 0,
          title: specTitle,
          subSpecifications: [subSpec],
        });
      } else if (specs.length > 0) {
        if (specs.find((spec) => spec.title === specTitle)) {
          const index = specs.findIndex((spec) => spec.title === specTitle);
          specs[index].subSpecifications.push(subSpec);
        } else {
          specs.push({
            orderIndex: specs.length,
            title: specTitle,
            subSpecifications: [subSpec],
          });
        }
      }
      setSpecifications(specs);

      e.target.form.reset();
    } else {
      alert("Please fill in all fields.");
    }
  }

  async function handleSubmit(e) {
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
        ...product,
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
      setIsSubmitting(true);
    } else {
      alert("Please fill in all fields.");
    }
  }

  function handleImgChange(e) {
    const imgs = Array.from(e.target.files);
    const imgsObj = [];
    let loadedCount = 0;

    imgs.forEach((img) => {
      const reader = new FileReader();
      reader.readAsDataURL(img);
      reader.onload = (e) => {
        imgsObj.push({
          orderIndex: loadedCount,
          fileName: img.name,
          data: e.target.result.split(",")[1], // Base64 data (remove header)
          fileType: img.type,
        });

        loadedCount++;
        if (loadedCount === imgs.length) {
          setImages(imgsObj); // Update state only after all images are loaded
        }
      };
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
                {spec.subSpecifications.map((subSpec, index) => {
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
