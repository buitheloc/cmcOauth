package com.cmc.demo.oauth.util;

import com.cmc.demo.oauth.model.enumerate.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    /**
     * The result of the response, true if successful and false otherwise
     */
    private boolean success;
    /**
     * Custom response code
     */

    private int code;

    /**
     * Response data
     */
    private T data;

    /**
     * Response message
     */
    private String message;


    public static <T> BaseResponse<T> success(ResponseCode responseCode, String message, T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .code(responseCode.getValue())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> success(ResponseCode responseCode, T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .code(responseCode.getValue())
                .message(String.valueOf(responseCode.getValue()))
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> success(ResponseCode responseCode) {
        return BaseResponse.<T>builder()
                .success(true)
                .code(responseCode.getValue())
                .message(String.valueOf(responseCode.getValue()))
                .data(null)
                .build();
    }

    public static <T> BaseResponse<T> error(ResponseCode responseCode) {
        return BaseResponse.<T>builder()
                .success(false)
                .code(responseCode.getValue())
                .message(String.valueOf(responseCode.getValue()))
                .data(null)
                .build();
    }

}