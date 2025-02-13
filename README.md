# 🏝 Agencia de Viajes - Viajes Seguros S.A.

Este proyecto es una **Plataforma Web Progresiva (PWA)** para la gestión de viajes, reservas y pagos en línea. Su objetivo es proporcionar una experiencia segura y fluida a los usuarios al momento de **buscar, reservar y cancelar viajes**, integrando un **sistema de pagos confiable** y cumpliendo con altos estándares de **seguridad**.

---

## 📌 Características Principales

✅ **Búsqueda de viajes** por fecha, destino y presupuesto.  
✅ **Reserva y cancelación** de viajes con gestión de disponibilidad.  
✅ **Integración con un sistema de pagos** seguro y confiable.  
✅ **Notificaciones** por correo sobre reservas y cancelaciones.  
✅ **Autenticación y seguridad** con cifrado de datos y validación de entradas.  

---

## 📌 Contexto del Caso de Estudio

En la era digital actual, la industria del turismo ha evolucionado con plataformas en línea que facilitan la **búsqueda, comparación y reserva** de viajes.  
La compañía **“Viajes Seguros S.A.”** busca ofrecer una solución robusta y segura que permita:

📌 **Búsqueda de viajes** mediante filtros de fecha, destino y presupuesto.  
📌 **Reserva de viajes** con verificación de disponibilidad y procesamiento de pagos.  
📌 **Cancelación de reservas** considerando políticas de reembolso.  
📌 **Notificaciones** por correo electrónico sobre estados de reserva y cancelación.  
📌 **Seguridad** con autenticación de usuarios, encriptación de datos y registro de actividad.  

---

## 📌 Requisitos Funcionales  

✔ **RF1 - Búsqueda de viajes:** Permitir a los usuarios buscar viajes por fecha, destino y precio.  
✔ **RF2 - Reserva de un viaje:** Verificar disponibilidad y registrar la reserva.  
✔ **RF3 - Cancelación de reserva:** Permitir la cancelación con condiciones de reembolso.  
✔ **RF4 - Confirmación y notificación:** Enviar notificaciones de reserva o cancelación por correo.  
✔ **RF5 - Integración con sistema de pagos:** Asegurar la verificación y procesamiento seguro de pagos.  

---

## 🔐 Requisitos de Seguridad  

🔒 **RS1 - Autenticación y autorización:** Solo usuarios autenticados pueden reservar o cancelar.  
🔒 **RS2 - Encriptación de datos:** Información personal y de pago se cifra en tránsito (HTTPS) y en reposo.  
🔒 **RS3 - Validación de entradas:** Protección contra ataques XSS, SQL Injection y otros vectores.  
🔒 **RS4 - Registro de actividades:** Registro de todas las transacciones para auditoría y detección de anomalías.  
🔒 **RS5 - Gestión de errores:** Manejo seguro de excepciones sin exposición de información sensible.  

---

## 🚀 Tecnologías Utilizadas

🔹 **Backend:** Spring Boot (Java)  
🔹 **Base de Datos:** MySQL (con Docker Compose)  
🔹 **Autenticación:** Spring Security + JWT  
🔹 **ORM:** Spring Data JPA  
🔹 **Pruebas:** JUnit 5, Mockito  

---

## ⚙️ Configuración y Ejecución  

### 🔹 1️⃣ **Clonar el repositorio**
```bash
    git clone https://github.com/usuario/agencia-viajes.git
    cd agencia-viajes
### 🔹 2️⃣  **Configurar variables de entorno**

Crear el archivo .env y definir las credenciales de la base de datos:

```bash
    MYSQL_ROOT_PASSWORD=root
    MYSQL_DATABASE=agencia_viajes
    MYSQL_USER=user
    MYSQL_PASSWORD=password

### 🔹 2️⃣  **Levantar la base de datos con Docker**
```bash
    docker-compose up -d
### 🔹 2️⃣  **Ejecutar la aplicación Spring Boot**
La API estará disponible en http://localhost:9090.
