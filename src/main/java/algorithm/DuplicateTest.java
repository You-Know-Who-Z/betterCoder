package algorithm;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;
import java.util.BitSet;

/**
 * N 长数组查重
 * 边界：
 * 1. 暂未考虑无法将原始数组读进内存的情况；
 * 无边界读思路：文件逐行读、hash分片
 * 2. 暂未考虑需计算重复元素数量的情况；
 * 元素计数思路：单独存储重复的元素
 * 实现思路：
 * 1. 采取hash法 实现O(n);
 * 2. 采取bigMap节省内存占用;
 * 附加题：
 * 1. 有序状态的数组，即只有相邻的元素会重复
 *
 * @author zz
 */
public class DuplicateTest {
    /**
     * 无序查重
     *
     * @param array 无序数组
     */
    public static void shuffleDuplicate(int[] array) {
        BitSet bitSet = new BitSet();
        for (int i : array) {
            if (bitSet.get(i)) {
                System.out.printf("重复元素: %s\n", i);
            } else {
                bitSet.set(i, true);
            }
        }
    }

    /**
     * 有序查重
     *
     * @param array 有序数组
     */
    public static void orderedDuplicate(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1 && array[i] == array[i + 1]) {
                System.out.printf("重复元素: %s\n", array[i]);
            }
        }
    }

    /**
     * 初始化数据
     *
     * @param width
     * @param sort
     * @return
     */
    public static int[] initData(int width, boolean sort) {
        int[] arr = new int[width];
        for (int i = 0; i < width; i++) {
            arr[i] = RandomUtil.randomInt(width);
        }
        if (sort) {
            Arrays.sort(arr);
        }
        return arr;
    }

    public static void main(String[] args) {
        shuffleDuplicate(initData(1000, false));
        orderedDuplicate(initData(1000, true));
    }
}
