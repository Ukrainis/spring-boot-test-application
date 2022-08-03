# Overview

This is a Spring Boot application which was created as experiment application for API Test automation trainingg.  
It's base url is: <https://spring-boot-test-aplication.herokuapp.com/>  
Currently next api's present in this application:  

1. `/api/users` - **200 code**, `GET` request to get all users data, which currently present in DB. Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.
2. `/api/user/createUserPost` - **201 code**, `POST` request for adding a new user. Can be used JSON format(with default _Accept application/json_ headers) and XML format(with headers: _Accept application/xml_).  

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
<CreateUserRequest>
 <name>string</name>
 <userName>string</userName>
 <email>string</email>
 <phone>string</phone>
 <website>string</website>
</CreateUserRequest>
```

3. `/api/user/createUserUrl` - **201 code**, `POST` request for adding user, using `application/x-www-form-urlencoded` media time. All data and exceptions same as for previous. `Accept` headers same as for previous.

4.`/api/user/{userName}/delete` - **204 code**, `DELETE` request for removing user by it's userName. 

 4.1. Exceptions, that may occur:

* ***UserNotFoundException*** - if incorrect "userName" provided.

5.`/api/user/{userName}/company` - **204 code**, `PUT` request for adding/updating Company to user. Can be used JSON format(with default headers) and XML format(with headers: Accept application/xml).

 5.1. Exceptions, that may occur:

* ***InvalidCompanyDataException*** - if any field is empty.
* ***UserNotFoundException*** - if provided user not exist.

 5.2. Examples of requests:  

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
<AddCompanyRequest>
 <name>string</name>
 <catchPhrase>string</catchPhrase>
 <bs>string</bs>
</AddCompanyRequest>
```

6.`/api/user/{userName}/address` - **204 code**, `PUT` request for adding/updating Address to user. Can be used JSON format(with default headers) and XML format(with headers: Accept application/xml).

 6.1. Exceptions, that may occur:

* ***400 code, InvalidAddressDataException*** - if any field is empty.
* ***404 code, UserNotFoundException*** - if provided "userName" not exist.

 6.2. Examples of requests:

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
<AddAddressRequest>
 <street>string</street>
 <suite>string</suite>
 <city>string</city>
 <zipcode>string</zipcode>
</AddAddressRequest>
```

7.`/api/user/{userName}/address/geo` - **204 code**, `PUT` request for adding/updating Geo to user's Address. Can be used JSON format(with default headers - _Accept application/json_) and XML format(with headers: _Accept application/xml_).

 7.1. Exceptions, that may occur:

* ***400 code, InvaliGeoException*** - if any field is empty.
* ***404 code, UserNotFoundException*** - if provided "userName" not exist.
* ***400 code, MissingAddressException*** - if provided user doesn't have saved Address.

 7.2. Examples of requests:

* JSON:

```json
{
  "lat": "string",
  "lng": "string"
}
```

* XML:

```xml
<AddGeoRequest>
 <lat>string</lat>
 <lng>string</lng>
</AddGeoRequest>
```

8.`api/users/findById/{id}` - **200 code**, `GET` request for getting user by his "id". Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.

 8.1. Exceptions, that may occur:

* **404 code, UserNotFoundException** - if user with provided "id" not exist.

9.`api/users/findByUserName/{userName}` - **200 code**, `GET` request for getting user by his "userName". Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.

 9.1. Exceptions, that may occur:

* **404 code, UserNotFoundException** - if user with provided "userName" not exist.

10.`/api/todo` - **201 code**, `POST` request for adding a new Todo. Can be used JSON format(with default headers - _Accept application/json_) and XML format(with headers: _Accept application/xml_).

 10.1. Exceptions, that may occur:

* **400 code, InvalidTodoException** - if field is missing.
* **400 code, DublicatedTodoException** - if such TODO exists.

 11.2. Examples of requests:

* JSON:

```json
{
  "title": "string"
}
```

* XML:

```xml
<Todo>
 <title>string</title>
</Todo>
```

12.`/api/todos` - **201 code**, `GET` request for getting all todo's. Headers: depending on what you want to receive, for json - default _Accept application/json_, for xml - _Accept application/xml_.

13.`/api/user/{userName}/assignTodo/{todoId}` - **204 code**, `PUT` request for assigning TODO to User.

 13.1. Exceptions, that may occur:

* **404 code, UserNotFoundException** - if user with provided "userName" not exist.
* **404 code, TodoNotFoundException** - if TODO with provided "id" not exist.
* **400 code, NotCompletedUserDataException** - if user with provided "userName" has not completed data (Address, Company)

14.`/api/todo/{todoId}` - **204 code**, `PUT` request for changing TODO status. Can be used JSON format(with default headers - _Accept application/json_) and XML format(with headers: _Accept application/xml_).

 14.1. Exceptions, that may occur:

* **400 code, InvalidTodoStatusException** - if field is missing.
* **404 code, TodoNotFoundException** - if TODO with provided "id" not exist.
* **400 code, TodoIsNotAssignedException** - if TODO is not assigned to any person.
* **400 code, WrongTodoStatusException** - if TODO statuses are not in correct way (Todo -> In progress -> Done)

 14.2. Examples of requests:

* JSON:

```json
{
  "status": "string"
}
```

* XML:

```xml
<TodoStatusChangeRequest>
 <status>string</status>
</TodoStatusChangeRequest>
```

15.`/api/apiKeyAuthenticatedResource` **200 code**, `GET` request to get resource with authorization with token in headers. Required header: name: _Auth-Key_, value: _123456789_.
 
16.`/api/basicAuthAuthenticatedResource`  **200 code**, `GET` request to get resource with Basic authorization. Authentication data: username: _admin_, password: _password_.

17. `/api/fileUpload` **200 code**, `POST` request to send .txt file and get it's contain in response.

17.1. Exceptions that may occur:
* **400 code, EmptyFileException** - if provided file is empty.
* **400 code, TooBigFileException** - if provided file size is more than 5000 bytes.
* **400 code, WrongFileExtensionException** - if provided file has other than **.txt** extension.

18. `/api/token/{userName}/generateToken` **200 code**, `GET` request to get authorization token for user.
18.1. Exceptions that may occur:
* **404 code, UserNotFound** - if provided userName doesn't belong to any existing users.

19. `api/user/{userName}/todos` **200 code**, `GET` request to get todo's, assigned to current user. Require Bearer token in headers, which can be generated, using previous request.
19.1. Exceptions, that may occur:
* **400 code** - if no Authorization header is provided.
* **400 code, InvalidJwtTokenProvided** - if empty Authorization header is provided.
* **401 code, InvalidUserTokenException** - if token is generated to different user.
