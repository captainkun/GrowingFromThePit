import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

/**
 * @author qukun
 * @date 2020/11/7
 * @description 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C02_AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        //[9]
        //[1,9,9,9,9,9,9,9,9,9]
        ListNode l1 = new ListNode();
        l1.val = 2;
        l1.next = new ListNode(4);
        ListNode l2 = new ListNode();
        l2.val = 5;
        l2.next = new ListNode(6, new ListNode(4));

        Instant start = Instant.now();
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println("耗时(ms)：" + Duration.between(start, Instant.now()).toMillis());
        System.out.println(listNode);

        Instant start2 = Instant.now();
        ListNode listNode2 = addTwoNumbers_method2(l1, l2);
        System.out.println("耗时(ms)：" + Duration.between(start2, Instant.now()).toMillis());
        System.out.println(listNode2);
    }

    /**
     * 方法1
     * @param l1 入参1
     * @param l2 入参2
     * @return 结果
     */
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 获取ListNode内的值，按规则组装成正确的结果值
        BigDecimal num1 = getNum(l1, "");
        BigDecimal num2 = getNum(l2, "");

        // 相加后的结果
        BigDecimal result = num1.add(num2);

        // 对结果值进行规格封装到
        return getListNode(result);
    }

    /**
     * 方法1
     * @param l1 入参1
     * @param l2 入参2
     * @return 结果
     */
    private static ListNode addTwoNumbers_method2(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        //    输入：(2 -> 4) + (5 -> 6 -> 4)
        //    输出：7 -> 0 -> 5
        //    原因：42 + 465 = 507
        while(l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return root.next;
    }

    private static BigDecimal getNum(ListNode listNode, String numStr) {
        int val = listNode.val;
        ListNode next = listNode.next;
        numStr = numStr == null ? "" : numStr;
        if (next != null) {
            int nextVal = next.val;
            if ("".equals(numStr)) {
                numStr = numStr + val + nextVal;
            } else {
                numStr = numStr + nextVal;
            }
            return getNum(next, numStr);
        } else if ("".equals(numStr)) {
            numStr = numStr + val;
        }

        char[] chars = numStr.toCharArray();
        int length = chars.length;
        StringBuilder numSortStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char num = chars[length - 1 - i];
            numSortStr.append(num);
        }

        return new BigDecimal(numSortStr.toString());
    }

    private static ListNode getListNode(BigDecimal result) {
        String resultStr = "" + result;
        char[] chars = resultStr.toCharArray();
        int length = chars.length;
        ListNode newListNode = new ListNode(chars[length - 1] - '0');
        for (int i = 1; i < length; i++) {
            ListNode nextListNode = new ListNode();
            nextListNode.val = chars[length - i - 1] - '0';
            ListNode nextNode = getNextNode(newListNode);
            nextNode.next = nextListNode;
        }
        return newListNode;
    }

    private static ListNode getNextNode(ListNode listNode) {
        ListNode next = listNode.next;
        if (next != null) {
            return getNextNode(next);
        }
        return listNode;
    }


}
