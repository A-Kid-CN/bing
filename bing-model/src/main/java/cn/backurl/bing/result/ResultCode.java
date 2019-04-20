package cn.backurl.bing.result;


/**
 * Ajax请求状态码枚举类
 * @author akid
 *
 * 2017年12月1日
 */
public enum ResultCode {
	/**
	 * 系统异常.
	 */
	SystemException(100, "系统异常"),
	/**
	 * 成功.
	 */
	SUCCESS(200, "执行成功"),
	/**
	 * 失败.
	 */
	FAILURE(400, "执行失败"),
	/**
	 * 未验证身份
	 */
	NoAuthentication (401, "未验证身份"),
	/**
	 * 失败:没有权限
	 */
	NoPermission (403, "没有权限！"),
	/**
	 * 失败：登录信息过期！
	 */
	IdentityOverdue(405, "登录信息过期！"),

	/**
	 * 失败：请求方法错误
	 */
	ERROR_REQUEST_METHOD(406, "错误的请求方法"),

	/**
	 * 空指针异常
	 */
	SystemNullPointerException(101, "空指针异常"),
	/**
	 * IO异常.
	 */
	SystemIOException(102, "IO异常"),

	/**
	 * 参数验证错误.
	 */
	ParamException(103, "参数验证错误");


	private int code;
	private String msg;

	ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public static ResultCode getMsgByCode(int code) {
		for (ResultCode ec : ResultCode.values()) {
			if (ec.getCode() == (code)) {
				return ec;
			}
		}
		return null;
	}
}
