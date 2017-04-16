package com.ruidev.contina.wear.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.activity.WearableActivityDelegate;
import android.support.wearable.view.CircledImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruidev.contina.model.Categories;
import com.ruidev.contina.model.CategoryType;
import com.ruidev.contina.wear.NewItemActivity;
import com.ruidev.contina.wear.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24/02/2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> implements WearableActivityDelegate.AmbientCallback{

    boolean ambientMode;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.fragment_categories, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Categories categories = mCategories.get(position);
        CategoryType categoryType = CategoryType.getCateroryTypebyId(categories.getId());
        TextView textView = holder.categoriesText;
        textView.setText(categoryType.getName());
        CircledImageView circledImageView = holder.circledImageView;
        circledImageView.setImageDrawable(getContext().getResources().getDrawable(categoryType.getResource()));
        circledImageView.setCircleColor(getContext().getResources().getColor(categoryType.getColorName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", categories.getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        if (ambientMode){
            circledImageView.setCircleColor(getContext().getResources().getColor(R.color.black));
        }else{
            circledImageView.setCircleColor(getContext().getResources().getColor(categoryType.getColorName()));
        }

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    private List<Categories> mCategories = new ArrayList<>();
    private Context mContext;

    public CategoriesAdapter(Context context) {
        mContext = context;
        for (int i=0; i<=11; i++) mCategories.add(new Categories(i));
    }
    private Context getContext() {
        return mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CircledImageView circledImageView;
        public TextView categoriesText;

        public ViewHolder(View itemView) {
            super(itemView);

            circledImageView = (CircledImageView) itemView.findViewById(R.id.fragment_categories_image);
            categoriesText = (TextView) itemView.findViewById(R.id.fragment_categories_text);
        }
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        updateDisplay();
        ambientMode = true;
    }

    @Override
    public void onUpdateAmbient() {
        updateDisplay();

    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        ambientMode = false;

    }

    private void updateDisplay() {
        notifyDataSetChanged();
    }
}