# An Web Application For Online Sellers

This is a web application for online sellers to List, Create, Update, and Delete products. Consists of a web based front-end that communicates with back-end restful endpoints and stores information in a database. 

## ERD
![alt text](https://drive.google.com/file/d/1vzqUGPrHHGhFTWgS3-xSuDAg4Q6LpizO/view?usp=sharing)

## User Flow
![alt text](https://drive.google.com/file/d/1RlyzOfiPOq4_2aph8i-WYcnl133gdeVz/view?usp=sharing)

## Completed Functionalities

**Sign up/Sign in**
Register and sign in.

**List**
Return all products on sale with the lowest price after user authentication. A user can paginate throught lists of products.

**Create**
1. Create products: Add a new product
2. Create sellers: Create a new seller to sell current product 
   * backend accomplished, frontend UI has not
   * can test backend API with Postman Collection: https://go.postman.co/workspace/My-Workspace~790a6fbf-7f3e-4a6b-8376-8fc21c5f77b7/collection/14276940-65eabf32-0634-4bdd-8a23-03b3d8aab403

**Update**
Update product name.

**Delete**
Remove a product.

## Architecture Design

### front-end
React

### back-end
Spring Boot(with Maven to build dependencies) + Spring Security 

### database
PostgreSQL on AWS
