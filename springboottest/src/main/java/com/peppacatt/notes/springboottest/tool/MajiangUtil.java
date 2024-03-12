package com.peppacatt.notes.springboottest.tool;

import java.util.Random;

public class MajiangUtil {
    /**
     * 麻将
     */
    private static class Majiang {
        private static final String[] nums = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};

        private String name;

        private int num;

        private MajiangColor majiangColor;

        private enum MajiangColor {
            WAN("万"), TONG("筒"), TIAO("条");
            private final String color;

            private MajiangColor(String color) {
                this.color = color;
            }

            public String getColor() {
                return color;
            }
        }

        /**
         * 生成一个麻将对象
         *
         * @param num          麻将序号
         * @param majiangColor 麻将花色
         * @return 一个麻将
         */
        public Majiang(int num, MajiangColor majiangColor) {
            this.num = num;
            this.majiangColor = majiangColor;
            this.name = String.format("%s%s", nums[num + 1], majiangColor.getColor());
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 获取一个1到13中的数字
     *
     * @return 获取一个1到9中的数字
     */
    private int getNumOf9() {
        return (int) (Math.random() * 9) + 1;
    }

    /**
     * 清一色
     */
    public static void oneColorListen() {
        int len = (int) (Math.random() * 13) + 2;
    }

    /**
     * 1对将牌:11
     *
     * @return 1对将牌
     */
    private int[] da2jiang() {
        int num = getNumOf9();
        return new int[]{num, num};
    }

    /**
     * 1坎牌:111
     *
     * @return 1坎牌
     */
    private int[] da3equal() {
        int num = getNumOf9();
        return new int[]{num, num, num};
    }

    /**
     * 3个连续的牌:123
     *
     * @return 3个连续的牌
     */
    private int[] da3Sequence() {
        int num = getNumOf9();
        int[] nums = new int[3];
        for (int i = 0; i < 3; i++) {
            if (num + 3 > 13) {
                nums[i] = num - i;
            } else {
                nums[i] = num + i;
            }
        }
        return nums;
    }

    /**
     * 生成一幅牌
     */
    private void combination(int len) {

    }

    private void winValidator(int[] cards) {

    }
}
