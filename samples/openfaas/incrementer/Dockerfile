FROM openjdk:8u121-jdk-alpine

RUN apk --no-cache add curl \
    && curl -sL https://github.com/openfaas/faas/releases/download/0.9.14/fwatchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog

WORKDIR /root/

COPY incrementer.openfaas-0.0.1-SNAPSHOT-shaded.jar .

ENV fprocess="java -Djava.util.logging.config.file="logging.properties" -jar incrementer.openfaas-0.0.1-SNAPSHOT-shaded.jar"

HEALTHCHECK --interval=1s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]