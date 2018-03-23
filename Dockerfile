FROM payara/server-full:181

RUN wget http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.46.tar.gz && tar -xvzf mysql-connector-java-5.1.46.tar.gz && mv mysql-connector-java-5.1.46/mysql-connector-java-5.1.46.jar ~/glassfish/domains/domain1/lib/ && rm -rf mysql-connector-java-5.1.46 && rm -r mysql-connector-java-5.1.46.tar.gz

COPY ./target/*.war /opt/payara41/deployments