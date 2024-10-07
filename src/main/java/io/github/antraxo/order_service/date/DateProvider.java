package io.github.antraxo.order_service.date;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateProvider {

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
