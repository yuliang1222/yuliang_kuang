package com.example.demo.mapper;

import com.example.demo.entity.article;

public interface articleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(article record);

    int insertSelective(article record);

    article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(article record);

    int updateByPrimaryKeyWithBLOBs(article record);

    int updateByPrimaryKey(article record);
}