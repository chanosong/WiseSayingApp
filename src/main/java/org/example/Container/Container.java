package org.example.Container;

import java.util.*;

public class Container {
    private static Scanner sc;

    public static void init() {
        sc = new Scanner(System.in);
    }

    public static void close() {
        sc.close();
    }

    public static Scanner getScanner() {
        return sc;
    }
}
