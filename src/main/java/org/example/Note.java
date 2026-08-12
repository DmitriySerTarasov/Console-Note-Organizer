package org.example;

public class Note {

    private String content;
    private String date;

    public Note(String content, String date) {
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString() {
        return date + " | " + content;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
