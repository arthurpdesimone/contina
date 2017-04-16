package com.ruidev.contina.wear;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.drawer.WearableDrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ruidev.contina.model.CategoryType;

public class NewItemActivity extends WearableActivity {

    TextView newItemCategory;
    ImageView dateItemImage;
    TextView dateItemText;
    ImageView timeItemImage;
    TextView timeItemText;
    ImageView priceItemImage;
    TextView priceItemText;
    CircledImageView saveItemButton;
    WearableDrawerLayout wearableDrawerLayout;

    CategoryType categoryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        setAmbientEnabled();

        LinearLayout datePicker;
        LinearLayout timePicker;
        LinearLayout pricePicker;

        categoryType = CategoryType.getCateroryTypebyId(
                getIntent().getExtras().getInt("id"));

        datePicker = (LinearLayout) findViewById(R.id.activity_new_item_date_ll);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(NewItemActivity.this);
                    datePickerDialog.show();
                }
            }
        });

        timePicker = (LinearLayout) findViewById(R.id.activity_new_item_time_ll);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(NewItemActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        }
                    }, 0, 0, true);
                    timePickerDialog.show();
                }
            }
        });

        pricePicker = (LinearLayout) findViewById(R.id.activity_new_item_price_ll);

        newItemCategory = (TextView) findViewById(R.id.activity_new_item_category);
        dateItemImage = (ImageView) findViewById(R.id.activity_new_item_date_image);
        dateItemText = (TextView) findViewById(R.id.activity_new_item_date_text);
        timeItemImage = (ImageView) findViewById(R.id.activity_new_item_time_image);
        timeItemText = (TextView) findViewById(R.id.activity_new_item_time_text);
        priceItemImage = (ImageView) findViewById(R.id.activity_new_item_price_image);
        priceItemText = (TextView) findViewById(R.id.activity_new_item_price_text);
        saveItemButton = (CircledImageView) findViewById(R.id.activity_new_item_save_image);
        wearableDrawerLayout = (WearableDrawerLayout) findViewById(R.id.activity_new_item_drawer_layout);

        newItemCategory.setTextColor(getResources().getColor(categoryType.getColorAccent()));
        newItemCategory.setText(categoryType.getName());
        Drawable drawable = getResources().getDrawable(R.drawable.bg_round);
        drawable.setColorFilter(getResources().getColor(categoryType.getColorLighterElement()), PorterDuff.Mode.SRC_OVER);
        dateItemImage.setBackground(getResources().getDrawable(categoryType.getColorBackground()));
        dateItemText.setTextColor(getResources().getColor(R.color.white));
        timeItemImage.setBackground(drawable);
        timeItemText.setTextColor(getResources().getColor(R.color.white));
        priceItemImage.setBackground(drawable);
        priceItemText.setTextColor(getResources().getColor(R.color.white));
        saveItemButton.setCircleColor(getResources().getColor(categoryType.getColorActiveElement()));
        saveItemButton.setCircleBorderWidth(0f);
        wearableDrawerLayout.setBackgroundColor(getResources().getColor(categoryType.getColorBackground()));
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {

            newItemCategory.setTextColor(getResources().getColor(R.color.white));
            dateItemImage.setBackground(null);
            dateItemText.setTextColor(getResources().getColor(R.color.white));
            timeItemImage.setBackground(null);
            timeItemText.setTextColor(getResources().getColor(R.color.white));
            priceItemImage.setBackground(null);
            priceItemText.setTextColor(getResources().getColor(R.color.white));
            saveItemButton.setCircleColor(getResources().getColor(R.color.transparent));
            saveItemButton.setBackground(getResources().getDrawable(R.drawable.bg_round_stroke_white));
            wearableDrawerLayout.setBackgroundColor(getResources().getColor(R.color.black));

        }
        else{
            newItemCategory.setTextColor(getResources().getColor(categoryType.getColorAccent()));
            newItemCategory.setText(categoryType.getName());
            Drawable drawable = getResources().getDrawable(R.drawable.bg_round);
            drawable.setColorFilter(getResources().getColor(categoryType.getColorLighterElement()), PorterDuff.Mode.SRC_OVER);
            dateItemImage.setBackground(drawable);
            dateItemText.setTextColor(getResources().getColor(R.color.white));
            timeItemImage.setBackground(drawable);
            timeItemText.setTextColor(getResources().getColor(R.color.white));
            priceItemImage.setBackground(drawable);
            priceItemText.setTextColor(getResources().getColor(R.color.white));
            saveItemButton.setCircleColor(getResources().getColor(categoryType.getColorActiveElement()));
            saveItemButton.setBackground(null);
            wearableDrawerLayout.setBackgroundColor(getResources().getColor(categoryType.getColorBackground()));
        }
    }
}
