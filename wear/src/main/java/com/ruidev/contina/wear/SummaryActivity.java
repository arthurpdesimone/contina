package com.ruidev.contina.wear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

public class SummaryActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_summary);
    }
}
