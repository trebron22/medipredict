# medipredict

Swagger url http://localhost:8080/swagger-ui/index.html

start application
- gradle clean
- gradle build
- docker-compose up --build


How would you go about implementing a MQ for e-mail notifications in this architecture?
SQS, SNS email subscription
How would you design an endpoint that is used for large file uploads?
create lambda which load line by line rows and send data to sqs and create a service which subcribe the sns and read the files
