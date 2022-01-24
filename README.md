# TripMaster_TourGuide

SpringBoot application - Gradle - Microservices - Feign - Docker

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