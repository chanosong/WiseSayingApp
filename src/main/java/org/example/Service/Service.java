package org.example.Service;

import org.example.Entity.Path;
import org.example.Entity.Saying;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Service {
    private List<Saying> sayingList;
    private int cnt;

    // Service 생성자
    public Service() {
        sayingList = new LinkedList<>();
        cnt = 0;
    }

    // sayingList 반환
    public List<Saying> listUp() {
        return sayingList;
    }

    // author, content로 saying 등록
    public int enroll(String author, String content) {
        sayingList.add(new Saying(++cnt, author, content));
        return cnt;
    }

    // Id로 Saying 반환
    public Saying findById(long id) {
        return sayingList.get((int) (id -1));
    }

    // id, author, content로 saying 업데이트
    public void updateSaying(long id, String author, String content) {
        sayingList.get((int) (id - 1)).modify(author, content);
    }

    // id로 saying 삭제, 성공 시 true 실패 시 false
    public boolean dropById(long id) {
        if (sayingList.get((int) (id - 1)).isExist()) {
            sayingList.get((int) (id - 1)).delete();
            return true;
        }
        return false;
    }

    // txt 파일 로드, 성공 시 true 실패 시 false
    public boolean load() throws IOException, ParseException {
        File file = new File(Path.textPath);

        if (file.exists()) {

            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(Path.textPath);

            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                long id = (long) jsonObject.get("id");
                String author = (String) jsonObject.get("author");
                String content = (String) jsonObject.get("content");
                sayingList.add(new Saying(id,author,content));
            }

            cnt = sayingList.size();

            return true;
        }
        return false;
    }

    // txt 파일로 상태 저장
    public boolean save() throws IOException {
        JSONArray jsonArray = new JSONArray();

        PrintWriter writer = new PrintWriter(new FileWriter(Path.textPath));

        for (Saying s : sayingList){
            JSONObject obj = new JSONObject();
            obj.put("id", s.getId());
            obj.put("content", s.getContent());
            obj.put("author", s.getAuthor());

            jsonArray.add(obj);
        }

        writer.write(jsonArray.toJSONString());
        writer.close();

        return true;
    }

    // json 파일로 결과 저장
    public boolean saveJson() throws IOException {
        File jfile = new File(Path.jsonPath);
        JSONArray jarr = new JSONArray();

        try {
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

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
