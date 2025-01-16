package dev.tolana.exambackend.drone.exception;

public class DroneNotInServiceException extends RuntimeException {
    public DroneNotInServiceException(String message) {
        super(message);
    }
}
