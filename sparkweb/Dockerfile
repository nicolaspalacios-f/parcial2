FROM openjdk:8

WORKDIR /usrapp/bin


COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arem.ASE.app.SparkWebApp"]