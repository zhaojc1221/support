package top.support.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralReqDTO<T> implements Serializable {
    private String requestType;
    private T data;
}
