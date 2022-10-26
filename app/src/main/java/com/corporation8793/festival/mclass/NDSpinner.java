package com.corporation8793.festival.mclass;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import java.lang.reflect.Field;

public class NDSpinner extends AppCompatSpinner {

//    private android.widget.ListPopupWindow popupWindow;

    public NDSpinner(Context context)
    { super(context); }

    public NDSpinner(Context context, AttributeSet attrs)
    { super(context, attrs); }

    public NDSpinner(Context context, AttributeSet attrs, int defStyle)
    { super(context, attrs, defStyle); }

    @Override
    public void setSelection(int position, boolean animate) {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position, animate);
        if (sameSelected) {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        }
    }

    @Override
    public void setSelection(int position) {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position);
        if (sameSelected) {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        }
    }

    public interface OnSpinnerEventsListener {

        //Callback triggered when the spinner was opened.
        void onSpinnerOpened(Spinner spinner);

        //Callback triggered when the spinner was closed.
        void onSpinnerClosed(Spinner spinner);
    }

   /* @Override
    public void setAdapter(SpinnerAdapter adapter) {
        super.setAdapter(adapter);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            popupWindow = (android.widget.ListPopupWindow) popup.get(this);
            popupWindow.setHeight(100);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }*/
}
