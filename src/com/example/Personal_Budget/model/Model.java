/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Personal_Budget.model;

import android.app.Activity;

import java.io.*;

/**
 *
 * @author VLad
 */
public class Model implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6537245340817619248L;
	Operations dohod;
    Operations rashod;
    public String str;
    public Model()
    {
        dohod=new Operations("доходы");
        rashod=new Operations("расходы");
    }
    public Operations getObj(String name)
    {
        if (name=="расходы") return rashod;
        else return dohod;
        
    }
    public float getBalance(int mes)
    {
        float bal=0f;
        bal=dohod.summCategories(mes)-rashod.summCategories(mes);
        return bal;
    }
	public void writeToFile(Activity act) 
	{
		Model m=new Model();
		m.dohod=dohod;
		m.rashod=rashod;
		m.dohod.clearAllDel();
		m.rashod.clearAllDel();
		try {
			FileOutputStream fos = act.openFileOutput("file", act.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(m);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void readFromFile(Activity act) 
	{
		Model m;
		try {
			FileInputStream fis = act.openFileInput("file");
			ObjectInputStream is = new ObjectInputStream(fis);
			m = (Model) is.readObject();
			dohod=m.dohod;
			rashod=m.rashod;
			is.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
}
