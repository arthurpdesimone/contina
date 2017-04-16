package com.ruidev.contina;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruidev.contina.model.Constants;
import com.ruidev.contina.model.Item;
import com.ruidev.contina.model.ViewAnimationUtils;

import java.util.HashMap;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by pendragon on 15/09/16.

public class ItemAdapter extends RealmRecyclerViewAdapter<Item, ItemAdapter.MyViewHolder> {
    private static final String CLASS_NAME = "ItemAdapter";
    private final MainActivity activity;
    public HashMap<Integer,Handler> handler = new HashMap<>();

    public ItemAdapter(MainActivity activity, OrderedRealmCollection<Item> data) {
        super(activity ,data, true);
        this.activity = activity;
        Log.i(CLASS_NAME,"Criou adapter");

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.fragment_history_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Item obj = getData().get(position);
        holder.data = obj;
        holder.itemPrice.setText(obj.getPrice()+"");
        holder.itemWhat.setText(obj.getItemWhat()+" :::: "+position+" ::::: "+holder.getAdapterPosition()+"::::::"+obj.getId());

        // Set the view to fade in
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(Constants.FADE_IN_DURATION);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        holder.view.setAnimation(anim);
        holder.view.startAnimation(anim);


        Handler handlerObject = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //chamo um método para o tratamento da mensagem
                //e melhor organização do código.
                Log.i(CLASS_NAME,"Click handler "+msg.getData().getInt("position"));
                if(holder.view.getTag() == null ){
                    holder.view.setTag(0);
                }
                if ((int) holder.view.getTag() == 0){
                    holder.view.setTag(1);
                    ViewAnimationUtils.expand(holder.expandLinearLayout);
                }else {
                    holder.view.setTag(0);
                    ViewAnimationUtils.collapse(holder.expandLinearLayout);
                    holder.itemDelete.setVisibility(View.GONE);
                }
            }
        };

        this.handler.put(position,handlerObject);
    }



    class MyViewHolder extends RecyclerView.ViewHolder{



        public View view;
        public Item data;

        TextView itemCancel;
        TextView itemWhere;
        TextView itemWhat;
        TextView itemPrice;
        TextView itemDate;
        TextView itemTime;
        ImageView itemEdit;
        ImageView itemDelete;
        TextView dateMonth;
        RelativeLayout expandRelativeLayout;
        RelativeLayout expandLinearLayout;
        ImageView editCategory;
        ImageView colorCategory;

        public MyViewHolder(final View view) {
            super(view);
            this.view = view;
            itemPrice = (TextView) view.findViewById(R.id.fragment_history_item_price);
            itemWhat = (TextView) view.findViewById(R.id.fragment_history_item_name);
            itemEdit = (ImageView) view.findViewById(R.id.fragment_history_item_edit);
            itemDelete = (ImageView) view.findViewById(R.id.fragment_history_item_delete);
            dateMonth = (TextView) view.findViewById(R.id.fragment_history_item_date_month);
            editCategory = (ImageView) view.findViewById(R.id.fragment_history_item_category_icon);
            colorCategory = (ImageView) view.findViewById(R.id.fragment_history_item_category_color);
            expandLinearLayout = (RelativeLayout) view.findViewById(R.id.fragment_history_item_category_visible);
            expandRelativeLayout = (RelativeLayout) view.findViewById(R.id.fragment_history_item_category_header);

//            view.setOnClickListener(this);
//            itemEdit.setOnClickListener(this);
//            itemDelete.setOnClickListener(this);

        }


//        @Override
//        public void onClick(View v) {
//            Log.i(CLASS_NAME,"onClick");
//            ValueAnimator animatorDropDown;
//            ValueAnimator animatorCard;
//
//                /*int value = 16;
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) itemCard.getLayoutParams();
//                layoutParams.setMargins(value, value, value, value);
//                itemCard.setLayoutParams(layoutParams);*/
//
//            switch (v.getId()){
//                case R.id.fragment_history_item_edit : {
//
//                    activity.clickOpenEditDialog(data);
//                    activity.setMODE(MainActivity.EDIT_MODE);
//                    break;
//                }
//                case R.id.fragment_history_item_delete : {
//                    Log.i(CLASS_NAME,"Clicou no botão deletar "+getAdapterPosition());
//
//                    Animation animFadeOut =  new TranslateAnimation(0, expandLinearLayout.getMeasuredWidth(),0, 0);
//                    animFadeOut.setDuration(Constants.FADE_OUT_DURATION);
//                    animFadeOut.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {
//                            Log.i(CLASS_NAME,"Animation delete started");
//                            view.setTag(0);
//                            ViewAnimationUtils.collapse(expandLinearLayout);
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//                            Log.i(CLASS_NAME,"Animation delete ended");
//                            //activity.clickDeleteRecyclerView(data);
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//                            Log.i(CLASS_NAME,"Animation delete repeat");
//                        }
//                    });
//                    animFadeOut.setInterpolator(new AccelerateInterpolator());
//                    animFadeOut.setFillAfter(true);
//                    view.startAnimation(animFadeOut);
//                    break;
//
//                }
//                default:{
//
//                };
//            }
//
//
//        }
    //}
//}