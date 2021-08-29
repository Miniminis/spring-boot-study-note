package com.example.validation.domains;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {

    private String name;

    @Max(value = 90, message = "90 이하여야합니다.")
    private Integer age;

    @Email(message = "이메일 형식이 맞지 않습니다. example@example.com")
    private String email;   //Bad Request

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;

    @Size(min = 6, max = 6)
    private String reqMonth;        //yyyyMM

    @AssertTrue(message = "yyyyMM 형식에 맞지 않습니다.")
    public boolean isReqMonthValid() {
        try {
            LocalDate localDate = LocalDate.parse(getReqMonth()+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return false;
        }
        return true;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqMonth() {
        return reqMonth;
    }

    public void setReqMonth(String reqMonth) {
        this.reqMonth = reqMonth;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqMonth='" + reqMonth + '\'' +
                '}';
    }
}


