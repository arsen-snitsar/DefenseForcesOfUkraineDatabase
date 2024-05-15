package org.example.finalprojectalpha.Data;

import java.util.ArrayList;

public class Quicksort {

    private static int partition(ArrayList<Comparable> a, int lo, int hi, String sortOrder) {
        int i = lo, j = hi + 1;
        if (sortOrder.equals("asc")) {
            while (true) {
                while (less(a.get(++i), a.get(lo)))
                    if (i == hi) break;
                while (less(a.get(lo), a.get(--j)))
                    if (j == lo) break;

                if (i >= j) break;
                swap(a, i, j);
            }
        } else {
            while (true) {
                while (more(a.get(++i), a.get(lo)))
                    if (i == hi) break;
                while (more(a.get(lo), a.get(--j)))
                    if (j == lo) break;

                if (i >= j) break;
                swap(a, i, j);
            }
        }

        swap(a, lo, j);
        return j;
    }

    private static void swap(ArrayList<Comparable> a, int i, int j) {
        Comparable temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    public static void sort(ArrayList<Comparable> a, String sortOrder) {
        sort(a, 0, a.size() - 1, sortOrder);
    }

    private static void sort(ArrayList<Comparable> a, int lo, int hi, String sortOrder) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi, sortOrder);
        sort(a, lo, j - 1, sortOrder);
        sort(a, j + 1, hi, sortOrder);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean more(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }
}