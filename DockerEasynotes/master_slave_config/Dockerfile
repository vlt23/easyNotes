FROM mysql:5.7

RUN apt update; apt install -y netcat
COPY ./master_slave.sh /
CMD ["bash", "/master_slave.sh"]
