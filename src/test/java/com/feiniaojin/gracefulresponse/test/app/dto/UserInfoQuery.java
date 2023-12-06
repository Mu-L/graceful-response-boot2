package com.feiniaojin.gracefulresponse.test.app.dto;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 请求的DTO.
 *
 * @author qinyujie
 */
public class UserInfoQuery {

    @NotNull(message = "userId is null !")
    private Long userId;

    @NotNull(message = "userName is null !")
    @Length(min = 6, max = 12)
    @ValidationStatusCode(code = "520")
    private String userName;

    @NotNull(message = "age is null !")
    @Range(min = 18, max = 50)
    private Integer age;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
