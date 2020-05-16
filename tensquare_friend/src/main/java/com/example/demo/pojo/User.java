package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

//@Table(name = "tb_user")
@Data
public class User   {



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @KeySql(useGeneratedKeys = true)
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 性别，1男性，2女性
    private Integer sex;

    // 出生日期
    private Date birthday;

    // 创建时间
    private Date created;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password,Date creattime) {
        this.username = username;
        this.password = password;
        this.created = creattime;
    }
    // 更新时间
    @JsonFormat
    private Date update;

    // 备注
    @Transient//不需要持久化到数据库
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getupdate() {
        return update;
    }

    public void setupdate(Date update) {
        this.update = update;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", name=" + name + ", age=" + age + ", sex=" + sex
                + ", birthday=" + birthday + ", created=" + created
                + ", update=" + update + ", note=" + note + "]";
    }
}
