Dummy FX Platform
===

Scenario
---
“Dummy FX platform” is a Java/Spring Service exposing a REST API. The requirements for phase 1 are limited in scope, not well refined and need to be interpreted by the Developer who will make his assumptions to implement the best possible lean solution.

Instructions
---
The Service should provide the following functionalities:
1) For this simple version the current price is static and it is supplied via config file (E.g. GBP/USD = 1.2100).
2) Order registration.
-The order contains fields  userId, orderType: BID or ASK, currency(GBP/USD), price(E.g. 1.2100), amount(E.g. 8500).
3) Cancel an order.
4) Return summary regarding live not matched orders.
5) Return a summary of matched trades.

FxPlatformApplication
---

When running this application it requires a command line parameter for a file name which will contain a single number of a price


When the application is running a definiation of the endpoints is available in [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


This can be used to explore and test the URLS published.


