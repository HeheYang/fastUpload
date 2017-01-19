package fastUpload.util;

/**
 * Created by HEHE on 2016/12/13.
 */
public class BizResponse {
    /**
     * 状态码
     */
    private int code=0;
    /**
     * 消息
     */
    private String message="";

    /**
     * 是否成功
     */
    private  boolean success =true;
    /**
     * 结果集
     */
    private Object result;


    public BizResponse(int code, String message, Object result,boolean success) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.success = success;
    }

    public BizResponse() {

    }

    public BizResponse(Object result) {
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



}

