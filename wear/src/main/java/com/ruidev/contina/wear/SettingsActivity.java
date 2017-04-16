package com.ruidev.contina.wear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

public class SettingsActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
    }
}
