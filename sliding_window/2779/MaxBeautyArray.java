import java.util.*;

public class MaxBeautyArray {
    public void showRanges(List<int[]> ranges) {
        for (int[] range : ranges) {
            System.out.print("[");
            int len = range.length;
            for (int r : range) {
                if ((len--) > 1) {
                    System.out.print(r + ",");
                } else {
                    System.out.print(r);
                }
            }
            System.out.print("]");
        }
        System.out.println();
    }

    public void dumpArray(int [] array) {
        System.out.print("[" + array[0] + ", " + array[1] + "]");
    }

    public int maximumBeauty(int[] numbers, int k) {
        //用來存儲 [numbers[i] - k, numbers[i] + k]
        List<int[]> intervals = new ArrayList<>();
        for (int num : numbers) {
            intervals.add(new int[]{num - k, num + k});
        }
        showRanges(intervals);
        //按左端點升序排列區間
        intervals.sort(Comparator.comparingInt(a -> a[0]));
        showRanges(intervals);
        int maxBeauty = 0;
        int left = Integer.MIN_VALUE;   //紀錄滑動窗口內的左端點 
        int right = Integer.MIN_VALUE;  //紀錄滑動窗口內的右端點
        System.out.println("right = left = " + Integer.toString(Integer.MIN_VALUE));
        //遍歷所有區間
        for (int[] interval : intervals) {
            //更新窗口的右端點
            System.out.println("right=> " + Integer.toString(right));
            right = Math.min(right, interval[1]);
            dumpArray(interval);
            System.out.println("right=> " + Integer.toString(right));

            //如果當前區間的左端點比窗口的右端點大, 更新窗口
            if (interval[0] > right) {
                left = interval[0];
                right = interval[1];
                System.out.println("interval[0] > right=> " + Integer.toString(right) + " ,left=> " + Integer.toString(left));
            } else {
                left = Math.max(left, interval[0]);
                System.out.println("Math.max interval[0]=> " + interval[0] + ", left=> " + Integer.toString(left));
            }
            //計算目前的美麗值
            maxBeauty = Math.max(maxBeauty, right - left);
            System.out.print("Math.max(" + Integer.toString(maxBeauty));
            System.out.print(", (right):" + Integer.toString(right));
            System.out.print(" - (left):" + Integer.toString(left));
            System.out.print(")");
            System.out.println();
        }

        return maxBeauty;
    }
    
    public void showArray(int [] numbers, int k) {
        for(int i : numbers) {
            System.out.print(i + " ");
        }
        System.out.println(", k: " + Integer.toString(k));
    }
    
    public int maximumBeauty2(int[] numbers, int k) {
        showArray(numbers, k);
        Arrays.sort(numbers); 
        showArray(numbers, k);

        int maxBeauty = 0;
        int left = 0;
        for (int right = 0; right < numbers.length; right++) {
            
            while (numbers[right] - numbers[left] > 2 * k) {
                left++; 
            }
        
            maxBeauty = Math.max(maxBeauty, right - left + 1);
        }
        return maxBeauty;
    }



    public static void main(String[] args) {
        MaxBeautyArray solution = new MaxBeautyArray();
        int[] numbers = new int[]{1, 3, 5, 2};
        System.out.print("int[] number:");
        for(int n : numbers) { 
            System.out.print(Integer.toString(n) + " ");
        }
        int k = 2;
        System.out.println("k: " + Integer.toString(k));

        System.out.println("Beauty value: " + solution.maximumBeauty(numbers, k)); // Output: 4
    }
            /*
         *  
            int[] number:1 3 5 2 k: 2
            [-1,3][1,5][3,7][0,4]
            [-1,3][0,4][1,5][3,7]
            right = left = -2147483648
            right=> -2147483648
            [-1, 3]right=> -2147483648
            interval[0] > right=> 3 ,left=> -1
            Math.max(4, (right):3 - (left):-1)
            right=> 3
            [0, 4]right=> 3
            Math.max interval[0]=> 0, left=> 0
            Math.max(4, (right):3 - (left):0)
            right=> 3
            [1, 5]right=> 3
            Math.max interval[0]=> 1, left=> 1
            Math.max(4, (right):3 - (left):1)
            right=> 3
            [3, 7]right=> 3
            Math.max interval[0]=> 3, left=> 3
            Math.max(4, (right):3 - (left):3)
            Beauty value: 4
         */
}
