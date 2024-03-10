package com.peppacatt.notes.springboottest.tool;

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
     * 清一色
     */
    public static void oneColorListen() {
        int len = (int) (Math.random() * 13 + 1);
        int[] mjNums = new int[len];

    }

//    private da2
}
