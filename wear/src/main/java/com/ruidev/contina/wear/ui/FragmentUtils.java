package com.ruidev.contina.wear.ui;

import android.app.Activity;
import android.app.Fragment;
import android.widget.FrameLayout;

/**
 * Created by ruiri on 11/03/2017.
 */

public abstract class FragmentUtils {
    public static int inflateFragment(FrameLayout container, Fragment frag){
        container.removeAllViews();
        return ((Activity)container.getContext()).getFragmentManager()
                .beginTransaction()
                .add(container.getId(), frag)
                .commit();
    }
}
