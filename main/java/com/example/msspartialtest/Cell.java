package com.example.msspartialtest;

public class Cell {
    public int x, y, value;
    private String text;
    public boolean isOpen = false;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        double temp = Math.random()*11;
        if(temp > 9){
            temp=9;
        }
        value = (int) temp;
    }
    public String getText(){
        if (value == 9) {
            return "X"; // Bomb
        } else if(value == 0){
            return "";
        } else {
            return String.valueOf(value);
        }
    }
}
