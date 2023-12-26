package com.peppacatt.notes.springboottest.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * <Content><![CDATA[你好]]></Content>
 * </xml>
 * 封装文本消息
 */
@XStreamAlias("xml")
@Data
public class TextMsg {
    /**
     * 因为最后回复到vx服务器上的xml数据的节点都必须是大写开头, 但是Java的规范命名的属性又必须是小写的,
     * 所以要使用注解@XStreamAlias("xxx"), 使该java对象调用XStream API转为xml字符串之后, xml的
     * 节点名称是符合vx服务器xml返回消息规范的
     */
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private Long createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("Content")
    private String content;
}
