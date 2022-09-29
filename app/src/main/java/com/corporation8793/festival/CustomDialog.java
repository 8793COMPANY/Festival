package com.corporation8793.festival;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog {

    TextView dialogDateText, dialogNumText;
    Button okButton, closeButton;

    public CustomDialog(@NonNull Context context, String total, String num, String name) {
        super(context);
        setContentView(R.layout.activity_custom_dialog);

        dialogDateText = findViewById(R.id.dialogDateText);
        dialogNumText = findViewById(R.id.dialogNumText);

        dialogDateText.setText("예약날짜: " + total);
        dialogNumText.setText("예약인원: " + num + "명");

        okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                BreakdownFragment breakdownFragment = new BreakdownFragment();

                Bundle bundle = new Bundle();
                bundle.putString("예약내역", "추가");
                bundle.putString("예약날짜", total);
                bundle.putString("예약인원", num + "명");
                bundle.putString("알람예약축제이름", name);

                breakdownFragment.setArguments(bundle);
                transaction.replace(R.id.containers,breakdownFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                dismiss();
                /*
                Intent intent = new Intent(context, BreakdownFragment.class);
                intent.putExtra("예약날짜", total);
                intent.putExtra("예약인원", num + "명");
                intent.putExtra("알람예약축제이름", name);
                context.startActivity(intent);*/
            }
        });

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
    /*
    public void callDialog() {
        Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.activity_custom_dialog);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        dialog.show();
    }
     */
}