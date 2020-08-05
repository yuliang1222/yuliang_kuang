package com.example.demo.mapper;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Integer bookid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}