package org.example;

import com.sun.jdi.IntegerType;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) throws IOException {
        new App().run();
    }
}

class Saying {
    private int id;
    private String author;
    private String content;

    Saying(int id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent(){
        return content;
    }

    public void delete() {
        author = null;
        content = null;
    }

    public boolean isExist() {
        return !(author == null && content == null);
    }

    public void modify(String author, String content) {
        this.author = author;
        this.content = content;
    }
}