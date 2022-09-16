package com.corporation8793.festival;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog {

    TextView dialogDateText, dialogNumText;
    Button okButton, closeButton;

    public CustomDialog(@NonNull Context context, String contents) {
        super(context);
        setContentView(R.layout.activity_custom_dialog);

        dialogDateText = findViewById(R.id.dialogDateText);
        dialogNumText = findViewById(R.id.dialogNumText);

        okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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