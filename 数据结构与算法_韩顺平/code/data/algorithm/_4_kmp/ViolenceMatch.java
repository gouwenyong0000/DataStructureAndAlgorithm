package data.algorithm._4_kmp;

/**
 * 现在要判断 str1 是否含有 str2, 如果存在， 就返回第一次出现的位置, 如果没有， 则返回-1
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        //测试暴力匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
//        String str2 = "尚硅谷你尚硅你~";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    /**
     * 现在要判断 str1 是否含有 str2, 如果存在， 就返回第一次出现的位置, 如果没有， 则返回-1
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int violenceMatch(String str1, String str2) {

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int i = 0;//str1 位置记录
        int j = 0;//str2位置记录

        while (i < str1.length() && j < str2.length()) {

            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1; // str1 恢复到当前匹配前位置并向后移动一位
                j = 0; // str2  重头开始
            }
        }

        //判断是否匹配成功
        if (j == str2.length()) {
            return i - j;
        } else {
            return -1;
        }
    }
}
