package com.example.demo.mapper;


import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: HuYi.Zhang
 * @create: 2018-05-20 11:54
 **/
@Component
@Mapper
public interface UserMapper {

    public List<User> UserById(List id);

    public void insertuser(@Param("pojo") User pojo);

    List<String> selectby(@Param("pojo") String pojo, @Param("aaa") String aaa);
}
