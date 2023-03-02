package org.example;

import com.sun.jdi.IntegerType;

import java.util.*;
import java.io.*;

import org.example.Container.Container;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) throws IOException {

        Container.init();
        new App().run();
        Container.close();
    }
}

