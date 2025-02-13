# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Crea un directorio para la app
WORKDIR /app

# Copia el .jar generado por Maven al contenedor
COPY target/agenciaViajes-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
