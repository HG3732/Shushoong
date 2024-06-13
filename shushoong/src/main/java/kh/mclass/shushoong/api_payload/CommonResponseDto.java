package kh.mclass.shushoong.api_payload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import kh.mclass.shushoong.api_payload.status_code.BaseCode;
import kh.mclass.shushoong.api_payload.status_code.SuccessStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder
public class CommonResponseDto<T> {
	private final Boolean isSuccess;
    private final String code;
    private final String message;
    private T result;

    public static <T> CommonResponseDto<T> ok(T result) {
        return new CommonResponseDto<>(true, SuccessStatus._OK.getCode(),
                SuccessStatus._OK.getMessage(), result);
    }

    public static <T> CommonResponseDto<T> of(BaseCode code, T result) {
        return new CommonResponseDto<>(true, code.getCode(), code.getMessage(), result);
    }

    public static <T> CommonResponseDto<T> onFailure(String code, String message, T result) {
        return new CommonResponseDto<>(false, code, message, result);
    }
    
}
