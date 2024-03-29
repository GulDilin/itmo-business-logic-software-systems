FROM ubuntu:18.04
RUN apt-get update && \
    apt-get install -y wait-for-it openjdk-8-jdk maven
WORKDIR /app
COPY pom.xml /app/
COPY . /app/
RUN mvn clean compile

CMD wait-for-it $RABBITMQ_HOST:$RABBITMQ_PORT -- mvn \
        -DPOSTGRES_HOST=$POSTGRES_HOST \
        -DPOSTGRES_PORT=$POSTGRES_PORT \
        -DPOSTGRES_DB=$POSTGRES_DB \
        -DPOSTGRES_USER=$POSTGRES_USER \
        -DPOSTGRES_PASSWORD=$POSTGRES_PASSWORD \
        -DRABBITMQ_USERNAME=$RABBITMQ_USERNAME \
        -DRABBITMQ_PASSWORD=$RABBITMQ_PASSWORD \
        -DRABBITMQ_HOST=$RABBITMQ_HOST \
        -DRABBITMQ_VIRTUALHOST=$RABBITMQ_VIRTUALHOST \
        -DRABBITMQ_PORT=$RABBITMQ_PORT \
        -DRABBITMQ_TIMEOUT=$RABBITMQ_TIMEOUT \
    install
