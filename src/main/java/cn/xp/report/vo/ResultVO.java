package cn.xp.report.vo;

import java.io.Serializable;

/**
 * @author wanghf
 * @company 杭州尚尚签网络科技有限公司
 * @date 2016-12-05
 * @since 3.0
 */
public class ResultVO implements Serializable {

    private static final long serialVersionUID = -4361182696720848606L;

    public static final int SUCCESS_CODE = 10000;

    public static final String SUCCESS_DEFAULT_MESSAGE = "success";

    /**
     * 返回的参数
     */
    private int code;
    /**
     * 返回的描述信息
     */
    private String message;

    /**
     * 请求成功情况下返回的参数
     */
    private Object result;

    public ResultVO() {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_DEFAULT_MESSAGE;
    }

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(int code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultVO message(String message) {
        this.message = message;
        return this;
    }

    public static ResultVO createSuccessResult() {
        return new ResultVO(SUCCESS_CODE, SUCCESS_DEFAULT_MESSAGE);
    }

    public static ResultVO createSuccessResult(Object result) {
        return new ResultVO(SUCCESS_CODE, SUCCESS_DEFAULT_MESSAGE, result);
    }

    public static ResultVO createSuccessResult(Object result, Class<?> type) {
        return new ResultVO(SUCCESS_CODE, SUCCESS_DEFAULT_MESSAGE, result);
    }
}
