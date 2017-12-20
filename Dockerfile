FROM anapsix/alpine-java:8_jdk

ADD target/scala-2.12/pingpong-server-assembly-0.1.jar pingpong.jar
EXPOSE 4321
ENTRYPOINT ["java","-cp","pingpong.jar","Main"]
