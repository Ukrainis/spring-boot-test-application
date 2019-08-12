# Overview

This is a Spring Boot application which was created as experiment application for API Test automation trainingg.  
It's base url is: <https://spring-boot-test-aplication.herokuapp.com/>  
Currently next api's present in this application:  

1. `/api/users` - `GET` request to get all users data, which currently present in DB.

2. `/api/user` - `POST` request for adding a new users, request example:

```json
{
   "email": "string",
   "name": "string",
   "phone": "string",
   "userName": "string",
   "website": "string"
}
```

Validation: all fields are mandatory, "userName" is unique.

3. `/api/user/{userName}` - `DELETE` request for removing user by it's userName.  
4. `/api/user/{userName}/company` - `PUT` request for adding/updating Company to user, request example:  

```json
{
  "bs": "string",
  "catchPhrase": "string",
  "name": "string"
}
```

Validation: all fields are mandatory.

5. `/api/user/{userName}/address` - `PUT` request for adding/updating Address to user, request example:

```json
{
  "city": "string",
  "street": "string",
  "suite": "string",
  "zipcode": "string"
}
```

Validation: all fields are mandatory.

6. `/api/user/{userName}/address/geo` - `PUT` request for adding/updating Geo to user's Address, request example:

```json
{
  "lat": "string",
  "lng": "string"
}
```

Validation: all fields are mandatory.

7. `/api/todo` - `POST` request for adding a new Todo, request example:

```json
{
  "title": "string"
}
```

Validation: all fields are mandatory, "title" is unique.

8. `/api/todos` - `GET` request for getting all todo's.
9. `/api/user/{userName}/todo/{todoId}` - `PUT` request for assigning TODO to User.
