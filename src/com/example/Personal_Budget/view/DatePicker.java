package com.example.Personal_Budget.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import java.util.Calendar;

//import android.support.v4.app.DialogFragment;

public class DatePicker extends android.app.DialogFragment
        implements DatePickerDialog.OnDateSetListener {
 
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
        // ���������� ������� ����
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
 
        // ������� DatePickerDialog � ���������� ���
        Dialog picker = new DatePickerDialog(getActivity(), this, 
                year, month, day);
        picker.setTitle("�������� ����");
 
        return picker;
    }
    @Override
    public void onStart() {
        super.onStart();
        // ��������� ��������� ����� ��� ������
        Button nButton =  ((AlertDialog) getDialog())
                .getButton(DialogInterface.BUTTON_POSITIVE);
        nButton.setText("������");
 
    }
 
    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, 
            int month, int day) {
 
        //TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        //tv.setText(day + "-" + month + "-" + year);
    }
}