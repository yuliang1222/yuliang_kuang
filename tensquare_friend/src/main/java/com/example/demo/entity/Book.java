package com.example.demo.entity;

public class Book {
    private Integer bookid;

    private String titleParStatus;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getTitleParStatus() {
        return titleParStatus;
    }

    public void setTitleParStatus(String titleParStatus) {
        this.titleParStatus = titleParStatus == null ? null : titleParStatus.trim();
    }
}