FROM openjdk:18

ENV ENVIRONMENT=prod

LABEL maintainer="Irene Papaspyratos"

ADD backend/target/flightsproject.jar flightsproject.jar

CMD [ "sh", "-c", "java -jar /flightsproject.jar" ]