#Introduction

Software represents a registration form to groomer service. 


#Non-functional requirements

* JDK 16+;<br/>
* Maven 3.6+;<br/>
* Database PostgreSQL;<br/>
* Docker-machine;<br/>
* Node.js v16.13.1.<br/>


#Functional requirements

The application provides:</br>

⦁   choose different options for registration user to service depends on preference;<br/>
⦁   change record if user made mistake and want to choose different options;<br/>
⦁   information about free time for record, if free time doesn't provide that groomer is busy;<br/>
⦁   registration form that contains information about record;<br/>
⦁   registration fields have validation;<br/>
⦁   if registration is successful user will forward to page that show that.<br/>

The application based on four microservice, front-end application and database. That structure is showing on image bellow.

![img.png](img.png)

The microservices relate between each other throw REST API. The functional scheme us showing on image bellow

![img_1.png](img_1.png)


#Use case

The database is filled with test data that is located along the path resource/src/main/resources/initdb<br/>

###Compile project and create container

git clone https://github.com/Hardziyevich/microservice<br/>
./mvnw clean package -DskipTests<br/>
docker-compose up<br/>

Enter the path in the browser:<br/>
http://localhost:3000/<br/>

#Test case

The application didn't cover integration test.
