package com.example.validation.domains;

import com.example.validation.annotations.YearMonth;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.List;

public class User {

    private String name;

    @Max(value = 90, message = "90 이하여야합니다.")
    private Integer age;

    @Email(message = "이메일 형식이 맞지 않습니다. example@example.com")
    private String email;   //Bad Request

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @YearMonth
    @JsonProperty(value = "req_month")
    private String reqMonth;        //yyyyMM

    @Valid
    private List<Car> cars;

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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqMonth='" + reqMonth + '\'' +
                ", cars=" + cars +
                '}';
    }
}


