package com.example.demo.mapper;

import com.example.demo.entity.book;

public interface bookMapper {
    int deleteByPrimaryKey(Integer bookid);

    int insert(book record);

    int insertSelective(book record);

    book selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(book record);

    int updateByPrimaryKey(book record);
}