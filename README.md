
## Steps to reproduce error

1. Start the server
```
cd server/
./mvnw mn:run
```
2. Run test on client project
```
cd client/
./mvnw clean test
```

One of the tests will fail, because the server expects a uri param. The uri param is not set in the client applications `HttpClientFilter` for the request that returns a `Flowable`, but it is set for a request that return `Single<String>`.