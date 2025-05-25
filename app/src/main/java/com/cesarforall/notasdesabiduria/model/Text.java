package com.cesarforall.notasdesabiduria.model;

public class Text {
    private int id;
    private String text;
    private String author;
    private String source;
    private String sourceType; // "book" o "movie"
    private Integer pageNumber; // Solo para libros
    private Integer minute; // Solo para películas

    public Text(String text, String author, String source, String sourceType, Integer pageNumber, Integer minute) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.source = source;
        this.sourceType = sourceType;
        this.pageNumber = pageNumber;
        this.minute = minute;
    }
    public Text(int id, String text, String author, String source, String sourceType, Integer pageNumber, Integer minute) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.source = source;
        this.sourceType = sourceType;
        this.pageNumber = pageNumber;
        this.minute = minute;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public String getSource() {
        return source;
    }

    public String getSourceType() {
        return sourceType;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getMinute() {
        return minute;
    }
}
