package com.example.demo.servcice;


import com.example.demo.datasource.annotation.DataSource;
import com.example.demo.exceptions.GlobalException;
import com.example.demo.pojo.User;

import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.CodeMsg;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.demo.utils.CodeMsg.SERVER_ERROR;

/**
 * @author: HuYi.Zhang
 * @create: 2018-05-20 11:52
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @DataSource("slave1")
    @Transactional
    public  List<User> queryById(){
//        Object zt = requestJson.get("zt");
//        Object yjgdm = requestJson.get("yljgdm");
//        List department = JsonUtils.JsonArrayToString((JSONArray) yjgdm, String.class);
        List<String> myList = Arrays.asList("123","213");
        List<User> userById = this.userMapper.UserById(myList);
        return userById;
    }

    @DataSource("slave2")
    @Transactional
    public  List<User> queryById2(){
        List<String> myList = Arrays.asList("123","213");
        List<User> userById = this.userMapper.UserById(myList);
        return userById;
    }
    @DataSource("slave3")
    @Transactional
    public  List<User> queryById3(){
        List<String> myList = Arrays.asList("123","213");
        return this.userMapper.UserById(myList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertuser() {
       this.insertuser1();
    }

    public Result<Object> insertuser1()  {

        try {
//            User user1 = new User("12122212", "adfsa1");
            User user = new User("a3411", "", new Date());
            userMapper.insertuser(user);
            int i = 1 / 0;
            userMapper.insertuser(user);

//            queryAll();
            System.out.println(11);
        } catch (Exception e) {
            if (1 < 2) {
//                return Result.error(CodeMsg.TRANSACTION_ERROR);
                throw new GlobalException(SERVER_ERROR);
            }
        }


        return null;
    }

    public CodeMsg queryAll() {
        return CodeMsg.TRANSACTION_ERROR;
    }
}
