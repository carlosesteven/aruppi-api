 # Stage 1: Cache Gradle dependencies
FROM gradle:8-jdk21-corretto AS cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle.kts /home/gradle/src/build.gradle.kts
COPY settings.gradle.kts /home/gradle/src/settings.gradle.kts
COPY gradle/libs.versions.toml /home/gradle/src/gradle/libs.versions.toml
WORKDIR /home/gradle/src
RUN gradle clean build -i --stacktrace

# Stage 2: Build Application
FROM gradle:8-jdk21-corretto AS build
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY . /usr/src/app/
WORKDIR /usr/src/app
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Build the fat JAR, Gradle also supports shadow
# and boot JAR by default.
RUN gradle buildFatJar --no-daemon

# Stage 3: Create the Runtime Image
FROM amazoncorretto:21 AS runtime
EXPOSE 8080/tcp
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/aruppi-api-all.jar /app/app.jar
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
