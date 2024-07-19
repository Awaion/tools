package com.awaion.demo009.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Employee extends BaseDomain {
    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin;
    private Department dept;
    private List<Role> roles = new ArrayList<Role>();

    @Override
    public String toString() {
        return "Employee [id=" + super.getId() + ", name=" + name + ", password=" + password + ", email=" + email + ", age=" + age
                + ", admin=" + admin + "]";
    }
}
