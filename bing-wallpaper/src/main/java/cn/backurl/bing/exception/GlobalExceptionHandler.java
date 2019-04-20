package cn.backurl.bing.exception;

import cn.backurl.bing.result.AjaxResult;
import cn.backurl.bing.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 全局异常处理器
 * </p>
 *
 * @author: akid
 * @create: 2019-04-20 21:36
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handle(Exception e){

        // 系统异常
        if (e instanceof SystemException) {
            return AjaxResult.failure(ResultCode.SystemException, ResultCode.SystemException.getMsg());
        }

        // 错误请求方法异常
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return AjaxResult.failure(ResultCode.ERROR_REQUEST_METHOD, ResultCode.ERROR_REQUEST_METHOD.getMsg());
        }
        
        log.error(e.toString());
        // 未知异常
        return AjaxResult.failure(ResultCode.SystemException);
    }
}
