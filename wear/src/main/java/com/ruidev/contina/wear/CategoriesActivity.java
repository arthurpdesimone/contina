package com.ruidev.contina.wear;

import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WearableRecyclerView;

import com.ruidev.contina.wear.adapter.CategoriesAdapter;

public class CategoriesActivity extends WearableActivity {

    CategoriesAdapter categoriesAdapter;
    BoxInsetLayout categoryBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setAmbientEnabled();

        final WearableRecyclerView wearableRecyclerView = (WearableRecyclerView) findViewById(R.id.activity_categories_recycler_view);
        categoriesAdapter = new CategoriesAdapter(this);
        categoryBackground = (BoxInsetLayout) findViewById(R.id.activity_categories_background);
        wearableRecyclerView.setAdapter(categoriesAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(wearableRecyclerView);

    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        categoriesAdapter.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        categoriesAdapter.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        categoriesAdapter.onExitAmbient();
        updateDisplay();
    }

    public void updateDisplay() {
        if (isAmbient()){

            categoryBackground.setBackgroundColor(getResources().getColor(R.color.black));

        }else {

            categoryBackground.setBackgroundColor(getResources().getColor(R.color.light_blue_dark_background));

        }
    }
}
