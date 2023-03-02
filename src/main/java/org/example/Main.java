package org.example;

import com.sun.jdi.IntegerType;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/main/java/org/example/Storage/data.txt";
        String jpath = "src/main/java/org/example/Storage/data.json";
        File file = new File(path);

        List<Saying> list = new LinkedList<>();
        int cnt = 0;

        if (file.exists()) {

            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                list.add(new Saying(Integer.valueOf(data[0]), data[1], data[2]));
            }

            reader.close();
            cnt = list.size();
        }


        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                File jfile = new File(jpath);
                JSONArray jarr = new JSONArray();

                PrintWriter writer = new PrintWriter(new FileWriter(jfile));
                for (Saying s : list) {
                    JSONObject obj = new JSONObject();
                    obj.put("id", s.getId());
                    obj.put("content", s.getContent());
                    obj.put("author", s.getAuthor());

                    jarr.add(obj);
                }
                writer.write(jarr.toJSONString());
                writer.close();
                break;
            }

            else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();

                list.add(new Saying(++cnt, author, content));
                System.out.println(cnt + "번 명언이 등록되었습니다.");
            }

            else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언 ");
                System.out.println("----------------");

                for (int i = cnt-1; i >=0; i-- ) {
                    if (list.get(i).isExist()) {
                        System.out.println(list.get(i).toString());
                    }
                }
            }
            else if (cmd.equals("빌드")) {
                File jfile = new File(jpath);
                JSONArray jarr = new JSONArray();

                PrintWriter writer = new PrintWriter(new FileWriter(jfile));
                for (Saying s : list) {
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

            else if (cmd.contains("삭제")) {
                String[] deletion = cmd.split("=");
                int idx = Integer.valueOf(deletion[1]);

                if (list.get(idx - 1).isExist()) {
                    list.get(idx - 1).delete();
                    System.out.println(deletion[1] + "번 명언이 삭제되었습니다.");
                }
                else {
                    System.out.println(deletion[1] + "번 명언은 존재하지 않습니다.");
                }
            }

            else if (cmd.contains("수정")) {
                String[] modi = cmd.split("=");
                int idx = Integer.valueOf(modi[1]);
                String newContent;
                String newAuthor;

                System.out.print("명언(기존) : ");
                System.out.println(list.get(idx - 1).getContent());
                System.out.print("명언 : ");
                newContent = sc.nextLine();

                System.out.print("작가(기존) : ");
                System.out.println(list.get(idx - 1).getAuthor());
                System.out.print("작가 : ");
                newAuthor = sc.nextLine();

                list.get(idx - 1).modify(newAuthor, newContent);
            }
        }
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