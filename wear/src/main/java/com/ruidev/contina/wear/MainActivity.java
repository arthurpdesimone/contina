package com.ruidev.contina.wear;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.drawer.WearableDrawerLayout;
import android.support.wearable.view.drawer.WearableNavigationDrawer;
import android.util.Log;
import android.widget.FrameLayout;

import com.ruidev.contina.wear.fragments.ExpensesFragment;
import com.ruidev.contina.wear.fragments.SettingsFragment;
import com.ruidev.contina.wear.fragments.SummaryFragment;

import java.util.ArrayList;

import static com.ruidev.contina.wear.ui.FragmentUtils.inflateFragment;

public class MainActivity extends WearableActivity implements SummaryFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, ExpensesFragment.OnFragmentInteractionListener{

    private WearableDrawerLayout mContainerView;
    private WearableNavigationDrawer navigationDrawer;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (WearableDrawerLayout) findViewById(R.id.drawer_layout);
        frameLayout = (FrameLayout) findViewById(R.id.activity_main_fragment);

        navigationDrawer = (WearableNavigationDrawer) findViewById(R.id.top_navigation_drawer);
        navigationDrawer.setAdapter(new NavigationAdapter (MainActivity.this));

      /*  Main Wearable Drawer Layout that wraps all content
        mWearableDrawerLayout = (WearableDrawerLayout) findViewById(R.id.drawer_layout);
        mWearableDrawerLayout.peekDrawer(Gravity.BOTTOM);
        mWearableDrawerLayout.peekDrawer(Gravity.TOP);

         Top Navigation Drawer
        mWearableNavigationDrawer = (WearableNavigationDrawer) findViewById(R.id.top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(new ArrayAdapter<String>(this, R.id.bottom_action_drawer, getResources().getStringArray(R.array.navigation_drawer_list)));

         Peeks Navigation drawer on the top.
        mWearableDrawerLayout.peekDrawer(Gravity.TOP);

         Bottom Action Drawer
        mWearableActionDrawer = (WearableActionDrawer) findViewById(R.id.bottom_action_drawer); */
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /*@Override
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
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.VISIBLE);
            primaryActionButton.setBackground(getResources().getDrawable(R.drawable.bg_round_stroke_white));

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackgroundColor(getResources().getColor(R.color.light_blue_dark_background));
            mClockView.setVisibility(View.GONE);
            primaryActionButton.setBackground(getResources().getDrawable(R.drawable.bg_round_light_blue_active_element));
        }
    }*/

    private final class NavigationAdapter
            extends WearableNavigationDrawer.WearableNavigationDrawerAdapter {

        private final Context mContext;
        private int selectedItem;

        ArrayList<NavigationDrawerItem> navigationDrawerItemList = new ArrayList<NavigationDrawerItem>();

        public NavigationAdapter(Context context) {
            mContext = context;

            navigationDrawerItemList.add(NavigationDrawerItem.EXPENSES);
            navigationDrawerItemList.add(NavigationDrawerItem.CHART);
            navigationDrawerItemList.add(NavigationDrawerItem.SETTINGS);
        }

        @Override
        public int getCount() {
            return navigationDrawerItemList.size();
        }

        @Override
        public void onItemSelected(int position) {
            Log.d("TAG", "WearableNavigationDrawerAdapter.onItemSelected(): " + position);
            selectedItem = position;
            position = navigationDrawerItemList.indexOf(navigationDrawerItemList.get(position));
            if (position == 0){
                inflateFragment (frameLayout, new ExpensesFragment());
            }else if (position == 1){
                inflateFragment (frameLayout, new SummaryFragment());
            }else if (position == 2){
                inflateFragment (frameLayout, new SettingsFragment());;
            }

        }

        @Override
        public String getItemText(int pos) {
            return getResources().getString(navigationDrawerItemList.get(pos).getMenuText());
        }

        @Override
        public Drawable getItemDrawable(int pos) {
            return getResources().getDrawable(navigationDrawerItemList.get(pos).getMenuImage());
        }
    }
}
