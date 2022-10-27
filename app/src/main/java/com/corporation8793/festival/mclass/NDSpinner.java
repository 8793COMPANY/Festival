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

    private OnSpinnerEventsListener mListener;
    private boolean mOpenInitiated = false;

    @Override
    public boolean performClick() {
        // register that the Spinner was opened so we have a status
        // indicator for when the container holding this Spinner may lose focus
        mOpenInitiated = true;
        if (mListener != null) {
            mListener.onSpinnerOpened(this);
        }
        return super.performClick();
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        if (hasBeenOpened() && hasFocus) {
            performClosedEvent();
        }
    }

    public void setSpinnerEventsListener(
            OnSpinnerEventsListener onSpinnerEventsListener) {
        mListener = onSpinnerEventsListener;
    }

    public void performClosedEvent() {
        mOpenInitiated = false;
        if (mListener != null) {
            mListener.onSpinnerClosed(this);
        }
    }

    public boolean hasBeenOpened() {
        return mOpenInitiated;
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
