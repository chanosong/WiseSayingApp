package org.example;

import com.sun.jdi.IntegerType;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        List<Saying> list = new LinkedList<>();
        int cnt = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) break;

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