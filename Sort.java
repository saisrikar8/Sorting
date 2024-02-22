package org.example.chart;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args){
        int[] array = new int[Math.abs(getRandom(10, 100000))];
        for (int i = 0; i < array.length; i++){
            array[i] = getRandom(5, 1000);
        }
        long start_time = System.currentTimeMillis();
        int[] quick_sorted = quick_sort(array);
        long end_time = System.currentTimeMillis();
        long elapsed_time_quick_sort = end_time - start_time;
        start_time = System.currentTimeMillis();
        int[] merge_sorted = merge_sort(array);
        end_time = System.currentTimeMillis();
        long elapsed_time_merge_sort = end_time - start_time;
        System.out.println("Array length: " + array.length + " Merge Sort Time: " + elapsed_time_merge_sort + " ms, Quick Sort Time: " + elapsed_time_quick_sort + " ms");

    }
    public static int getRandom(int min, int max){
        return (int)Math.round((Math.random()*max)+min);
    }
    public static int[] merge_sort(int[] nums){
        if (nums.length == 1){
            return nums;
        }
        int[] left = new int[nums.length/2];
        int[] right = new int[nums.length - left.length];
        for (int i = 0; i < nums.length; i++){
            if (i < left.length){
                left[i] = nums[i];
            }
            else{
                right[i - left.length] = nums[i];
            }
        }
        return merge(merge_sort(left), merge_sort(right));
    }
    private static int[] merge(int[] left, int[] right){
        int p1 = 0;
        int p2 = 0;
        int sortedArrayIndex = 0;
        int[] sortedArray = new int[left.length + right.length];
        while (p1 < left.length && p2 < right.length){
            if (left[p1] < right[p2]){
                sortedArray[sortedArrayIndex] = left[p1];
                p1++;
            }
            else{
                sortedArray[sortedArrayIndex] = right[p2];
                p2++;
            }
            sortedArrayIndex++;
        }
        for (int i = p1; i < left.length; i++){
            sortedArray[sortedArrayIndex] = left[i];
            sortedArrayIndex++;
        }
        for (int x = p2; x < right.length; x++){
            sortedArray[sortedArrayIndex] = right[x];
            sortedArrayIndex++;
        }
        return sortedArray;
    }
    public static int[] quick_sort(int[] nums){
        if (nums.length <= 1){
            return nums;
        }
        int pivot = nums[nums.length - 1];
        int i = -1;
        int j = 0;
        int[] newArray = nums.clone();
        while (j < nums.length){
            if (newArray[j] < pivot){
                i++;
                int temp = newArray[i];
                newArray[i] = newArray[j];
                newArray[j] = temp;
            }
            j++;
        }
        int temp = newArray[i + 1];
        newArray[i + 1] = pivot;
        newArray[newArray.length - 1] = temp;
        if (nums.length == 2){
            return newArray;
        }
        int[] small = new int[i + 1];
        int[] large = new int[nums.length - small.length - 1];
        for (int x = 0; x < newArray.length; x++){
            if (x < small.length){
                small[x] = newArray[x];
            }
            else if (x > small.length){
                large[x - (i + 2)] = newArray[x];
            }
        }
        int[] sortedSmall = quick_sort(small);
        int[] sortedLarge = quick_sort(large);
        int[] sorted = new int[nums.length];
        for (i = 0; i < sorted.length; i++){
            if (i < sortedSmall.length){
                sorted[i] = sortedSmall[i];
            }
            else if (i > sortedSmall.length){
                sorted[i] = sortedLarge[i - sortedSmall.length - 1];
            }
            else{
                sorted[i] = pivot;
            }
        }
        return sorted;
    }
}
