# TripMaster_TourGuide

SpringBoot application - Gradle - Microservices - Feign - Docker

---
**Instructions**

---
<em>run with Docker:</em> <br>
/TripMaster_TourGuide/docker-compose]$ docker-compose up <br>
<br>
<em>run without Docker:</em> <br>
/TripMaster_TourGuide/tourGuide/src/main/resources/application.properties<br>
switch properties<br>
run each module separately<br>
<br>
<em>test with userName : </em><br>
"internalUser{n}"  with  0<n<99
---
**Table of content**

---
1.Scheme

2.Endpoints

3.Performances

---
**1.Architectural  overview**
---
![](annexes/microservicesScheme.png)

___
**2.EndPoints**
---
*Tour Guide*
- http://localhost:8080/getUser?userName={userName}
  <br>
  <br>
- http://localhost:8080/getLocation?userName={userName}
- http://localhost:8080/getRewards?userName={userName}
  <br>
  <br>
- http://localhost:8080/getNearbyAttractions?userName={userName}
  <br>
  <br>
- http://localhost:8080/getTripDeals?userName={userName}
- http://localhost:8080/preferences?userName={userName}&numberOfAdults={numberOfAdults}&numberOfChildren={numberOfChildren}&tripDuration={tripDuration}
  <br>
  <br>
- http://localhost:8080/getAllCurrentLocations

___
**3.Performances**
---
![](annexes/getLocationsPerformances.png)
___
![](annexes/getRewardPerformances.png)

___