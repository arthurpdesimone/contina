package com.ruidev.contina.wear.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import com.ruidev.contina.model.Constants;
import com.ruidev.contina.model.Item;
import com.ruidev.contina.wear.R;

import java.util.HashMap;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by pendragon on 15/09/16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private static final String CLASS_NAME = "ItemAdapter";
    public HashMap<Integer,Handler> handler = new HashMap<>();

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    private int layoutId;

    public ItemAdapter(HashMap<Integer, Handler> handler, int layoutId) {
        this.handler = handler;
        this.layoutId = layoutId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_history_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Item obj = new Item();
        holder.data = obj;

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
                }else {
                    holder.view.setTag(0);
                }
            }
        };

        this.handler.put(position,handlerObject);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public Item data;

        public MyViewHolder(final View view) {
            super(view);
            this.view = view;
        }
    }
}
