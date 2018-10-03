### Restaurant_Rating

##### Technical task:
***
 Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
 The task is:
 Build a voting system for deciding where to have lunch.
 2 types of users: admin and regular users.
 Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 Menu changes each day (admins do the updates)
 Users can vote on which restaurant they want to have lunch at
 Only one vote counted per user
 If user votes again the same day:
 If it is before 11:00 we asume that he changed his mind.
 If it is after 11:00 then it is too late, vote can't be changed
 Each restaurant provides new menu each day.
***
### Restaurants: 

		  
##### get all restaurants:
    curl --request GET \
      --http://localhost:8080/profile/restaurants \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'
      
##### get all restaurants with count of votes:
    curl --request POST \
      --http://localhost:8080/profile/restaurants \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'      
      
##### add restaurant:
    curl --request POST \
      --http://localhost:8080/profile/restaurants/save \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'
      --header 'Content-Type: application/json' \
      --data '{"id": null,
       "name": "CrumbPotatoNew",
       "address": "Bus stationNew",
       "email": "CP@gmailNew.com"}'      

##### update restaurant by id:
    curl --request PUT \
      --http://localhost:8080/profile/restaurants/100004 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz' \
      --header 'Content-Type: application/json' \
      --data '
      {"id":100004,
      "name":"Belyash for gentlemen Update",
      "address":"SubWay",
       "email": "CP@gmailNew.com"
      }'

##### delete restaurant by id:
    curl --request DELETE \
      --url http://localhost:8080/profile/restaurants/100004 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

##### get restaurant by id:
    curl --request GET \
      --url http://localhost:8080/profile/restaurants/100003 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'
***
      
  
  
### Dishes:
		  
##### get all dishes:
    curl --request POST \
      --url http://localhost:8080/profile/dishes \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

##### get all dishes with count of votes:
    curl --request GET \
      --url http://localhost:8080/profile/dishes \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'      
      
 ##### add dish:
    curl --request POST \
      --url http://localhost:8080/profile/dishes/save \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz' \
      --header 'Content-Type: application/json' \
      --data '
      {"id": null,
      "description": "CrumbPotatoshkaNew",
      "date": "2018-10-02",
      "restaurant": {
      "id": 100003,
      "name": "CrumbPotato",
      "address": "Bus station",
      "email": "CP@gmail.com"
      },
      "price": 10000000
      }'

##### update dish by id:
    curl --request PUT \
      --url http://localhost:8080/profile/dishes/100006 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz' \
      --header 'Content-Type: application/json' \
      --data '
      {"id": 100006,
      "description": "CrumbPotatoshkaUpdate",
      "date": "2018-10-02",
      "restaurant": {
      "id": 100003,
      "name": "CrumbPotato",
      "address": "Bus station",
      "email": "CP@gmail.com"
      },
      "price": 11111
      }'

##### delete dish by id:
    curl --request DELETE \
      --url http://localhost:8080/profile/dishes/100006 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

##### get dish by id:
    curl --request GET \
      --url http://localhost:8080/profile/dishes/100008 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

##### get dishes with votes between dates:
    curl --request GET \
      --url http://localhost:8080/profile/dishes/filter?startDate=2018-10-02&endDate=2019-10-02 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

***

### Users:
		  
##### get all users:
    curl --request GET \
      --url http://localhost:8080/profile/users \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'
      
 ##### add user:
    curl --request POST \
      --url http://localhost:8080/profile/users/save \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz' \
      --header 'Content-Type: application/json' \
      --data '
      {
       "name": "newBie",
       "age": 23,
       "sex": "SEX_MALE",
       "email": "male@rating.com",
       "enabled": true,
       "password": "new pass",
       "roles": ["ROLE_USER"]
       }'

##### update user by id:
    curl --request PUT \
      --url http://localhost:8080/profile/users/100001 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz' \
      --header 'Content-Type: application/json' \
      --data '
      {"id": 100001,
      "name": "newBie",
      "age": 23,
      "sex": "SEX_MALE",
      "email": "user1@rating.com",
      "enabled": true,
      "password": "new pass",
      "roles": ["ROLE_USER"]
      }'

##### delete user by id:
    curl --request DELETE \
      --url http://localhost:8080/profile/users/100001 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

##### get user by id:
    curl --request GET \
      --url http://localhost:8080/profile/users/100000 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

***



### Votes:

		  
##### get all votes:
    curl --request GET \
      --url http://localhost:8080/profile/votes \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'
      
 ##### add vote:
    curl --request POST \
      --url http://localhost:8080/profile/votes/save \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz' \
      --header 'Content-Type: application/json' \
      --data '
      {"id": null, "user": {
      "id": 100000, "name": "admin","age": 23,
      "roles": ["ROLE_USER","ROLE_ADMIN"],
      "sex": "SEX_WHATEVER", "email": "admin@rating.com",
      "enabled": true,   "registeredDate": "2018-10-03"
      },
      "dish": {"id": 100006,"description": "CrumbPotatoshka",
      "date": "2018-10-02",
       "restaurant": {"id": 100003, "name": "CrumbPotato",
      "address": "Bus station", "email": "CP@gmail.com"
      },"price": 1000},
      "restaurant": {"id": 100003,"name": "CrumbPotato",
                          "address": "Bus station",
                          "email": "CP@gmail.com"},
                      "date": "2018-10-03T00:00:01"
      }'

##### update vote by id:
    curl --request PUT \
      --url http://localhost:8080/profile/votes/100009 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz' \
      --header 'Content-Type: application/json' \
      --data '{"id": 100009, "user": {
                    "id": 100000, "name": "admin","age": 23,
                    "roles": ["ROLE_USER","ROLE_ADMIN"],
                    "sex": "SEX_WHATEVER", "email": "admin@rating.com",
                    "enabled": true,   "registeredDate": "2018-10-03"
                    },
                    "dish": {"id": 100006,"description": "CrumbPotatoshka",
                    "date": "2018-10-02",
                     "restaurant": {"id": 100003, "name": "CrumbPotato",
                    "address": "Bus station", "email": "CP@gmail.com"
                    },"price": 1000},
                    "restaurant": {"id": 100003,"name": "CrumbPotato",
                                        "address": "Bus station",
                                        "email": "CP@gmail.com"},
                                    "date": "2014-10-03T00:00:01"
                    }'

##### delete vote by id:
    curl --request DELETE \
      --url http://localhost:8080/profile/votes/100012 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'

##### get vote by id:
    curl --request GET \
      --url http://localhost:8080/profile/votes/100011 \
      --header 'Authorization: Basic YWRtaW46YWRtaW5wYXNz'
		  
***

curl in [postman](https://documenter.getpostman.com/view/5493051/RWgm4MM5)