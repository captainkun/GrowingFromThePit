import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qu.kun
 * @date 2020/11/10
 * @description 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C05_LongestPalindrome {
    // 回文的意思是正着念和倒着念一样，如：上海自来水来自海上
    public static void main(String[] args) {
        System.out.println(handle("babad"));
        System.out.println(handle("cbbd"));
        System.out.println(handle("bacdfadb"));
        System.out.println(handle("a"));
        System.out.println(handle("ac"));
        System.out.println(handle("aacabdkacaa"));
        System.out.println(handle("aacaabdbacaa"));
        System.out.println(handle("ccc"));
        System.out.println(handle("dasfdcccxzc"));
    }

    /**
     * 方法一：
     * 执行用时：885 ms, 在所有 Java 提交中击败了5.00%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了51.91%的用户
     * @param s 字符串入参
     * @return 回文字符串
     */
    private static String handle(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int longestLength = 0;
        String longestStr = "";
        StringBuilder sb = new StringBuilder();
        Map<Object, List<Integer>> charAndIndexMap = new HashMap<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            int sbLength = sb.length();
            List<Integer> indexList = charAndIndexMap.get(aChar);
            if (indexList == null) {
                List<Integer> newIndexList = new ArrayList<>();
                newIndexList.add(i);
                charAndIndexMap.put(aChar, newIndexList);
            } else {
                // 对当前字符之前出现过的每次索引遍历，截取每一个出现的地方和现在的做比较
                StringBuilder reverseStringBuilder = new StringBuilder();
                for (Integer index : indexList) {
                    if (i - index + 1 > longestLength) {
                        String str = sb.substring(index, sbLength) + aChar;
                        String reverse = reverseStringBuilder.append(str).reverse().toString();
                        int strLength = str.length();
                        if (reverse.equals(str) && strLength > longestLength) {
                            longestLength = strLength;
                            longestStr = str;
                        }
                        reverseStringBuilder.delete(0, reverseStringBuilder.length());
                    }
                }
                indexList.add(i);
            }

            sb.append(aChar);
        }

        return longestStr.length() > 0 ? longestStr : s.charAt(0) + "";
    }



}
