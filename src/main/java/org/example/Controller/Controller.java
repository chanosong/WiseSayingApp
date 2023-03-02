package org.example.Controller;

import org.example.Container.Container;
import org.example.Entity.Saying;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private List<Saying> sayingList;
    int cnt;
    String path = "src/main/java/org/example/Storage/data.txt";
    String jpath = "src/main/java/org/example/Storage/data.json";
    public Controller() {
        sayingList = new LinkedList<>();
        cnt = 0;
    }

    public void sync() throws IOException {
        File file = new File(path);

        if (file.exists()){
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                sayingList.add(new Saying(Integer.valueOf(data[0]), data[1], data[2]));
            }
            cnt = sayingList.size();
            reader.close();
        }
    }
    public void exit() throws IOException {
        File jfile = new File(path);
        JSONArray jarr = new JSONArray();

        PrintWriter writer = new PrintWriter(new FileWriter(jfile));
        for (Saying s : sayingList) {
            JSONObject obj = new JSONObject();
            obj.put("id", s.getId());
            obj.put("content", s.getContent());
            obj.put("author", s.getAuthor());

            jarr.add(obj);
        }
        writer.write(jarr.toJSONString());
        writer.close();
    }
    public void enroll() {
        System.out.print("명언 : ");
        String content = Container.getScanner().nextLine();
        System.out.print("작가 : ");
        String author = Container.getScanner().nextLine();

        sayingList.add(new Saying(++cnt, author, content));
        System.out.println(cnt + "번 명언이 등록되었습니다.");
    }

    public void show() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("----------------");

        for (int i = cnt-1; i >=0; i-- ) {
            if (sayingList.get(i).isExist()) {
                System.out.println(sayingList.get(i).toString());
            }
        }
    }

    public void build() throws IOException {
        File jfile = new File(jpath);
        JSONArray jarr = new JSONArray();

        PrintWriter writer = new PrintWriter(new FileWriter(jfile));
        for (Saying s : sayingList) {
            JSONObject obj = new JSONObject();
            obj.put("id", s.getId());
            obj.put("content", s.getContent());
            obj.put("author", s.getAuthor());

            jarr.add(obj);
        }
        writer.write(jarr.toJSONString());
        writer.close();

        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }
    public void drop(String cmd) {
        String[] deletion = cmd.split("=");
        int idx = Integer.valueOf(deletion[1]);

        if (sayingList.get(idx - 1).isExist()) {
            sayingList.get(idx - 1).delete();
            System.out.println(deletion[1] + "번 명언이 삭제되었습니다.");
        }
        else {
            System.out.println(deletion[1] + "번 명언은 존재하지 않습니다.");
        }
    }

    public void modify(String cmd) {
        String[] modi = cmd.split("=");
        int idx = Integer.valueOf(modi[1]);
        String newContent;
        String newAuthor;

        System.out.print("명언(기존) : ");
        System.out.println(sayingList.get(idx - 1).getContent());
        System.out.print("명언 : ");
        newContent = Container.getScanner().nextLine();

        System.out.print("작가(기존) : ");
        System.out.println(sayingList.get(idx - 1).getAuthor());
        System.out.print("작가 : ");
        newAuthor = Container.getScanner().nextLine();

        sayingList.get(idx - 1).modify(newAuthor, newContent);
    }
}
