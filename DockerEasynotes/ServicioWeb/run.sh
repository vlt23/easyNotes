#!/bin/sh
while ! nc -z mysql-master 3306 ; do
    echo "Waiting for MySQL server to be available"
    sleep 5
done

echo "MySQL server available, starting web app"
exec java -jar *.jar
echo "All ok ;)"
