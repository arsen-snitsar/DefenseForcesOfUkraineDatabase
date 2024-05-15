package org.example.finalprojectalpha.Data;

import java.util.ArrayList;

public class BinarySearch {

    public static int search(ArrayList<Comparable> arr, Comparable target) {
        int left = 0;
        int right = arr.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid).compareTo(target) == 0) {
                return mid;
            }
            if (arr.get(mid).compareTo(target) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
