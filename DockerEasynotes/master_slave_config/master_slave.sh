#!/bin/bash

while ! nc -z mysql-master 3306 ; do
    echo "Waiting for MySQL master server to be available"
    sleep 5
done

while ! nc -z mysql-slave 3306 ; do
    echo "Waiting for MySQL slave server to be available"
    sleep 2
done

# Show master status
echo "master: showing master status"
master_file=$(eval "mysql --host mysql-master -uroot -p1234 -e 'SHOW MASTER STATUS \G' | grep File | sed -n -e 's/^.*: //p'")
master_position=$(eval "mysql --host mysql-master -uroot -p1234 -e 'SHOW MASTER STATUS \G' | grep Position | sed -n -e 's/^.*: //p'")

# Configure slave using master status
echo "slave: changing master"
mysql --host mysql-slave -uroot -p1234 -AN -e 'STOP SLAVE;';
mysql --host mysql-slave -uroot -p1234 -AN -e 'RESET SLAVE ALL;';
mysql --host mysql-slave -uroot -p1234 -AN -e "CHANGE MASTER TO master_host='mysql-master', master_port=3306, \
        master_user='easynotes_replic', master_password='1234', master_log_file='$master_file', master_log_pos=$master_position;"
mysql --host mysql-slave -uroot -p1234 -AN -e "START SLAVE;"

echo "All ok ;)"
