package io.github.antraxo.order_service.order.domain.exception;

public class StatusUpdateNotAllowedException extends Exception {

    public StatusUpdateNotAllowedException(String message) {
        super(message);
    }
}
