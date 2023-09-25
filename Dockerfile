FROM eclipse-temurin:17
COPY /zajavka_ogarnizerAPI/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]