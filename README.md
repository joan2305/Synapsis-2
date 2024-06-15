# Synapsis-Ecommerce
# Basic e-commerce API(s) including :
- **CRUD CARTS (path : "/api/carts")** : 
  - get all products from user's cart 
  - add product to cart -> adjusted to the UI's behaviour to just be able to add 1 product per request and able to update the quantity of the product if product exists in the user's cart)
  - delete item cart from user's cart -> also adjusted to the UI's behaviour to just be able to remove 1 item cart from the cart
  - checkout several item cart -> will create order and item order to save transaction, products' quantities will be reduced according to the datas
- **LOGIN REGISTER USER (path : "/api/users")** :
   - basic registration process -> password will be encrypted and user cache will be saved into redis after registered
   - basic login process -> will check cache first into redis (if doesn't exist then check from db)
- **PRODUCT API (path : "/api/products")** :
   - will be able to get products based on category or category name (query params are optional, can get all products too)
- Project has already been containerized using Docker, kindly check : jadr02/app:latest
- This project is using 2 database (1 relational database using PostgreSQL and 1 NoSQL in-memory database using Redis)
- Both databases have already been deployed, kindly check the app properties
- The docker images have been deployed to cloud, host : https://denpasar-jadr02-1df50c03.koyeb.app/e-comm
