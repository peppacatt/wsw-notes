package com.peppacatt.notes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[] arr = {1, 2, 4, 7, 8, 9};
        System.out.println(Arrays.binarySearch(arr, 7));
    }
}