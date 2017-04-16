package com.ruidev.contina.wear;

import android.os.Bundle;
import android.view.View;

import java.util.Date;

/**
 * Created by ruiri on 06/03/2017.
 */

public interface WearableEvents {

    void onEnterAmbient(Bundle ambientDetails);

    void onUpdateAmbient();

    void onExitAmbient();

    void updateDisplay();

}
