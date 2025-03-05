package com.tr.innova.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorLogsDto {

    private Long id;
    private Instant timestamp;
    private String logger;
    private String message;
    private String method;
    private String line;
    private String exception;

}
