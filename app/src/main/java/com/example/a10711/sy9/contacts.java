package com.example.a10711.sy9;

/**
 * Created by 10711 on 2018/6/9.
 */

public class contacts {
    public String name;

    public String phone;

    public String sex;

    public contacts(String name, int price, String category){
        this.name=name;
        this.phone=phone;
        this.sex=sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
