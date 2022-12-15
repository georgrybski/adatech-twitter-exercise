package br.com.ada.georg.twitter;

public class Comment {
    private String content;
    private Account author;
    private Tweet location;

    public Comment(String content, Account author, Tweet location) {
        this.content = content;
        this.author = author;
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public Account getAuthor() {
        return author;
    }

    public Tweet getLocation() {
        return location;
    }
}
