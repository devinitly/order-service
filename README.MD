# Order Service

## Wymagania
* Java 17
* Apache Maven 3.6.3
* Docker 27.2.0

## Instrukcja

1. Pakujemy aplikacje za pomocą Mavena:
   ```bash
   mvn clean package
   ```
2. Budujemy i uruchamiamy kontenery z aplikacją oraz MySQL za pomocą komendy:
   ```bash
   docker-compose up --build
   ```

Aplikacja jest dostępna pod localhost:8080

Pod adresem http://localhost:8080/swagger-ui/index.html znajduje się dokumentacja API z dostępnymi operacjami
