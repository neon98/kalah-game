package com.kalah.model.dto;

public class ApiResponse<T> {
    private T data = null;
    private Error error = null;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(Error error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
