package pl.fintech.challenge1.backend.domain.investment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvestmentAlreadyExistException extends RuntimeException {

    public InvestmentAlreadyExistException(String message) {
        super(message);
    }
}