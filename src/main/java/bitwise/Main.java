package bitwise;

public class Main {
    public static void main(String[] args) {
        // 二进制字面量（0b 前缀）
        int binary = 0b1010;  // 二进制 1010，十进制 10
        System.out.println(binary);  // 输出: 10
        String binaryString = Integer.toBinaryString(binary);
        System.out.println("binaryString = " + binaryString);
    }
}
