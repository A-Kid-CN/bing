package cn.backurl.bing.exception;


import cn.backurl.bing.result.ResultCode;

/**
* <p>
* 系统异常
* </p>
* @author: akid
* @create: 2019-04-20 22:47
*/
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -6640843483099484882L;
	
	protected ResultCode code; // 异常错误码
	protected String message; // 异常错误信息

	public SystemException(String message) {
		super(message);
		this.code = ResultCode.SystemException;// 默认未知错误
		this.message = message;
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 *            源错误信息
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResultCode.SystemException;// 默认未知错误
	}

	public SystemException(ResultCode code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public SystemException(ResultCode code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ResultCode getCode() {
		return code;
	}

	public void setCode(ResultCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
