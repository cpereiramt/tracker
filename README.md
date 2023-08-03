# Tracker

App to calculate and identify Mobile Stations near to Base Stations. 

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Documentation](#documentation)
- [Notes]( #notes)


## Features

- Group all MobileStations to BaseStations taking in consideration the proximity between them.
- When the application starts, @Scheduled spring boot annotation fires execution of monitoring from 5 to 5 seconds
- When some association is found (Between BaseStations and MobileStations), mobileStation is separate in a list to be save in a table reports in H2 database


## Prerequisites
- Git for download de source code 
- JDK 8
- Docker (Optional), if you want to run the project via container
- Any IDE from your choose.

## Installation
 
Clone the repository:

```bash
git clone https://github.com/cpereiramt/tracker.git
cd tracker
mvn clean package
docker compose -f docker-compose-local.yaml up --build 
```

## Documentation 
The documentation can be accessed in :
- ${app_host}/swagger-ui/index.html

## Notes
- I used BaseStation repository because don't exist no one endpoint in the use case for retrieving BaseStation information.

    ```java
    List<BaseStation> baseStationList =  baseStationRepository.findAll();
    ```
- The response from RestEndpoint2 is not in specified format in the use caso only to maintain the pattern of project for response, if necessary it can be easily modified.     ```JSON
     
  ```JSON
    {
      "mobileId": uuid,
      "x": float,
      "y": float,
      "error_radius": float,
      "error_code": integer,
      "error_description": string
      }
    ```