package com.peppacatt.test.springboottest.controller.vx;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @GetMapping("/verify")
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
}
