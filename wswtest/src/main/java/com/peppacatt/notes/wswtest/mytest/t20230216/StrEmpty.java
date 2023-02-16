package com.peppacatt.notes.wswtest.mytest.t20230216;

import cn.hutool.core.util.StrUtil;

public class StrEmpty {
    public static void main(String[] args) {
        System.out.println(StrUtil.isEmpty(""));
        System.out.println(StrUtil.isEmpty(" "));
        System.out.println(StrUtil.isEmpty("  "));
        System.out.println(StrUtil.isEmpty(null));
        System.out.println(StrUtil.isBlank(""));
        System.out.println(StrUtil.isBlank(" "));
        System.out.println(StrUtil.isBlank("  "));
        System.out.println(StrUtil.isBlank(null));
    }
}
