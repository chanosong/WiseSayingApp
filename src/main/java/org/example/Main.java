package org.example;

import java.io.*;

import org.example.Container.Container;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        Container.init();
        new App().run();
        Container.close();
    }
}

