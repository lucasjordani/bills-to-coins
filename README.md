# Getting Started

## Start the application
To start this application on command line, it is necessary to run the command mvn spring-boot:run.

If you want, you can download an IDE like Eclipse or Intellij and run inside the IDE app.

## Calling the endpoints

There are four endpoints in this application, all of them you can call using the Postman tool.

By default, all the coins start with 100 units, you can change that using one of the endpoints.

The application start on port 8080.

To calculate the least amount of change, you need to call the endpoint "http://localhost:8080/change/least/$value" using GET type, where the "$value" variable is the value of the bill to change.

To calculate the most amount of change, you need to call the endpoint "http://localhost:8080/change/most/$value" using GET type, where the "$value" variable is the value of the bill to change.

To check how many coins still left, you can call the "http://localhost:8080/change/coins" using GET type. The return as the quantity of all coins.

For the last, to change a quantity of a coin, you can call the endpoint "http://localhost:8080/change/set" using POST type, passing inside the body the value of the coin, and the quantity you want. The example below change the ten cents coin to 150 units:
{
    "value" : "0.10",
    "quantity" : "150"
}