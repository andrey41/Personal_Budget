/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Personal_Budget.model;

import java.io.Serializable;

/**
 *
 * @author VLad
 */
public class Record implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2399112086386327830L;
	int day;
    int month;
    int year;
    float money;
    boolean del;
    String description;
    public Record(int d,int mes,int y,float m,String de)
    {
        day=d;
        month=mes;
        year=y;
        money=m;
        description=de;
    }
    public float getMoney()
    {
        return money;
    }
    void changeMoney(float m)
    {
        money=m;
    }
    void showMoney()
    {
        System.out.println("Сумма "+money);
    }
    void changeDay(int d)
    {
        day=d;
    }
    ///////
    public int getDay()
    {
        return day;
    }
    void showDay()
    {
        System.out.println("День "+day);
    }
    ///////
    void changeMonth(int mes)
    {
        month=mes;
    }
    public int getMonth()
    {
        return month;
    }
    void showMonth()
    {
        System.out.println("Месяц "+month);
    }
    ///////
    void changeYear(int y)
    {
        year=y;
    }
    public int getYear()
    {
        return year;
    }
    void showYear()
    {
        System.out.println("Год "+year);
    }
    ///////
    void changeDescription(String de)
    {
        description=de;
    }
    String getDescription()
    {
        return description;
    }
    void showDescription()
    {
        System.out.println("Год "+description);
    }
    void setDel(boolean f)
    {
    	del=f;
    }
    boolean getDel()
    {
    	return del;
    }
}

