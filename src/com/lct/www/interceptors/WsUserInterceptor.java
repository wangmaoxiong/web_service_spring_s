package com.lct.www.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2019/2/18 0018.
 * 自定义拦截器——————用于验证客户端的账号密码
 */
public class WsUserInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private static final Logger logger = Logger.getGlobal();//日志记录器

    /**
     * 构造器中必须使用 super 设置拦截器发生的时刻/阶段
     * org.apache.cxf.phase.Phase 中提供了大量的常量表示拦截器发生的时刻
     */
    public WsUserInterceptor() {
        super(Phase.PRE_PROTOCOL);//协议前进行拦截
    }

    /**
     * 客户端传来的 soap 消息先进入拦截器这里进行处理，客户端的账目与密码消息放在 soap 的消息头<head></head>中，类似如下：
     * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Header><userInfo><name>admin</name><password>123456</password></userInfo></soap:Header><soap:Body><ns2:getStudentById xmlns:ns2="http://web_service.lct.com/"><sId>11</sId></ns2:getStudentById></soap:Body></soap:Envelope>
     * 现在只需要解析其中的 <head></head>标签，如果解析验证成功，则放行，否则这里直接抛出异常，服务端不会再往后运行，客户端也会跟着抛出异常，得不到正确结果
     *
     * @param message
     * @throws Fault
     */
    @Override
    public void handleMessage(SoapMessage message) throws Fault {

        /**org.apache.cxf.headers.Header
         * QName ：xml 限定名称，客户端设置头信息时，必须与服务器保持一致，否则这里返回的 header 为null，则永远通不过的
         */
        Header header = message.getHeader(new QName("userInfo"));
        if (header != null) {
            Element rootEle = (Element) header.getObject();//获取根元素，即 <userInfo>标签
            String name = rootEle.getElementsByTagName("name").item(0).getTextContent();//获取元素值
            String password = rootEle.getElementsByTagName("password").item(0).getTextContent();//获取元素值
            /**为了演示简单，直接写死了，实际中建议放在配置文件中提供配置*/
            if ("admin".equals(name) && "123456".equals(password)) {
                logger.info("webService 服务端自定义拦截器验证通过....");
                return;//放行
            }
        }
        /**
         * 验证失败，客户端没有提供账号密码、或者提供账号密码错误
         */
        logger.info("webService 服务端自定义拦截器验证失败....");
        throw new Fault(new RuntimeException("请提供正确的用户名和密码!格式：<Header><userInfo><name>xxx</name><password>xxx</password></userInfo></Header>"));
    }
}
