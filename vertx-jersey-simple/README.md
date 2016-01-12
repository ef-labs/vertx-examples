# Simple Vert.x Jersey Example
 
Uses the following components to create a fat jar to run:

* HK2 - dependency injection (https://hk2.java.net)
* Jersey - rest (https://jersey.java.net)
* Jackson - json serialization (http://wiki.fasterxml.com/JacksonHome)
* Swagger - rest documentation (http://swagger.io)
 

See the config.json file for registering a custom HK2 binder and the Jersey configuration.


Build and run
 
```

# Build fat jar
mvn clean package

# Run fat jar and provide config file
java -jar target/vertx-jersey-simple-1.0.0-SNAPSHOT-fat.jar -conf config.json

# Issue curl command to rest endpoint
curl localhost:8080/api

# Get swagger docs
curl localhost:8080/swagger.json

```

You can browse the swagger docs via swagger ui (ex. http://petstore.swagger.io and enter http://localhost:8080/swagger.json).