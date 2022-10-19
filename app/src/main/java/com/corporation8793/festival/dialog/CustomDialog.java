package com.corporation8793.festival.dialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.corporation8793.festival.activity.MainActivity;
import com.corporation8793.festival.R;
import com.corporation8793.festival.fragment.BreakdownFragment;

public class CustomDialog extends Dialog {

    TextView dialogDateText, dialogNumText;
    Button okButton, closeButton;

    public CustomDialog(@NonNull Context context, String total, String num, String name, int uid) {
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
                bundle.putInt("알람사용자구분", uid);

                breakdownFragment.setArguments(bundle);
                transaction.replace(R.id.containers,breakdownFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                dismiss();
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
}