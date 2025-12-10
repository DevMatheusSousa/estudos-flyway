package com.estudosflyway.estudosflyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstudosflywayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstudosflywayApplication.class, args);
	}

}

//requisicao do tipo GET para a url: http://localhost:8080/api/users
//requisicao do tipo POST para a url: http://localhost:8080/api/users
//requisicao do tipo PUT para a url: {id} exemplo: http://localhost:8080/api/users/1
//requisicao do tipo DELETE para a url: {id}
/*

{
	
    "username": "teste",
    "email": "teste@teste.com",
    "password": "123456",
    "role": "ROLE_USER"
    

    
}
*/

/*
testando Put:

{
    "username": "teste",
    "email": "teste@teste.com",
    "password": "123456",
    "role": "ROLE_USER"
}

url: http://localhost:8080/api/users/1
 */

/*
testa end Ponit de Product

GET: http://localhost:8080/api/products
POST: http://localhost:8080/api/products
PUT: http://localhost:8080/api/products/{id}
DELETE: http://localhost:8080/api/products/{id}

{
    "name": "teste",
    "price": 100,
    "description": "teste",
    "category": "teste",
    "stockQuantity": 100
    "createdAt": "null",
    "updatedAt": "null"
}
*/

/*
Patch:
http://localhost:8080/api/orders/{id}
body:
{
    "status": "COMPLETED"
}
*/