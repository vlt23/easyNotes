

FROM openjdk:8-jre
RUN apt-get update; apt-get install -y netcat
COPY *.jar /app/
COPY ./run.sh /app/
WORKDIR /app
RUN chmod 777 /app/run.sh
EXPOSE 8081
EXPOSE 5701
CMD ["/app/run.sh"]
