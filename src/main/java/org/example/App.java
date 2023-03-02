package org.example;

import org.example.Container.Container;
import org.example.Controller.Controller;
import org.example.Entity.Saying;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {

    void run() throws IOException {

        Controller controller = new Controller();

        controller.sync();

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = Container.getScanner().nextLine();

            if (cmd.equals("종료")) {
                controller.exit();
                break;
            }
            else if (cmd.equals("등록")) {
                controller.enroll();
            }
            else if (cmd.equals("목록")) {
                controller.show();
            }
            else if (cmd.equals("빌드")) {
                controller.build();
            }
            else if (cmd.contains("삭제")) {
                controller.drop(cmd);
            }
            else if (cmd.contains("수정")) {
                controller.modify(cmd);
            }
        }
    }
}
