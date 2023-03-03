package org.example.Entity;

public class Saying {
    private long id;
    private String author;
    private String content;

    public Saying(long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
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
