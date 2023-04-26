FROM eclipse-temurin:17
ADD target/jandas.jar jandas.jar
COPY src/main/resources/roles.csv src/main/resources/roles.csv
ENTRYPOINT ["java","-cp","jandas.jar","fr.uga.jandas.Main"]