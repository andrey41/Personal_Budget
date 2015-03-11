package com.example.Personal_Budget.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import com.example.Personal_Budget.R;
import com.example.Personal_Budget.model.Model;
import com.example.Personal_Budget.model.Record;


import java.util.Calendar;

public class Records_view extends Activity{
	Model m;
	static CharSequence name="";
	boolean delete=false;
	int i=0;
	static int gradient_r;
	static String type;
	LayoutParams layout_params;
	LayoutParams layout1_params;
	LayoutParams layout2_params;
	LayoutParams text1_params;
	LayoutParams space1_params;
	LayoutParams text2_params;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.records);
		
		m=new Model();
		
		TextView title=((TextView)findViewById(R.id.name));
		title.setText(name);
		title.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addAlertDialog();
				
			}
		});
		
		Button add = (Button)findViewById(R.id.add);
        add.setBackgroundResource(gradient_r);
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!delete)
				{
					i++;
					
					addRecord("record"+ i,i);
					m.getObj(type).getCategory((String)name).addRecord(i, i, i, i, "");
					
				}
				else
				{
					acceptDel((ScrollView)findViewById(R.id.scroll));
					//m.getObj(type).getCategory((String)name).delChoosenCategories();
					
				}
			}
		});
        
        Button del = (Button)findViewById(R.id.del);
        del.setBackgroundResource(gradient_r);
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
					//m.getObj(type).getCategory((String)name).cancelDelRecords();
				}
			}
		});
        
        Button day=(Button)findViewById(R.id.day3);
        day.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!delete)
				{
					//showRecords((ScrollView)findViewById(R.id.scroll), 1);
				}
				
			}
		});
        Button week=(Button)findViewById(R.id.week3);
        week.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!delete)
				{
					//showRecords((ScrollView)findViewById(R.id.scroll), 7);
				}
				
			}
		});
        Button month=(Button)findViewById(R.id.month3);
        month.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!delete)
				{
					//showRecords((ScrollView)findViewById(R.id.scroll), 30);
				}
				
			}
		});
        
	}
	
	public void addRecord(String n, float mon)
	{
		ScrollView scrol=(ScrollView)findViewById(R.id.scroll);

		TextView date=new TextView(this);

		date.setText(n);
		date.setTextSize(30);
		date.setLayoutParams(text1_params);
		
		
	
		
		TextView money=new TextView(this);

		money.setText(" "+(int)mon+" руб.");
		money.setTextSize(30);
		money.setLayoutParams(text2_params);
		
		LinearLayout linear=new LinearLayout(this);
		linear.setOrientation(LinearLayout.VERTICAL);
		linear.setLayoutParams(layout_params);
		
		LinearLayout linear1=new LinearLayout(this);
		linear1.setOrientation(LinearLayout.HORIZONTAL);
		linear1.setLayoutParams(layout1_params);
		
		LinearLayout linear2=new LinearLayout(this);
		linear2.setOrientation(LinearLayout.HORIZONTAL);
		linear2.setLayoutParams(layout2_params);
		linear2.setBackgroundResource(R.drawable.granica);
		linear2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (delete)
				{
					LinearLayout l=(LinearLayout)((LinearLayout)v).getParent();
					chooseRecord(l);
					
					int index=((LinearLayout)((ScrollView)findViewById(R.id.scroll)).getChildAt(0)).indexOfChild(l);
					//m.getObj(type).getCategory((String)name).chooseRecordForDel(0);
					
				}
				
			}
		});
		
		Space space1=new Space(this);
		
		space1.setLayoutParams(space1_params);
		
		linear2.addView(date);
		linear2.addView(space1);	
		linear2.addView(money);
	
		linear.addView(linear1);
		linear.addView(linear2);
		((LinearLayout)scrol.getChildAt(0)).addView(linear);
		
		
	}
	public void chooseRecord(LinearLayout l)
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
	public void acceptDel(ScrollView scrol)
	{
		delete=false;
		((Button)findViewById(R.id.add)).setText("  +  ");
		((Button)findViewById(R.id.del)).setText("  -  ");
		LinearLayout layout=((LinearLayout)scrol.getChildAt(0));
		LinearLayout l;
		int size=layout.getChildCount();
		for (int i=0,j=0;i<size;i++)
		{
			l=(LinearLayout)((LinearLayout)(layout.getChildAt(j))).getChildAt(1);
			if (l.getAlpha()==0.5)
				layout.removeViewAt(j);
			else
				j++;
		}
	}
	public void cancelDel(ScrollView scrol)
	{
		LinearLayout layout=((LinearLayout)scrol.getChildAt(0));
		LinearLayout l;
		for (int i=0;i<layout.getChildCount();i++)
		{
			l=(LinearLayout)((LinearLayout)(layout.getChildAt(i))).getChildAt(1);
			if (l.getAlpha()==0.5)
				l.setAlpha(1.0f);
		}
		
		delete=false;
		((Button)findViewById(R.id.del)).setText("  -  ");
		((Button)findViewById(R.id.add)).setText("  +  ");
	}
	public void startDelMode()
	{
		delete=true;
		((Button)findViewById(R.id.del)).setText(" X ");
		((Button)findViewById(R.id.add)).setText(" V ");
	}
	public void initialization()
	{
		((TextView)findViewById(R.id.name)).setText(name);
		ScrollView scrol=(ScrollView)findViewById(R.id.scroll);
		LinearLayout layout=(LinearLayout)((LinearLayout)scrol.getChildAt(0)).getChildAt(0);
		LinearLayout layout1=(LinearLayout)layout.getChildAt(0);
		LinearLayout layout2=(LinearLayout)layout.getChildAt(1);
		layout_params=layout.getLayoutParams();
		layout1_params=layout1.getLayoutParams();
		layout2_params=layout2.getLayoutParams();
		text1_params=layout2.getChildAt(0).getLayoutParams();
		space1_params=layout2.getChildAt(1).getLayoutParams();
		text2_params=layout2.getChildAt(2).getLayoutParams();
		((LinearLayout)scrol.getChildAt(0)).removeAllViews();
	}
	public void showRecords(ScrollView scrol,int a)
	{
		((LinearLayout)scrol.getChildAt(0)).removeAllViews();
		for(int i=0;i<a;i++)
		{
			addRecord(currentDate(), i);
		}
	}
	public void addAlertDialog()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Новое имя для категории");
		alert.setMessage("Введите новое имя");
		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("подтвердить", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  Editable value = input.getText();
		  TextView title= ((TextView)findViewById(R.id.name));
		  title.setText(value.subSequence(0, value.length()));
		  title.setFontFeatureSettings("normal");
		  }
		});

		alert.setNegativeButton("отмена", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
	}
	public String currentDate()
	{
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day+"."+month+"."+year%100;
	}
	
	public static void setName(CharSequence n)
	{
		
		name=n;
	}
	public void recordsFromModel()
	{
		Record r;
        for (int i=0;i<m.getObj(type).getCategory((String)name).getRecordsCount();i++)
        {
        	r=m.getObj(type).getCategory((String)name).getRecord(i);
        	addRecord("record"+i, r.getMoney());
        }
	}
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		m.readFromFile(this);
		initialization();
		
		recordsFromModel();//������ �����
		
	}
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		m.writeToFile(this);
	}
	

}
