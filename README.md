# An Web Application For Online Sellers

This is a web application for online sellers to List, Create, Update, and Delete products. Consists of a web based front-end that communicates with back-end restful endpoints and stores information in a database. Front-end and back-end were developed seperately, you can find back-end in the main branch and front-end in the front-end branch.

## ERD

![Assignment ERD - Page 1](https://user-images.githubusercontent.com/73251017/140905260-58a99fdc-05a9-4e8e-afb1-cbdff4974758.png)

## User Flow
![Sellozo -  User Flow](https://user-images.githubusercontent.com/73251017/140905309-0544c682-d2d7-453b-b831-4a400235d13b.png)

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
