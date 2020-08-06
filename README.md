# Overview

This is a Spring Boot application which was created as experiment application for API Test automation trainingg.  
It's base url is: <https://spring-boot-test-aplication.herokuapp.com/>  
Currently next api's present in this application:  
1. `/api/users` - `GET` request to get all users data, which currently present in DB. Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.
2. `/api/user` - `POST` request for adding a new user. Can be used JSON format(with default _Accept application/json_ headers) and XML format(with headers: _Accept application/xml_). 
2.1. Exceptions, that may occur:
* **400 code, InvalidUserDataException** - if any field is missing;
* **400 code, DublicateUserNameException** - if "userName" is not unique.
2.2. Examples of request:
* In JSON:
```json
{
   "email": "string",
   "name": "string",
   "phone": "string",
   "userName": "string",
   "website": "string"
}
```
* In XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<CreateUserRequest>
	<name>string</name>
	<userName>string</userName>
	<email>string</email>
	<phone>string</phone>
	<website>string</website>
</CreateUserRequest>
```

3. `/api/user/{userName}` - `DELETE` request for removing user by it's userName. 

	3.1. Exceptions, that may occur:
* ***UserNotFoundException*** - if incorrect "userName" provided.
4. `/api/user/{userName}/company` - `PUT` request for adding/updating Company to user. Can be used JSON format(with default headers) and XML format(with headers: Accept application/xml).

	4.1. Exceptions, that may occur:
* ***InvalidCompanyDataException*** - if any field is empty.
* ***UserNotFoundException*** - if provided user not exist.

	4.2. Examples of requests:  
* JSON:
```json
{
  "bs": "string",
  "catchPhrase": "string",
  "name": "string"
}
```
* XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<AddCompanyRequest>
	<name>string</name>
	<catchPhrase>string</catchPhrase>
	<bs>string</bs>
</AddCompanyRequest>
```
5. `/api/user/{userName}/address` - `PUT` request for adding/updating Address to user. Can be used JSON format(with default headers) and XML format(with headers: Accept application/xml).

	5.1. Exceptions, that may occur:
* ***400 code, InvalidAddressDataException*** - if any field is empty.
* ***404 code, UserNotFoundException*** - if provided "userName" not exist.

	5.2. Examples of requests:
* JSON:
```json
{
  "city": "string",
  "street": "string",
  "suite": "string",
  "zipcode": "string"
}
```
* XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<AddAddressRequest>
	<street>string</street>
	<suite>string</suite>
	<city>string</city>
	<zipcode>string</zipcode>
</AddAddressRequest>
```
6. `/api/user/{userName}/address/geo` - `PUT` request for adding/updating Geo to user's Address. Can be used JSON format(with default headers - _Accept application/json_) and XML format(with headers: _Accept application/xml_).

	6.1. Exceptions, that may occur:
* ***400 code, InvaliGeoException*** - if any field is empty.
* ***404 code, UserNotFoundException*** - if provided "userName" not exist.
* ***400 code, MissingAddressException*** - if provided user doesn't have saved Address.

	6.2. Examples of requests:
* JSON:
```json
{
  "lat": "string",
  "lng": "string"
}
```
* XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<AddGeoRequest>
	<lat>string</lat>
	<lng>string</lng>
</AddGeoRequest>
```
7. `api/users/findById/{id}` - `GET` request for getting user by his "id". Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.

	7.1. Exceptions, that may occur:
* **404 code, UserNotFoundException** - if user with provided "id" not exist.
8. `api/users/findByUserName/{userName}` - `GET` request for getting user by his "userName". Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.

	8.1. Exceptions, that may occur:
* **404 code, UserNotFoundException** - if user with provided "userName" not exist.
9. `/api/todo` - `POST` request for adding a new Todo. Can be used JSON format(with default headers - _Accept application/json_) and XML format(with headers: _Accept application/xml_).

	9.1. Exceptions, that may occur:
* **400 code, InvalidTodoException** - if field is missing.
* **400 code, DublicatedTodoException** - if such TODO exists.

	9.2. Examples of requests:
* JSON:
```json
{
  "title": "string"
}
```
* XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Todo>
	<title>string</title>
</Todo>
```
10. `/api/todos` - `GET` request for getting all todo's. Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.
11. `/api/user/{userName}/assignTodo/{todoId}` - `PUT` request for assigning TODO to User.

	11.1. Exceptions, that may occur:
* **404 code, UserNotFoundException** - if user with provided "userName" not exist.
* **404 code, TodoNotFoundException** - if TODO with provided "id" not exist.
* **400 code, NotCompletedUserDataException** - if user with provided "userName" has not completed data (Address, Company)
12. `/api/todo/{todoId}` - `PUT` request for changing TODO status. Can be used JSON format(with default headers - _Accept application/json_) and XML format(with headers: _Accept application/xml_).
	
	12.1. Exceptions, that may occur:
* **400 code, InvalidTodoStatusException** - if field is missing.
* **404 code, TodoNotFoundException** - if TODO with provided "id" not exist.
* **400 code, TodoIsNotAssignedException** - if TODO is not assigned to any person.
* **400 code, WrongTodoStatusException** - if TODO statuses are not in correct way (Todo -> In progress -> Done)

	12.2. Examples of requests:
* JSON:
```json
{
  "status": "string"
}
```
* XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<TodoStatusChangeRequest>
	<status>string</status>
</TodoStatusChangeRequest>
```
13. `/api/authenticatedResource` `GET` request to get resource with authorization with token in headers. 

	13.1. Required header: name: _Auth-Key_, value: _123456789_.
