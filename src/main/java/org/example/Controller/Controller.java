package org.example.Controller;

import org.example.Container.Container;
import org.example.Entity.Saying;
import org.example.Service.Service;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.List;

public class Controller {

    Service service;

    // Controller 생성자
    public Controller() {
        service = new Service();
    }

    // txt 파일 기반 데이터 로드
    public void sync() throws IOException, ParseException {
        service.load();
    }

    // txt 파일 저장 후 종료
    public void exit() throws IOException {
        service.save();
    }

    // 새로운 Saying 등록
    public void enroll() {
        System.out.print("명언 : ");
        String content = Container.getScanner().nextLine();
        System.out.print("작가 : ");
        String author = Container.getScanner().nextLine();

        int idx = service.enroll(author, content);
        System.out.println(idx + "번 명언이 등록되었습니다.");
    }

    // 전체 sayingList 출력
    public void show() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("----------------");

        List<Saying> sayingList = service.listUp();

        for (int i = sayingList.size() - 1; i >=0; i-- ) {
            if (sayingList.get(i).isExist()) {
                System.out.println(sayingList.get(i).toString());
            }
        }
    }

    // 현재 sayingList를 json으로 저장
    public void build() throws IOException {
        if (service.saveJson()) {
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        }
        else {
            System.out.println("data.json 갱신에 실패하였습니다.");
        }
    }

    // id 기반 saying 삭제
    public void drop(String cmd) {
        String[] deletion = cmd.split("=");
        long idx = Long.valueOf(deletion[1]);

        if (service.dropById(idx)) {
            System.out.println(idx + "번 명언이 삭제되었습니다.");
        }
        else {
            System.out.println(idx + "번 명언은 존재하지 않습니다.");
        }
    }

    // id 기반 saying 수정
    public void modify(String cmd) {
        String[] modi = cmd.split("=");
        long idx = Long.valueOf(modi[1]);
        String newContent;
        String newAuthor;

        Saying saying = service.findById(idx);

        System.out.print("명언(기존) : ");
        System.out.println(saying.getContent());
        System.out.print("명언 : ");
        newContent = Container.getScanner().nextLine();

        System.out.print("작가(기존) : ");
        System.out.println(saying.getAuthor());
        System.out.print("작가 : ");
        newAuthor = Container.getScanner().nextLine();

        service.updateSaying(idx, newAuthor, newContent);
    }
}
