package top.support.dto;

import lombok.Data;
import top.support.enums.ResponseEnum;
import top.support.enums.base.GeneralEnum;

@Data
public class GeneralRespDTO<T> {
    private String code;
    private String desc;
    private String msg;
    private T data;

    GeneralRespDTO(String code,String desc,String msg,T data){
        this.code = code;
        this.desc = desc;
        this.msg = msg;
        this.data = data;
    }

    public GeneralRespDTO(GeneralEnum enums, T data){
        this.code = enums.getKey();
        this.desc = enums.getDesc();
        this.msg = enums.getValue();
        this.data = data;
    }

    public GeneralRespDTO(GeneralEnum enums, String msg){
        this.code = enums.getKey();
        this.desc = enums.getDesc();
        this.msg = msg;
    }

    public static <T> GeneralRespDTO<T> success(T data){
        return new GeneralRespDTO<>(ResponseEnum.SUCCESS,data);
    }

    public static <T> GeneralRespDTO<T> fail(ResponseEnum enums,T data){
        return new GeneralRespDTO<>(enums,data);
    }

    public static <T> GeneralRespDTO<T> fail(ResponseEnum enums,String msg){
        return new GeneralRespDTO<T>(enums,msg);
    }

}
