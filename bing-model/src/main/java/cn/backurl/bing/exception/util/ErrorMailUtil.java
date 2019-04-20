package cn.backurl.bing.exception.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 异常邮件
 *
 * @author zqx
 * @date 2018年7月11日
 */
public class ErrorMailUtil {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ErrorMailUtil.class);

    public static void send(Throwable e, String className, String methodName, String methodDesc, String params) {

        /*
         * 处理异常信息
         */
        String errorName = e.getClass().getName();
        String errorMsg = e.getMessage();
        StackTraceElement[] stackTrace = e.getStackTrace();
        int lineNumber = -1;
        // 记录错误的详细信息
        String errorDetails = "";
        if (stackTrace != null && stackTrace.length > 0) {
            // 获取目标方法在类中出现错误的行数（默认取第一行记录log中）
            lineNumber = stackTrace[0].getLineNumber();
            // 获取详细错误信息（用于邮件内容）
            for (StackTraceElement s : stackTrace) {
                errorDetails += "\tat " + s + "\r\n";
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDate = sdf.format(new Date());

        // 记录错误日志
        String logError = "{" + curDate + "} " + methodDesc + " happen Exception：" + errorName + "：" + errorMsg + " , The error line is : " + lineNumber + " , errorDetails：\r\n" + errorDetails;
        logger.error(logError);

//        // 发送异常邮件
//        String isSendExceptionMail = ChatConfigUtil.getString("isSendExceptionMail");
//        if (StringUtils.isNotBlank(isSendExceptionMail) && "true".equals(isSendExceptionMail)) {
//            StringBuffer sb = new StringBuffer();
//            sb.append("<p>系统时间：" + curDate + "</p>");
//            sb.append("<p>请求参数：" + params + "</p>");
//            sb.append("<p>异常类：" + className + "</p>");
//            sb.append("<p>异常方法：" + methodName + "</p>");
//            sb.append("<p>错误名称：" + errorName + "</p>");
//            sb.append("<p>错误信息：" + errorMsg + "</p>");
//            sb.append("<p>错误所在行数：" + lineNumber + "</p>");
//            sb.append("<p>错误详细信息：" + errorDetails + "</p></div>");
//            String emailContent = sb.toString();
//            String emailSubject = curDate + " - " + methodName + "：happen Exception";
////            String[] receivedMails = {"itfaisco@163.com"};
//            try {
//                MailsUtil.send(receivedMails, emailSubject, emailContent);
//            } catch (IOException e1) {
//                logger.error("发送异常邮件失败！");
//            }
//        }

    }
}
