package domain;

import validator.Validate;

public class Employee {
    private int id;
    @Validate(rules = "unique:employees,email")
    private String email;
    @Validate(rules = "min:3| max: 10")
    private String nickname;
    @Validate(rules = "exists:departments,name")
    private String department;

    public Employee() {
    }

    public Employee(String email, String nickname, String department) {
        this.email = email;
        this.nickname = nickname;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}