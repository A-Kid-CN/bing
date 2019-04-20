package cn.backurl.bing.exception;


import cn.backurl.bing.result.ResultCode;

/**
 * 空指针异常
 */
public class SystemNullPointerException extends SystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1986515981743755213L;

	public SystemNullPointerException(String message) {
		super(message);
		this.code = ResultCode.SystemNullPointerException; // 空指针异常
	}

	public SystemNullPointerException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResultCode.SystemNullPointerException; // 空指针异常
	}

}
