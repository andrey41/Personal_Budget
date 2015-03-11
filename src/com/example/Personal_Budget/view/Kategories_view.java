package com.example.Personal_Budget.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import com.example.Personal_Budget.R;
import com.example.Personal_Budget.model.Category;
import com.example.Personal_Budget.model.Model;

import java.util.Calendar;

public class Kategories_view extends Activity{
	Model m;
	boolean delete=false;
	int i=0;
	static CharSequence type="";
	static int gradient;
	static int gradient_k;
	LayoutParams layout_params;
	LayoutParams space1_params;
	LayoutParams text1_params;
	LayoutParams space2_params;
	LayoutParams text2_params;
	LayoutParams space3_params;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kategories);
		
		m=new Model();
		
        Button add = (Button)findViewById(R.id.add);
        add.setBackgroundResource(gradient_k);
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!delete)
				{
					i++;
					
					m.getObj((String)type).addCategory("kategory"+ i);
					
					Category categ=m.getObj((String)type).getCategory(m.getObj((String)type).getCategoriesCount()-1);
					addKategory(categ.name,categ.summCategory(Calendar.getInstance().get(Calendar.MONTH)));
				}
				else
				{
					
					acceptDel((ScrollView)findViewById(R.id.scroll));
					m.getObj((String)type).delChoosenCategories();
					
				}
			}
		});
        
        Button del = (Button)findViewById(R.id.del);
        del.setBackgroundResource(gradient_k);
        del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!delete)
				{
					startDelMode();
				}
				else
				{
					cancelDel((ScrollView)findViewById(R.id.scroll));
					m.getObj((String)type).cancelDelCategories();
				}
			}
		});
        
        
        
        
	}
	public void categoriesFromModel()
	{
		Category cat;
        for (int i=0;i<m.getObj((String)type).getCategoriesCount();i++)
        {
        	cat=m.getObj((String)type).getCategory(i);
        	addKategory(cat.name, cat.summCategory(Calendar.getInstance().get(Calendar.MONTH)));
        }
	}
	public void addKategory(String n, final float mon)
	{
		ScrollView scrol=(ScrollView)findViewById(R.id.scroll);

		TextView name=new TextView(this);

		name.setText(n);
		name.setTextSize(30);
		name.setLayoutParams(text1_params);
		name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!delete)
				{
					perehodNaRecordsView((TextView)v);
	               
				}
				
			}
		});
		
	
		
		TextView money=new TextView(this);

		money.setText(" "+mon+" руб.");
		money.setTextSize(30);
		money.setLayoutParams(text2_params);
		
		LinearLayout newLinear=new LinearLayout(this);
		newLinear.setOrientation(LinearLayout.HORIZONTAL);
		newLinear.setLayoutParams(layout_params);
		newLinear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (delete)
				{
					LinearLayout l=((LinearLayout)v);
					chooseCategoryView(l);
					m.getObj((String)type).chooseCategoryForDel(((LinearLayout)((ScrollView)findViewById(R.id.scroll)).getChildAt(0)).indexOfChild(l));	
				}
				
			}
		});
		
		Space space1=new Space(this);
		Space space2=new Space(this);
		Space space3=new Space(this);
		
		space1.setLayoutParams(space1_params);
		space2.setLayoutParams(space2_params);
		space3.setLayoutParams(space3_params);
		
		newLinear.addView(space1);	
		newLinear.addView(name);
		newLinear.addView(space2);	
		newLinear.addView(money);
		newLinear.addView(space3);	
	
		
		((LinearLayout)scrol.getChildAt(0)).addView(newLinear);
		
		
	}
	public void chooseCategoryView(LinearLayout l)
	{
		float alfa=l.getAlpha();
		if (alfa==1.0)
		{
			l.setAlpha(0.5f);
		}
		else
		{
			l.setAlpha(1.0f);
		}
	}
	public void delKategory(View v)
	{
		ScrollView scrol=(ScrollView)findViewById(R.id.scroll);
		((LinearLayout)scrol.getChildAt(0)).removeView(v);
	}
	public static void setType(CharSequence t)
	{
		type=t;
	}
	public static void setBackgrounds(int g,int gk)
	{
		gradient=g;
		gradient_k=gk;
	}
	public void acceptDel(ScrollView scrol)
	{
		delete=false;
		((Button)findViewById(R.id.add)).setText(" + ");
		((Button)findViewById(R.id.del)).setText(" - ");
		LinearLayout layout=((LinearLayout)scrol.getChildAt(0));
		LinearLayout l;
		int size=layout.getChildCount();
		for (int i=0,j=0;i<size;i++)
		{
			l=(LinearLayout)(layout.getChildAt(j));
			if (l.getAlpha()==0.5)
				layout.removeViewAt(j);
			else
				j++;
		}
	}
	public void perehodNaRecordsView(TextView v)
	{
		Records_view.setName(v.getText());
		if (type=="расходы")
		{
			Records_view.gradient_r=R.drawable.gradient_rk;
			
		}
		if(type=="доходы")
		{
			Records_view.gradient_r=R.drawable.gradient_dk;
		}
		Records_view.type=(String)type;
		Intent SecAct = new Intent(this, Records_view.class);
        startActivity(SecAct);
	}
	public void cancelDel(ScrollView scrol)
	{
		LinearLayout layout=((LinearLayout)scrol.getChildAt(0));
		LinearLayout l;
		for (int i=0;i<layout.getChildCount();i++)
		{
			l=(LinearLayout)(layout.getChildAt(i));
			if (l.getAlpha()==0.5)
				l.setAlpha(1.0f);
		}
		
		delete=false;
		((Button)findViewById(R.id.del)).setText(" - ");
		((Button)findViewById(R.id.add)).setText(" + ");
	}
	public void startDelMode()
	{
		delete=true;
		((Button)findViewById(R.id.del)).setText(" X ");
		((Button)findViewById(R.id.add)).setText(" V ");
	}
	
	public void initialization()
	{
		ScrollView scrol=(ScrollView)findViewById(R.id.scroll);
		LinearLayout layout=(LinearLayout)((LinearLayout)scrol.getChildAt(0)).getChildAt(0);
		layout_params=layout.getLayoutParams();
		space1_params=layout.getChildAt(0).getLayoutParams();
		text1_params=layout.getChildAt(1).getLayoutParams();
		space2_params=layout.getChildAt(2).getLayoutParams();
		text2_params=layout.getChildAt(3).getLayoutParams();
		space3_params=layout.getChildAt(4).getLayoutParams();
		((LinearLayout)scrol.getChildAt(0)).removeAllViews();
		
		TextView tv=(TextView)findViewById(R.id.name);
		tv.setText(type);
		tv.setBackgroundResource(gradient);
		
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		m.readFromFile(this);
		initialization();
		TextView summ = (TextView)findViewById(R.id.summ);
		summ.setText(""+m.getObj((String)type).summCategories(Calendar.getInstance().get(Calendar.MONTH))+" руб.");
		
		categoriesFromModel();//������ �����
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		m.writeToFile(this);
	}
		

}
