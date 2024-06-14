FROM quay.io/drsylent/cubix/block2/optional-base:ubuntu22

USER 0
RUN apt-get -y update
RUN rm /var/lib/dpkg/info/libc-bin.* && apt-get clean && apt-get -y update && apt-get -y install openjdk-21-jre-headless && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

ARG BUILDER_USERNAME
LABEL image.builder=${BUILDER_USERNAME}
USER 1001
COPY --chown=1001 ./target/*.jar  app.jar

ENV JAR_ARGS=
ENV JAVA_OPTS=
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar $JAR_ARGS
