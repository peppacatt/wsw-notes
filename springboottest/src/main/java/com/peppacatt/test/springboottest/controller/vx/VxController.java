package com.peppacatt.test.springboottest.controller.vx;

import cn.hutool.core.util.StrUtil;
import com.peppacatt.test.springboottest.vo.TextMsg;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * vx验证
 */
@RestController
@RequestMapping("/vx")
public class VxController {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VxController.class);

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    /**
     * vx验证接口, 默认路径
     *
     * @param signature signature
     * @param timestamp timestamp
     * @param nonce     nonce
     * @param echostr   echostr
     * @return String
     */
    @GetMapping()
    public String verify(String signature, String timestamp, String nonce, String echostr) {
        String vxToken = "peppacatt";
        // 1 将token, timestamp, nonce三个参数按照字典序排序
        List<String> vxParamList = Arrays.asList(vxToken, timestamp, nonce);
        Collections.sort(vxParamList);
        // 2 将三个参数字符串拼接成一个一个字符串进行sha1加密
        StringBuilder vxParamSb = new StringBuilder();
        for (String item : vxParamList) {
            vxParamSb.append(item);
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            byte[] digest = messageDigest.digest(vxParamSb.toString().getBytes());
            // 转为与signature相同的16进制字符串
            StringBuilder hexSb = new StringBuilder();
            for (byte item : digest) {
                String highFourBit = Integer.toHexString((item >> 4) & 15);
                String lowFourBit = Integer.toHexString(item & 15);
                hexSb.append(highFourBit);
                hexSb.append(lowFourBit);
            }
            // 3 将加密的后字符串与signature对比, 如果相同则表示该请求来自微信
            LOGGER.info("signature: {}\n,hexSb: {}, bytes: {}\n, echostr: {}\n", signature, hexSb, digest, echostr);
            if (StrUtil.isNotEmpty(signature) && signature.equals(hexSb.toString())) {
                return echostr;
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("MessageDigest error: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 接受普通消息, 以字节流读取的方式
     *
     * @param httpServletRequest httpServletRequest
     * @return String
     */
    @PostMapping("/plainMsg")
    public String plainMsg(HttpServletRequest httpServletRequest) {
        LOGGER.info("接受普通消息, 以字节流读取的方式");
        try {
            ServletInputStream servletInputStream = httpServletRequest.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = servletInputStream.read(bytes)) != -1) {
                LOGGER.info(new String(bytes, 0, len));
            }
        } catch (IOException e) {
            LOGGER.error("ServletInputStream: {}", e.getMessage());
        }
        return "";
    }

    /**
     * 接受普通消息, 封装到map中
     *
     * @param httpServletRequest httpServletRequest
     * @return String
     */
    @PostMapping("/plainMsgV1")
    public String plainMsgV1(HttpServletRequest httpServletRequest) {
        LOGGER.info("接受普通消息, 封装到map中");
        Map<String, String> map = new HashMap<>();
        try {
            ServletInputStream servletInputStream = httpServletRequest.getInputStream();
            SAXReader saxReader = new SAXReader();
            // 读取request输入流, 获得Document对象
            Document document = saxReader.read(servletInputStream);
            // 获得root节点
            Element rootElement = document.getRootElement();
            // 获取所有子节点
            for (Element element : rootElement.elements()) {
                map.put(element.getName(), element.getStringValue());
            }
        } catch (IOException e) {
            LOGGER.error("ServletInputStream: {}", e.getMessage());
        } catch (DocumentException e) {
            LOGGER.error("Document: {}", e.getMessage());
        }
        LOGGER.info("a plain message was received: {}", map);
        return "";
    }

    @PostMapping("/plainMsgV2")
    public String plainMsgV2(HttpServletRequest httpServletRequest) {
        LOGGER.info("接受普通消息, 封装到map中");
        Map<String, String> map = new HashMap<>();
        try {
            ServletInputStream servletInputStream = httpServletRequest.getInputStream();
            SAXReader saxReader = new SAXReader();
            // 读取request输入流, 获得Document对象
            Document document = saxReader.read(servletInputStream);
            // 获得root节点
            Element rootElement = document.getRootElement();
            // 获取所有子节点
            for (Element element : rootElement.elements()) {
                map.put(element.getName(), element.getStringValue());
            }
        } catch (IOException e) {
            LOGGER.error("ServletInputStream: {}", e.getMessage());
        } catch (DocumentException e) {
            LOGGER.error("Document: {}", e.getMessage());
        }
        LOGGER.info("a plain message was received: {}", map);

        // 被动回复用户消息
        return getReplyMsg(map);
    }

    /**
     * 封装返回的消息
     *
     * @param map map
     * @return String
     */
    private String getReplyMsg(Map<String, String> map) {
        TextMsg textMsg = new TextMsg();
        textMsg.setToUserName(map.get("FromUserName"));
        textMsg.setToUserName(map.get("ToUserName"));
        textMsg.setMsgType("text");
        textMsg.setContent("hello hello hello");
        textMsg.setCreateTime(System.currentTimeMillis() / 1000);

        // Xstream将Java对象转为xml字符串
        XStream xStream = new XStream();
        // 下面的用法需要在TextMsg类上添加注解
        xStream.processAnnotations(TextMsg.class);
        return xStream.toXML(textMsg);
    }
}
