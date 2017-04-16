package com.ruidev.contina;

import android.os.Bundle;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by ruiri on 06/07/2016.
 */
public class MyWelcomeActivity extends WelcomeActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_activities);
    }

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.WelcomeScreenTheme)
                .parallaxPage(R.layout.welcome1, "PLATA", "Your best app to note expenses.", R.color.md_teal_500, 0.2f, 2f)
                .parallaxPage(R.layout.welcome3, "DAY MODE", "Your app stays in a light theme when its day.", R.color.md_blue_300, 0.2f, 2f)
                .parallaxPage(R.layout.welcome2, "NIGHT MODE", "Your app turns into a dark theme when its night.", R.color.bg_night, 0.2f, 2f)
                .exitAnimation(android.R.anim.fade_out)
                .swipeToDismiss(true)
                .build();
    }
}
