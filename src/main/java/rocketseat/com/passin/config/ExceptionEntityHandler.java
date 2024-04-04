package rocketseat.com.passin.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;

@ControllerAdvice //captura exceções lançadas pelos controllers
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class) //lida com exceção
    public ResponseEntity handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
