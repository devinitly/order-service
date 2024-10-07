Zadanie techniczne w ramach rekrutacji do firmy Insert

Aby uruchomić aplikacje wykonujemy następujące kroki:

1. Pakujemy aplikacje za pomocą Mavena:

    ```mvn clean package```
2. Budujemy i uruchamiamy kontenery z aplikacją oraz MySQL za pomocą komendy:

   ```docker-compose up --build```

Aplikacja jest dostępna pod localhost:8080

Pod adresem http://localhost:8080/swagger-ui/index.html znajduje się dokumentacja API z dostępnymi operacjami
