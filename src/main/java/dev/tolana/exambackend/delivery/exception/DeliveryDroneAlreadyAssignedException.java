package dev.tolana.exambackend.delivery.exception;

public class DeliveryDroneAlreadyAssignedException extends RuntimeException {
    public DeliveryDroneAlreadyAssignedException(String message) {
        super(message);
    }
}
