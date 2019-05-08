package com.vote.queen.king.ucstvoting.model;

public class Selection {

    String name, txt1, txt2;
    int img;


    public Selection() {
    }

    public Selection(String name, String txt1, String txt2, int img) {
        this.name = name;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.img = img;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxt1() {
        return this.txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt2() {
        return this.txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public int getImg() {
        return this.img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
