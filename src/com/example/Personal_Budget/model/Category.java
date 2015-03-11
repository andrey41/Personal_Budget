/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Personal_Budget.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 449549456091009537L;
	ArrayList<Record> records;
    public String name;
    boolean del;
    
    Category(String n)
    {
        name=n;
        records=new ArrayList<Record>();
        del=false;
    }
    
    public void addRecord(int d,int mes,int y,float m,String de)
    {
        Record r=new Record(d, mes, y, m, de);
        records.add(r);
    }
    public void changeData(int i, int d,int mes,int y)
    {
        records.get(i).changeDay(d);
        records.get(i).changeMonth(mes);
        records.get(i).changeYear(y);
    }
    public void changeDescrip(int i, String de)
    {
        records.get(i).changeDescription(de);
    }
    public void changeMoney(int i, float m)
    {
        records.get(i).changeMoney(m);
    }
    
    public Record getCategoryDay(int d, int mes)
    {
        int t=records.size();
        int da;
        int me;
        for (int k=0;k<t;k++)
        {
            da=records.get(k).getDay();
            me=records.get(k).getMonth();
            if ((da==d)&&(me==mes))
                return records.get(k);
        }
        return null;
    }
    public Record getCategoryWeek(int d, int mes)
    {
        
        return null;
    }
    public Record getCategoryMonth(int mes)
    {
        int t=records.size();
        for (int k=0;k<t;k++)
        {
            int me=records.get(k).getMonth();
            if (me==mes)
                return records.get(k);
        }
        return null;
    } 
    public float summCategory(int mes)
    {
        int me;
        float summ=0f;
        int m=records.size();
        for(int k=0;k<m;k++)
        {
            me=records.get(k).getMonth();
            if (me==mes)
            summ=summ+records.get(k).getMoney();
        }
        return summ;
    }
    public Record getRecord(int i)
    {
        return records.get(i);
    }

    public void changeRecord(int i, int r, int d,int mes,int y,float m,String de)
    {
       changeData( i, d, mes, y);
       changeDescrip(i,de);
       changeMoney(i,m);
    }
    public void removeRecord (int i)
    {
        Record r=records.remove(i); 
    }
    public void setDel(boolean t)
    {
    	del=t;
    }
    public boolean getDel()
    {
    	return del;
    }
    public void chooseRecordForDel(int i)
    {
    	records.get(i).setDel(true);
    }
    public void cancelDelRecords()
    {
    	for(int i=0;i<records.size();i++)
    	{
    		records.get(i).setDel(false);
    	}
    }
    public void delChoosenCategories()
    {
    	Record c=new Record(0, 0, 0, 0, "");
    	int size=records.size();
		for (int i=0,j=0;i<size;i++)
		{
			c=records.get(j);
			if (c.getDel())
				records.remove(j);
			else
				j++;
		}
    }
    public String getName()
    {
    	return name;
    }
    public int getRecordsCount()
    {
    	return records.size();
    }

}

