FROM amazoncorretto:21
LABEL maintainer="Aleksandr_Barmin@epam.com"

ARG module_path
COPY ../$module_path/build/libs/*.jar /opt/app.jar

ADD buildscripts/entrypoint.sh /opt/entrypoint.sh
RUN chmod +x /opt/entrypoint.sh
RUN sed -i.bak 's/\r$//' /opt/entrypoint.sh

ENV IS_SERVICE_DISCOVERY false

ENTRYPOINT ["./opt/entrypoint.sh"]