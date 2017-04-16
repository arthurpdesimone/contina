package com.ruidev.contina;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ruidev.contina.model.Item;
import com.ruidev.contina.model.Payment;
import com.stephentuso.welcome.WelcomeScreenHelper;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.RunnableFuture;

import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String CLASS_NAME = "MainActivity";
    public static final int NEW_MODE = 0;
    public static final int EDIT_MODE = 1;
    private int MODE = NEW_MODE;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;
    View bottomSheet;
    BottomSheetBehavior behavior;
    ViewPager mPager;
    PagerAdapter mPagerAdapter;
    ArrayList<Fragment> fragments = new ArrayList<>();
    CardView itemCard;
    Dialog dialogNewItem;
    Dialog dialogSettings;
    Dialog dialogItem;
    TextView dateMonth;
    Spinner spinnerSettings;
    TextView doneButton;

    Handler handler;
    View mainView;
    View categoryColor;
    WelcomeScreenHelper welcomeScreen;
    LinearLayout itemClick;

    ScrollView mainScrollView;

    ImageView settingsButton;
    ImageView categoryHealth;
    ImageView categoryRestaurant;
    ImageView categoryMarket;
    ImageView categoryAnimals;
    ImageView categoryEntertainment;
    ImageView categoryHome;
    ImageView categoryElectronics;
    ImageView categoryBank;
    ImageView categoryPersonal;
    ImageView categoryTransport;
    ImageView categoryClothing;
    ImageView categoryOthers;
    ImageView closeBottomSheet;

    EditText itemWhere;
    TextView itemSave;
    EditText itemWhat;
    EditText itemPrice;
    RecyclerView itemRecyclerView;
    private Realm realm;
    //ImageView dropDown;
    Item item;

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);


        final Toolbar myToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(myToolbar);
        realm = Realm.getDefaultInstance();
        fab = (FloatingActionButton) findViewById(R.id.activity_main_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main_coordinator);
        bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setPeekHeight(0);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        dialogNewItem = new Dialog(this);
        dialogNewItem.setContentView(R.layout.fragment_dialog_new_item);


        dialogSettings = new Dialog(this);
        dialogSettings.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSettings.setContentView(R.layout.activity_settings);

        ArrayList<String> currencies = new ArrayList<>();
        for(Currency currency : Currency.getAvailableCurrencies()){
            currencies.add(currency.getCurrencyCode());
        };

        spinnerSettings = (Spinner) dialogSettings.findViewById(R.id.activity_settings_currency_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, currencies);
        spinnerSettings.setAdapter(arrayAdapter);
        Locale myLocale = Resources.getSystem().getConfiguration().locale;
        Currency myCurrency = Currency.getInstance(myLocale);
        int idMyCurrency = currencies.indexOf(myCurrency.getCurrencyCode());
        spinnerSettings.setSelection(idMyCurrency);


        categoryHealth = (ImageView) findViewById(R.id.fragment_dialog_categories_health);
        categoryRestaurant = (ImageView) findViewById(R.id.fragment_dialog_categories_restaurant);
        categoryMarket = (ImageView) findViewById(R.id.fragment_dialog_categories_market);
        categoryAnimals = (ImageView) findViewById(R.id.fragment_dialog_categories_animals);
        categoryEntertainment = (ImageView) findViewById(R.id.fragment_dialog_categories_entertainment);
        categoryHome = (ImageView) findViewById(R.id.fragment_dialog_categories_home);
        categoryElectronics = (ImageView) findViewById(R.id.fragment_dialog_categories_electronics);
        categoryBank = (ImageView) findViewById(R.id.fragment_dialog_categories_bank);
        categoryPersonal = (ImageView) findViewById(R.id.fragment_dialog_categories_personal);
        categoryTransport = (ImageView) findViewById(R.id.fragment_dialog_categories_transport);
        categoryClothing = (ImageView) findViewById(R.id.fragment_dialog_categories_clothing);
        categoryOthers = (ImageView) findViewById(R.id.fragment_dialog_categories_others);
        settingsButton = (ImageView) findViewById(R.id.activity_main_settings);


        closeBottomSheet = (ImageView) findViewById(R.id.fragment_dialog_categories_close);
        doneButton = (TextView) dialogSettings.findViewById(R.id.activity_settings_done);
        itemSave = (TextView) dialogNewItem.findViewById(R.id.fragment_dialog_new_item_save);
        itemWhere = (EditText) dialogNewItem.findViewById(R.id.fragment_dialog_where);
        itemWhat = (EditText) dialogNewItem.findViewById(R.id.fragment_dialog_what);
        itemPrice = (EditText) dialogNewItem.findViewById(R.id.fragment_dialog_price);
        categoryColor = (View) dialogNewItem.findViewById(R.id.fragment_dialog_new_item_category);
        itemRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);



        welcomeScreen = new WelcomeScreenHelper(this, MyWelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);

        dialogItem = new Dialog(this);
        dialogItem.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogItem.setContentView(R.layout.fragment_fullscreen_item);

        itemClick = (LinearLayout) findViewById(R.id.fragment_history_item);
        //dropDown = (ImageView) findViewById(R.id.fragment_history_item_drop_down);

        dialogNewItem.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                switch (getMODE()){
                    case EDIT_MODE:{
                        itemWhere.setText(item.getItemWhere());
                        itemWhat.setText(item.getItemWhat());
                    }
                        break;
                    case NEW_MODE:{

                    }
                        break;
                }
            }
        });

        /*mainScrollView = (ScrollView) findViewById(R.id.activity_main_scroll_view);
        mainScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > 0){
                    myToolbar.setElevation(12);
                    myToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    //Log.i("SCROLL" , "certo");
                }else {
                    myToolbar.setElevation(0);
                    myToolbar.setBackgroundColor(getResources().getColor(R.color.background));
                }
            }
        });*/
        setClickListener(fab,categoryHealth,categoryRestaurant, categoryMarket, categoryAnimals,
                categoryEntertainment, categoryHome, categoryElectronics, categoryBank,
                categoryPersonal, categoryTransport, categoryClothing, categoryOthers,itemSave);

/*        setClickListener(itemClick, itemSave, itemWhat, itemWhere, itemPrice,
                            itemDate, itemTime, itemCard, itemEdit);*/

        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setLocalNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
            // Now recreate for it to take effect
            recreate();
        }

        setupUI();
    }

    public void clearDatabase(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
    public void setupUI() {
        Log.i(CLASS_NAME, "Setup UI");

        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //final ItemAdapter adapter = new ItemAdapter(this, realm.where(Item.class).findAll());
        //itemRecyclerView.setAdapter(adapter);

        Log.i(CLASS_NAME, "RecyclerView animator : " + itemRecyclerView.getItemAnimator().getClass().getName());

        /** Scrollaware fab*/
        itemRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });



        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                itemRecyclerView,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        final int id = itemRecyclerView.getChildPosition(view);
                        //final Item item = adapter.getItem(id);
                        Log.i(CLASS_NAME,"onDismiss ("+item.toString()+":"+id+")");


                        clickDeleteRecyclerView(item);
                        //adapter.notifyItemRemoved(id);
                        /*Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Delete"+item.toString(), Snackbar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Snackbar undoSnackBar = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                                        undoSnackBar.show();
                                    }
                                }).setCallback(new Snackbar.Callback() {
                                    @Override
                                    public void onDismissed(Snackbar snackbar, int event) {
                                        super.onDismissed(snackbar, event);

                                    }
                                }).setDuration(Snackbar.LENGTH_LONG);

                        snackbar.show();
*/


                    }
                })
                .setIsVertical(false)
                .setItemClickCallback(new SwipeDismissRecyclerViewTouchListener.OnItemClickCallBack() {
                    @Override
                    public void onClick(int i) {
                        Log.i(CLASS_NAME,"onClick "+i);
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putInt("position",i);
                        message.setData(bundle);
                        //adapter.handler.get((int) i).sendMessage(message);
                    }
                })
                .setItemTouchCallback(
                        new SwipeDismissRecyclerViewTouchListener.OnItemTouchCallBack() {
                            @Override
                            public void onTouch(int index) {
                                Log.i(CLASS_NAME,"onTouch");
                                //showDialog(String.format("Click item %d", index));
                            }
                        })
                .create();

        itemRecyclerView.setOnTouchListener(listener);

    }

    /** Method to open an item and update it
     * @param item*/
    public void clickOpenEditDialog(Item item){
        /** Store as public reference */
        this.item = item;
        dialogNewItem.show();
    }

    /** Method to delete item
     * @param item the item to be deleted */
    public void clickDeleteRecyclerView(final Item item){
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                   // RealmResults<Item> result = realm.where(Item.class).equalTo("id",item.getId()).findAll();
                    //result.deleteAllFromRealm();
                }
            });
        }catch (Exception e){
            Log.i(CLASS_NAME,e.getMessage());
        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode)
        {
            case Configuration.UI_MODE_NIGHT_NO:
                Log.i("MainActivity", "Night mode is not active, we're in day time");
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                Log.i("MainActivity", "Night mode is active, we're at night!");
                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                Log.i("MainActivity", "We don't know what mode we're in, assume notnight");
                break;
        }
    }

    public void setClickListener(View... views){
        for(View v : views){
            v.setOnClickListener(this);
        }
    }

    public void callDialog(){
        //The View with the BottomSheetBehavior
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        getDelegate().applyDayNight();
    }

    @Override
    public void onClick(final View v) {

        switch (v.getId())
        {
            case R.id.activity_main_fab:
                callDialog();
                break;
            case R.id.fragment_dialog_categories_close:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.fragment_dialog_categories_health:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_health));
                break;
            case R.id.fragment_dialog_categories_restaurant:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_restaurant));
                break;
            case R.id.fragment_dialog_categories_market:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_store));
                break;
            case R.id.fragment_dialog_categories_animals:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_animals));
                break;
            case R.id.fragment_dialog_categories_entertainment:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_entertainment));
                break;
            case R.id.fragment_dialog_categories_home:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_home));
                break;
            case R.id.fragment_dialog_categories_electronics:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_electronic));
                break;
            case R.id.fragment_dialog_categories_bank:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_bank));
                break;
            case R.id.fragment_dialog_categories_personal:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_personal));
                break;
            case R.id.fragment_dialog_categories_transport:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_transport));
                break;
            case R.id.fragment_dialog_categories_clothing:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_cloth));
                break;
            case R.id.fragment_dialog_categories_others:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 195);
                categoryColor.setBackgroundColor(getResources().getColor(R.color.bg_others));
                break;
            case R.id.activity_main_settings:
                dialogSettings.show();
                break;
            case R.id.activity_settings_done:
                dialogSettings.cancel();
                break;
            case R.id.fragment_dialog_new_item_cancel:
                dialogNewItem.cancel();
                break;
            case R.id.fragment_dialog_new_item_save:
                String itemWhat = this.itemWhere.getText().toString();
                String itemWhere = this.itemWhere.getText().toString();
                double price = Double.parseDouble(this.itemPrice.getText().toString());
                Date date = new Date("20/08/2016");

                Payment paymentType;

                // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
                RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
                Realm.setDefaultConfiguration(realmConfig);

                /** Realm
                Realm r = Realm.getDefaultInstance();
                r.beginTransaction();
                int idItem = 0;
                if((r.where(Item.class).max("id")) != null){
                    idItem = (int) (r.where(Item.class).max("id").intValue())+1;
                }
                //Categories category = new Categories();
                Item item = new Item(idItem,itemWhat,itemWhere,price,date,null,null);
                Log.i("Adicionou um item",item.toString());
                r.copyToRealm(item);
                r.commitTransaction();*/



                //dialogNewItem.dismiss();
                break;
            case R.id.fragment_history_item_edit:
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogNewItem.show();
                    }
                }, 225);
                dialogNewItem.show();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }


    public int getMODE() {
        return MODE;
    }

    public void setMODE(int MODE) {
        this.MODE = MODE;
    }
}
