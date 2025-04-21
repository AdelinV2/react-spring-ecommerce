CREATE DATABASE IF NOT EXISTS user_db;
CREATE DATABASE IF NOT EXISTS product_db;
CREATE DATABASE IF NOT EXISTS review_db;
CREATE DATABASE IF NOT EXISTS cart_wishlist_db;

CREATE USER IF NOT EXISTS 'user_service_user'@'%' IDENTIFIED BY 'Parola$User#';
CREATE USER IF NOT EXISTS 'product_service_user'@'%' IDENTIFIED BY 'Parola$Product#';
CREATE USER IF NOT EXISTS 'review_service_user'@'%' IDENTIFIED BY 'Parola$Review#';
CREATE USER IF NOT EXISTS 'cart_wishlist_service_user'@'%' IDENTIFIED BY 'Parola$Cart#';

GRANT ALL PRIVILEGES ON user_db.* TO 'user_service_user'@'%';
GRANT ALL PRIVILEGES ON product_db.* TO 'product_service_user'@'%';
GRANT ALL PRIVILEGES ON review_db.* TO 'review_service_user'@'%';
GRANT ALL PRIVILEGES ON cart_wishlist_db.* TO 'cart_wishlist_service_user'@'%';

FLUSH PRIVILEGES;