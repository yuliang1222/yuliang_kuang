package com.example.demo.entity;

import com.example.demo.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.constraints.NegativeOrZero;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 9111357402963030257L;
    private String id;
    private String name;
    private User user;
    private String messageId;

//    public void setMessageId(String messageId) {
//        this.messageId = messageId == null ? null : messageId.trim();
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class table {
      private String name;

    }

}