#FROM mysql
#COPY schema-mysql.sql /docker-entrypoint-initdb.d/

FROM eventuateio/eventuate-tram-sagas-mysql:0.6.0.RELEASE
COPY schema-mysql.sql /docker-entrypoint-initdb.d/z-eventuate-local-schema-mysql.sql