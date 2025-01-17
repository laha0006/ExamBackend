package dev.tolana.exambackend.exception;

import dev.tolana.exambackend.delivery.exception.DeliveryDroneAlreadyAssignedException;
import dev.tolana.exambackend.delivery.exception.DeliveryNeedsDroneToFinishException;
import dev.tolana.exambackend.delivery.exception.DeliveryNotFoundException;
import dev.tolana.exambackend.drone.exception.DroneNotFoundException;
import dev.tolana.exambackend.drone.exception.DroneNotInServiceException;
import dev.tolana.exambackend.drone.exception.NoStationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<ErrorResponse> deliveryNotFound(DeliveryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage())
        );
    }

    @ExceptionHandler(DeliveryNeedsDroneToFinishException.class)
    public ResponseEntity<ErrorResponse> deliveryNeedsDroneToFinish(DeliveryNeedsDroneToFinishException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage())
        );
    }

    @ExceptionHandler(DroneNotInServiceException.class)
    public ResponseEntity<ErrorResponse> droneNotInService(DroneNotInServiceException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage())
        );
    }

    @ExceptionHandler(DroneNotFoundException.class)
    public ResponseEntity<ErrorResponse> droneNotFound(DroneNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage())
        );
    }

    @ExceptionHandler(DeliveryDroneAlreadyAssignedException.class)
    public ResponseEntity<ErrorResponse> deliveryDroneAlreadyAssigned(DeliveryDroneAlreadyAssignedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage())
        );
    }

    @ExceptionHandler(NoStationException.class)
    public ResponseEntity<ErrorResponse> noStation(NoStationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage())
        );
    }

}
