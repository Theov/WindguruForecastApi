# Windguru unoficial scrapping API

This app is a marine weather REST API based on windguru.cz website data.
Powered by Java 8 and Springboot.

# How it works ?
Launch the app and make simple HTTP GET request like:
```sh
$ curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X
                GET http://hostname/forecast/spot/231/{YourSpotId}
```
The request while return a collection of json objects like this one:
```json
[
    {
        "swellHeight":2.0,
        "swellDir":293,
        "swellPeriod":13.5,
        "windSpeed":13.3,
        "windDir":355,
        "temp":14.8,
        "initTimeStamp":1491393600,
        "sunsetTimeStamp":1491417240,
        "sunriseTimeStamp":1491370800
    },
...
]
```
# package and launch API
```sh
$ git clone https://github.com/Theov/WindguruForecastApi.git
$ cd WindguruForecastApi
$ mvn clean package
$ java -jar java -jar target/WindguruForecastApi-0.0.1.jar
```