package com.example.user.project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;
import com.stacktips.view.CalendarListener;
import com.stacktips.view.CustomCalendarView;
import com.stacktips.view.DayView;
import com.stacktips.view.utils.CalendarUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * In this activity we have set different text along
 * with differnt color of text in it .Also
 * we have created a dialogBox and inflated it with button clicked
 *
 * @author UtkarshSundaram
 */
public class MainActivity extends AppCompatActivity {
    private TextView mHeadingTransaction;
    private TextView mHeadingSales;
    private Button mExportReport;
    private ImageView mDatePicker;
    CustomCalendarView calendarView;
    ToolTip tooltip;
    Dialog dialog;
    private TextView mDate;
    private EditText mEmailId;
    private ToolTipRelativeLayout mToolTipFrameLayout;
    private ToolTipView mPurpleToolTipView;
    boolean flag = true;

    //SimpleDateFormat df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolTipFrameLayout = (ToolTipRelativeLayout) findViewById(R.id.activity_main_tooltipframelayout);
        mHeadingTransaction = (TextView) findViewById(R.id.txvHeadingTransaction);
        mHeadingSales = (TextView) findViewById(R.id.txvHeadingSale);
        mExportReport = (Button) findViewById(R.id.btnExportReport);
        mDatePicker = (ImageView) findViewById(R.id.imgDatePicker);
        mDate = (TextView) findViewById(R.id.txvDateDisplay);

        setTheHeading();
        onButtonClicked();

    }

    /**
     * This function deals with the event which happens
     * when we clicked the button
     */
    private void onButtonClicked() {
        mExportReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rect rectf = new Rect();
                v.getGlobalVisibleRect(rectf);
                Log.d("WIDTH        :", String.valueOf(rectf.width()));
                Log.d("HEIGHT       :", String.valueOf(rectf.height()));
                Log.d("left         :", String.valueOf(rectf.left));
                Log.d("right        :", String.valueOf(rectf.right));
                Log.d("top          :", String.valueOf(rectf.top));
                Log.d("bottom       :", String.valueOf(rectf.bottom));


                //createDateDialog();
                createToolView();
            }
        });

    }


    private void createToolView() {

        tooltip = new ToolTip().withContentView(LayoutInflater.from(this).inflate(R.layout.custom_tool_tip_layout, null))
                .withColor(getResources().getColor(R.color.DarkBlue))
                .withAnimationType(ToolTip.AnimationType.NONE);

        mPurpleToolTipView = mToolTipFrameLayout.showToolTipForView(tooltip, findViewById(R.id.imgDatePicker));

        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

        if (flag) {
            calendarView.setCalendarListener(new CalendarListener() {
                @Override
                public void onDateSelected(Date date) {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    mDate.setText("" + df.format(date));

                }

                @Override
                public void onMonthChanged(Date date) {
                    SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                    Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                }
            });
            flag = false;
            mToolTipFrameLayout.setVisibility(View.VISIBLE);
            //mToolTipFrameLayout.setVisibility(View.GONE);

        } else {
            flag = true;
            mToolTipFrameLayout.setVisibility(View.GONE);

        }

    }

    private void onSelectingDate() {

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                mDate.setText("" + df.format(date));

            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This function is to create dialog
     * windows when we click on the button
     */
    private void createDialog() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout_report);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mEmailId = (EditText) dialog.findViewById(R.id.input_email);
//        View dialog_root = dialog.findViewById(R.id.dialog_root);

        dialog.show();
//        dialog_root.requestFocus();

//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(mEmailId.getWindowToken(), 0);
    /*    final TextInputLayout mTextInputLayout=(TextInputLayout)dialog.findViewById(R.id.input_layout_email);
        mTextInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                 mTextInputLayout.setFocusableInTouchMode(true);
            }
        });*/

        mEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        ImageView imageViewCloseDialog = (ImageView) dialog.findViewById(R.id.imgDialogClosed);
        imageViewCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
/*private void createDateDialog()
{
    final Dialog dialog=new Dialog(MainActivity.this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

   WindowManager.LayoutParams abc= dialog.getWindow().getAttributes();
    abc.gravity = Gravity.CENTER|Gravity.RIGHT;
    abc.x = 50;
    abc.y=10;
    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    dialog.setContentView(R.layout.dialog_date_picker);
    calendarView=(CustomCalendarView) dialog.findViewById(R.id.calendar_view);
    Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
    calendarView.setShowOverflowDate(false);
    calendarView.refreshCalendar(currentCalendar);
    dialog.show();


}*/

    /**
     * This function is used to set the text in
     * textview mHeadingTransaction & mHeadingSales
     * with different color
     */
    private void setTheHeading() {
        String formatedTextForTransactionHeading = "<font color=#626A9C>Total no of </font> <font color=#434F8A>Transactions</font>";
        String formattedTextForSaleHeading = "<font color=#626A9C>Total</font> <font color=#434F8A>Sales</font>";
        if (Build.VERSION.SDK_INT >= 24) {

            mHeadingTransaction.setText(Html.fromHtml(formatedTextForTransactionHeading, Html.FROM_HTML_MODE_LEGACY));
            mHeadingSales.setText(Html.fromHtml(formattedTextForSaleHeading, Html.FROM_HTML_MODE_LEGACY));
        } else

        {
            mHeadingTransaction.setText(Html.fromHtml(formatedTextForTransactionHeading));
            mHeadingSales.setText(Html.fromHtml(formattedTextForSaleHeading));
        }
    }
}
