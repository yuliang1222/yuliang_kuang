package com.pazl.authorization.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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