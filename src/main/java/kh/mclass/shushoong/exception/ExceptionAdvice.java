package kh.mclass.shushoong.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kh.mclass.shushoong.api_payload.CommonResponseDto;
import kh.mclass.shushoong.api_payload.status_code.ErrorStatus;

@RestControllerAdvice(annotations = { RestController.class })
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = GeneralException.class)
	public ResponseEntity<Object> generalException(GeneralException e, HttpServletRequest request) {
		return handleExceptionInternal(e, null, request);
	}

	@ExceptionHandler
	public ResponseEntity<Object> exception(Exception e, WebRequest request) {
		return handleExceptionInternal(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY,
				ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(), request, e.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		LinkedHashMap<String, String> errors = new LinkedHashMap<>();
		e.getBindingResult().getFieldErrors().stream().forEach(fieldError -> {
			String fieldName = fieldError.getField();
			String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
			errors.merge(fieldName, errorMessage,
					(existingMessage, newMessage) -> String.join(", ", existingMessage, newMessage));
		});

		return handleExceptionInternalArgs(e, HttpHeaders.EMPTY, ErrorStatus._BAD_REQUEST, request, errors);
	}

	private ResponseEntity<Object> handleExceptionInternal(GeneralException e, HttpHeaders headers,
			HttpServletRequest request) {
		CommonResponseDto<Object> body = CommonResponseDto.onFailure(e.getErrorCode(), e.getErrorReason(), null);
		WebRequest webRequest = new ServletWebRequest(request);
		return super.handleExceptionInternal(e, body, headers, e.getHttpStatus(), webRequest);
	}

	private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorStatus errorStatus, HttpHeaders headers,
			HttpStatus status, WebRequest request, String errorPoint) {
		CommonResponseDto<String> body = CommonResponseDto.onFailure(errorStatus.getCode(), errorStatus.getMessage(),
				errorPoint);
		return super.handleExceptionInternal(e, body, headers, status, request);
	}

	private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers,
			ErrorStatus errorStatus, WebRequest request, Map<String, String> errorArgs) {
		CommonResponseDto<Map<String, String>> body = CommonResponseDto.onFailure(errorStatus.getCode(),
				errorStatus.getMessage(), errorArgs);
		return super.handleExceptionInternal(e, body, headers, errorStatus.getHttpStatus(), request);
	}
}
