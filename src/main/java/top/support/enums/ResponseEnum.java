package top.support.enums;

import top.support.enums.base.GeneralEnum;

public enum ResponseEnum implements GeneralEnum {
    SUCCESS("0000","Success.","成功。"),
    DEFAULT("9999","Default","失败"),
    ;

    private final String key;
    private final String value;
    private final String desc;

    ResponseEnum(String key, String value, String desc){
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
