package com.emma.jpamanytomany.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private String responseCode;
    private String Message;
    private Object object;

    public ResponseMessage(String responseCode, String message) {
        this.responseCode = responseCode;
        Message = message;
    }

}
