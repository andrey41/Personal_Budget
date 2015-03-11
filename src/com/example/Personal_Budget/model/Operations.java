/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Personal_Budget.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author VLad
 */
public class Operations implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5445985796213019952L;
	ArrayList<Category> categories;
    String name;
    int day,month,year;
    Operations(String n)
    {
        name=n;
        categories=new ArrayList<Category>();
        
    }
    public void setData(int day,int month,int year)
    {
        this.day=day;
        this.month=month;
        this.year=year;
    }
    public void addCategory(String cat)
    {
       Category c=new Category(cat);
       categories.add(c);
    }
    public void removeCategory (int i)
    {
       categories.remove(i); 
    }
    public float summCategories(int mes)
    {
        float summ=0;
        int m=categories.size();
        for(int k=0;k<m;k++)
        {
            summ=summ+categories.get(k).summCategory(mes);
        }
        return summ;
    }
    public void changeCategory(String n)
    {
        name=n;
    }
    public Category getCategory(int i)
    {
    	return categories.get(i);
    }
    public int getCategoriesCount()
    {
    	return categories.size();
    }
    public void chooseCategoryForDel(int i)
    {
    	categories.get(i).setDel(true);
    }
    public void cancelDelCategories()
    {
    	for(int i=0;i<categories.size();i++)
    	{
    		categories.get(i).setDel(false);
    	}
    }
    public void delChoosenCategories()
    {
    	Category c=new Category("");
    	int size=categories.size();
		for (int i=0,j=0;i<size;i++)
		{
			c=categories.get(j);
			if (c.getDel())
				categories.remove(j);
			else
				j++;
		}
    }
    public Category getCategory(String s)
    {
    	int i=0;
    	int size=categories.size();
    	Category c=new Category("");
    	while(i<size)
    	{
    		c=categories.get(i);
    		if(c.getName().equals(s))
    		{
    			i=size;
    		}
    		i++;
    	}
        return c;
    }
    public void clearAllDel()
    {
    	cancelDelCategories();
    	for (int i=0;i<categories.size();i++)
    		categories.get(i).cancelDelRecords();
    }


    



    
}
