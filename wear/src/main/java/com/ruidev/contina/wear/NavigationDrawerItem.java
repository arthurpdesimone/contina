package com.ruidev.contina.wear;
import android.app.Fragment;

import com.ruidev.contina.wear.fragments.ExpensesFragment;
import com.ruidev.contina.wear.fragments.SettingsFragment;
import com.ruidev.contina.wear.fragments.SummaryFragment;

/**
 * Created by ruiri on 28/02/2017.
 */

public enum NavigationDrawerItem {

    EXPENSES(R.string.menu_expenses, R.drawable.ic_attach_money_white_48dp, R.layout.fragment_expenses),
    CHART(R.string.menu_chart, R.drawable.ic_pie_chart_white_48dp, R.layout.fragment_summary),
    SETTINGS(R.string.menu_settings, R.drawable.ic_settings_white_48dp, R.layout.fragment_settings);

    private int menuText;
    private int menuImage;
    private final int menuTextColor = R.color.white;
    private int layout;

    NavigationDrawerItem(int menuText, int menuImage, int layout) {
        this.menuText = menuText;

        this.menuImage = menuImage;
        this.layout = layout;
    }

    public int getMenuText() {
        return menuText;
    }

    public void setMenuText(int menuText) {
        this.menuText = menuText;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public int getMenuTextColor() {
        return menuTextColor;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public Fragment getFragment(){
     switch (layout){
         case R.layout.fragment_expenses:
             return new ExpensesFragment();

         case R.layout.fragment_summary:
             return new SummaryFragment();

         case R.layout.fragment_settings:
             return new SettingsFragment();
     }
        return null;
    }
}
