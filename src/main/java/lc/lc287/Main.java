package lc.lc287;

public class Main {
}

class Solution {
    public int findDuplicate(int[] nums) {
        int duplicate = 0;
        int n = nums.length - 1;  // 数字范围是 [1, n]

        // 遍历每一位二进制位
        for (int bit = 0; bit < 32; bit++) {
            int mask = 1 << bit;  // 当前位的掩码
            int count = 0;        // 数组中该位为 1 的个数
            int expected = 0;     // [1, n] 范围内该位为 1 的个数

            // 统计数组中该位为 1 的个数
            for (int num : nums) {
                if ((num & mask) != 0) {
                    count++;
                }
            }

            // 统计 [1, n] 范围内该位为 1 的个数
            for (int i = 1; i <= n; i++) {
                if ((i & mask) != 0) {
                    expected++;
                }
            }

            // 如果 count > expected，说明重复数字的该位是 1
            if (count > expected) {
                duplicate |= mask;  // 将该位设置为 1
            }
        }

        return duplicate;
    }
}/**
 * 位运算（状态表示）
 * 但是这样的话集合只能表示32位或64位数字，而题目要求 n < 100000
 */
class Solution1 {
    public int findDuplicate(int[] nums) {
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            int offset = nums[i];
            // 判断是否在集合 s 中
            if (((s >> offset) & 1) == 1) return offset;
            // 将元素添加到集合 s
            s = s | (1 << offset);
        }
        return 0;
    }
}