import veldaraLogo from "../assets/VeldaraLogo.png";
import SearchBar from "./SearchBar";

export default function Navbar() {
  return (
    <nav className="nav-container">
      <div className="nav-logo-search-cont">
        <img className="nav-logo" src={veldaraLogo} alt="Veldara logo" />
        <SearchBar />
      </div>
      <ul className="nav-list">
        <li className="nav-li-item">
          <a className="item-link" href="#">
            Wishlist
          </a>
        </li>
        <li className="nav-li-item">
          <a className="item-link" href="#">
            Cart
          </a>
        </li>
        <li className="nav-li-item">
          <a className="item-link" href="#">
            Account
          </a>
        </li>
      </ul>
    </nav>
  );
}
