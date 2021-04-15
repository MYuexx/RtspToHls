FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=rtsp.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS="\
 -server \
 -Xmx4g \
 -Xms4g \
 -Xmn1g \
 -XX:SurvivorRatio=8 \
 -XX:MetaspaceSize=256m \
 -XX:MaxMetaspaceSize=256m \
 -XX:ParallelGCThreads=4 \
 -XX:+PrintGCDetails \
 -XX:+PrintTenuringDistribution \
 -XX:+PrintGCTimeStamps \
 -XX:+HeapDumpOnOutOfMemoryError \
 -XX:HeapDumpPath=/ \
 -Xloggc:/logs/gc.log \
 -XX:+UseGCLogFileRotation \
 -XX:NumberOfGCLogFiles=5 \
 -XX:+DisableExplicitGC \
 -XX:+UseConcMarkSweepGC \
 -XX:+UseParNewGC \
 -XX:+CMSParallelRemarkEnabled \
 -XX:+CMSClassUnloadingEnabled \
 -XX:LargePageSizeInBytes=128M \
 -XX:+UseFastAccessorMethods \
 -XX:+UseCMSInitiatingOccupancyOnly \
 -XX:CMSInitiatingOccupancyFraction=80 \
 -XX:SoftRefLRUPolicyMSPerMB=0 \
 -XX:+PrintClassHistogram \
 -XX:+PrintHeapAtGC \
 -XX:+UnlockDiagnosticVMOptions \
 -XX:+UnlockExperimentalVMOptions \
 -XX:+PrintFlagsFinal\
 -XX:GCLogFileSize=10M"
ENTRYPOINT ["java","-Duser.timezone=GMT+08","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]