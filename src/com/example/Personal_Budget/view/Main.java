package com.example.Personal_Budget.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.Personal_Budget.R;
import com.example.Personal_Budget.model.Model;


import java.util.Calendar;

public class Main extends Activity {
	Model m;
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		m=new Model();
		
		//m.str="����� �����";
		setContentView(R.layout.activity_main);
		
		
		Button rashod = (Button)findViewById(R.id.rashod);
		rashod.setText("расход \n"+m.getObj("расходы").summCategories(Calendar.getInstance().get(Calendar.MONTH))+" руб.");
        rashod.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Kategories_view.setType("расходы");
				Kategories_view.setBackgrounds(R.drawable.gradient_r, R.drawable.gradient_rk);
				
				
				
				Intent SecAct = new Intent(getApplicationContext(), Kategories_view.class);
                startActivity(SecAct);
				
			}
		});
        Button dohod = (Button)findViewById(R.id.dohod);
        dohod.setText("доход \n"+m.getObj("доходы").summCategories(Calendar.getInstance().get(Calendar.MONTH))+" руб.");
        dohod.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Kategories_view.setType("доходы");
				Kategories_view.setBackgrounds(R.drawable.gradient_d, R.drawable.gradient_dk);
				
				

				Intent SecAct = new Intent(getApplicationContext(), Kategories_view.class);
                startActivity(SecAct);
				
			}
		});
        
        Button stat = (Button)findViewById(R.id.stat);
        stat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent SecAct = new Intent(getApplicationContext(), Statistic_view.class);
               // startActivity(SecAct);

				
			}
		});
        TextView date=(TextView)findViewById(R.id.name);
        date.setText("дата: "+currentDate());
        
       
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public String currentDate()
	{
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day+"."+month+"."+year%100;
	}
	


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		m.readFromFile(this);
		TextView balance=(TextView)findViewById(R.id.food);
	    balance.setText("баланс: "+m.getBalance( Calendar.getInstance().get(Calendar.MONTH))+" руб.");
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		m.writeToFile(this);
	}
	
	
	


			
}
