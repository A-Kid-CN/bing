package cn.backurl.bing.result;

/**
 * ajax数据结构
 *
 * @author akid
 * <p>
 * 2018年2月1日
 */
public class AjaxResult {

    /**
     * 状态码
     */
    private int statusCode = ResultCode.SUCCESS.getCode();

    /**
     * 请求返回信息
     */
    private String message = "";

    /**
     * 请求结果
     */
    private Object data = null;

    /**
     * Instantiates a new Ajax result.
     */
    private AjaxResult() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * <p>
     * 功能描述: 执行成功
     * </p>
     *
     * @param
     * @Author: akid
     * @Date: 2019-04-20 22:16
     * @Return: cn.backurl.bing.result.AjaxResult
     */
    public static AjaxResult success() {
        return success(null);
    }

    /**
     * <p>
     * 功能描述: 执行成功
     * </p>
     *
     * @param
     * @Author: akid
     * @Date: 2019-04-20 22:16
     * @Return: cn.backurl.bing.result.AjaxResult
     */
    public static AjaxResult success(Object data) {
        return success("", data);
    }

    /**
     * <p>
     * 功能描述: 执行成功
     * </p>
     *
     * @param
     * @Author: akid
     * @Date: 2019-04-20 22:16
     * @Return: cn.backurl.bing.result.AjaxResult
     */
    public static AjaxResult success(String message, Object data) {
        AjaxResult result = new AjaxResult();
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 获取错误结果模板
     *
     * @return AjaxResult
     */
    public static AjaxResult failure() {
        return failure(ResultCode.FAILURE);
    }

    /**
     * 获取错误结果模板
     *
     * @return AjaxResult
     */
    public static AjaxResult failure(ResultCode resultCode) {
        return failure(resultCode, resultCode.getMsg());
    }

    /**
     * 获取错误结果模板
     *
     * @param message 请求返回信息
     * @return AjaxResult
     */
    public static AjaxResult failure(ResultCode statusCode, String message) {
        AjaxResult result = new AjaxResult();
        result.setStatusCode(statusCode.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 获取错误结果模板
     *
     * @param message 请求返回信息
     * @param data     请求结果
     * @return AjaxResult
     */
    public static AjaxResult failure(ResultCode statusCode, String message, Object data) {
        AjaxResult result = new AjaxResult();
        result.setStatusCode(statusCode.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    @Override
    public String toString() {
        return "AjaxResult [statusCode=" + statusCode + ", message=" + message + ", data=" + data + "]";
    }
}
