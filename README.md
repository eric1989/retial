# retial

use maven clean install to build this project.

before starting application, you should have cassandra running on your localhost and port number 9042. 
Also, please run database_creation.cql should be run in cassandra before starting this application. 

after starting application, use this following command to start application:
java -jar /target/demo-0.0.1-SNAPSHOT.jar

the application runs on port 8080.

Here is the rest API for this application.

1. #/products/{id}/property?includes=NAME,PRICE

  The parameter is optional. If parameter is not supplied, the all of the product properties will be returned

2. #/products/{id}/price/update   
  
  The request body example is 
  {
    "value": "8.99",
    "currency_code": "USD"
  }
  
