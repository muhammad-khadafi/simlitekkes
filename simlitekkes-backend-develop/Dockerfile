# Build the JAR using Maven 3 container image
FROM maven:3.6.3-adoptopenjdk-11 AS builder
ENV MAVEN_OPTS="-Dhttps.protocols=TLSv1.2 -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=WARN -Dorg.slf4j.simpleLogger.showDateTime=true -Djava.awt.headless=true"
ENV MAVEN_CLI_OPTS="--batch-mode --errors --fail-at-end --show-version"
WORKDIR /app
COPY . .
RUN mvn ${MAVEN_CLI_OPTS} clean package

# Deploy the JAR into a Java 11 container image
FROM adoptopenjdk:11-jre-hotspot

# Setup custom arguments for building the container image
ARG USERNAME=simlitekkes
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

# Setup custom environment variables for running the container
ENV DEBUG=false
ENV JAVA_OPTS=""

# Prepare non-root user in the container image
RUN groupadd --gid ${USER_GID} ${USERNAME} \
    && useradd -s /bin/bash --uid ${USER_UID} --gid ${USER_GID} -M ${USERNAME}

# Set current working directory under non-root user and copy the required
# JAR files into the working directory
WORKDIR /app
COPY --chown=${USER_UID}:${USER_GID} --from=builder /app/target/*.jar ./simlitekkes.jar
RUN mkdir -p file && chown -R ${USERNAME}:${USERNAME} /app

# Configure how to run the application in the container
USER ${USERNAME}
EXPOSE 8001
VOLUME [ "/app/file" ]
ENTRYPOINT [ "java", "-jar", "./simlitekkes.jar" ]

# Set metadata information for the container image
ARG IMAGE_CREATED=""
ARG IMAGE_SOURCE="https://gitlab.pusilkom.com/ppsdm/simlitekkes-backend"
ARG IMAGE_VERSION=""
ARG IMAGE_REVISION=""
ARG IMAGE_VENDOR="Pusat Ilmu Komputer Universitas Indonesia"
ARG IMAGE_TITLE="SIMLITEKKES Backend"
ARG IMAGE_DESCRIPTION="Layanan backend untuk SIMLITEKKES"
LABEL org.opencontainers.image.created=${IMAGE_CREATED} \
      org.opencontainers.image.authors="Development Team @ Pusat Ilmu Komputer Universitas Indonesia" \
      org.opencontainers.image.source=${IMAGE_SOURCE} \
      org.opencontainers.image.version=${IMAGE_VERSION} \
      org.opencontainers.image.revision=${IMAGE_REVISION} \
      org.opencontainers.image.vendor=${IMAGE_VENDOR} \
      org.opencontainers.image.title=${IMAGE_TITLE} \
      org.opencontainers.image.description=${IMAGE_DESCRIPTION}
