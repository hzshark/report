package cn.xp.report.common.exception;

public class BizException extends Exception {

    private Integer code;
    private String message;

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return this.code+":"+this.getMessage();
    }
}
