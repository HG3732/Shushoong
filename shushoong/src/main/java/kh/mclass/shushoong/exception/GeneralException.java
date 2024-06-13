package kh.mclass.shushoong.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import kh.mclass.shushoong.api_payload.status_code.ErrorStatus;

@Getter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException {
	private final ErrorStatus errorStatus;

    public String getErrorCode() {
    	return errorStatus.getCode();
    }
    
    public String getErrorReason() {
        return errorStatus.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return errorStatus.getHttpStatus();
    }
}
