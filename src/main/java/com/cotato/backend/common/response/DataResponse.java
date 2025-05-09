package com.cotato.backend.common.response;

import com.cotato.backend.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataResponse<T> {
    private final boolean isSuccess;
    private final String code;
    private final String message;
    private final T results;

    public static <T> DataResponse<T> success(T data) {
        return new DataResponse<>(true, "REQUEST_OK", "request succeeded", data);
    }

    public static <T> DataResponse<T> failure(ErrorCode errorCode) {
        return new DataResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }
}
