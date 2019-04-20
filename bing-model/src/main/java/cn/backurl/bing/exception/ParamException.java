package cn.backurl.bing.exception;


import cn.backurl.bing.result.ResultCode;

/**
 * 参数验证异常
 */
public class ParamException extends SystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8004975722028133384L;

	public ParamException(String message) {
		super(message);
		this.code = ResultCode.ParamException; // 参数验证异常
	}

	public ParamException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResultCode.ParamException; // 参数验证异常
	}
}
