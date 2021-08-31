package com.example.exception.dtos;

import java.util.List;

public class ErrorResponse {

    private String statusCode;

    private String requestUrl;

    private String message;

    private String resultCode;

    private List<ErrorInfo> errorInfoList;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<ErrorInfo> getErrorList() {
        return errorInfoList;
    }

    public void setErrorList(List<ErrorInfo> errorInfoList) {
        this.errorInfoList = errorInfoList;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", requestUrl='" + requestUrl + '\'' +
                ", message='" + message + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errorList=" + errorInfoList +
                '}';
    }
}
