# Sse project

A small showcase for Quarkus using server-sent-events (with JAX-RS).

## Running and test the application

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

### SSE with JAX-RS

Open a few terminal windows and try to register clients to the SSE-endpoint:

```
curl -N http://localhost:8080/notifications
```
Next, open yet another terminal and try to push some notifications along the registrations:
```
curl -X PUT http://localhost:8080/notifications
```

__Expected Outcome:__ Each of the registered listeners should receive a notification<br/>
__Actual Outcome:__ None of the registered listerners receives anything after registration.
