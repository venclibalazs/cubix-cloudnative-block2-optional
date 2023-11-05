# Second optional assignment

You have one application in the __frontend__ folder which calls a __backend__ application, 
which can be started via this image: `quay.io/drsylent/cubix/block2/optional-backend:springboot3`.

You can test the applications with the Postman collection _Second Optional Assignment.postman_collection.json_ 
or with the Bruno collection in the _bruno-api_ folder.

## Frontend application

You can run the application with these commands:

```
./mvnw clean package
java -jar target/cubix-second-optional-frontend-0.0.1-SNAPSHOT.jar
```

## Running the two applications together

Take care to not start the backend application's container with the same port binding, as the frontend application.


The frontend can reach the backapp only if it's URL is provided.
The backend application's URL can be configured like this with the startup command (the URL here is http://localhost:8081):

```
java -jar target/cubix-second-optional-frontend-0.0.1-SNAPSHOT.jar --backend.url=http://localhost:8081
```
