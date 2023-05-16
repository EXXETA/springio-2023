FROM ghcr.io/graalvm/graalvm-ce:ol7-java17-22.3.1

# Setup tools
RUN yum update -y && yum install wget -y && yum install unzip -y && yum install patch -y

# Install graalpy
RUN gu install python
RUN graalpy -m venv project_matcher_venv
RUN source project_matcher_venv/bin/activate
RUN graalpy -m ginstall install numpy

# Install maven
ARG MAVEN_VERSION=3.8.8
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Setup spring-application
RUN mkdir -p /projectmatcher
COPY . .
EXPOSE 8080
RUN mvn clean verify
CMD [ "mvn", "spring-boot:run" ]