package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.util.ResourceManipulator;


public class ParentCategoryViewHolder extends ParentViewHolder {
    ImageView mCategoryImageView;
    public ParentCategoryViewHolder(View itemView){
        super(itemView);
        mCategoryImageView = (ImageView)itemView.findViewById(R.id.iv_category);
    }
    public void bind(ParentItem prodCat) {
        //Set Image of Category
        int resId;
        try {
            resId = ResourceManipulator.getResIdByStringName(itemView.getContext(),
                    prodCat.getImageName(), "drawable");
            mCategoryImageView.setImageResource(resId);
        } catch (NullPointerException e) {
            mCategoryImageView.setImageResource(R.drawable.vegetables);
            Log.e(getClass().getSimpleName(),"Cannot find drawable resource!");
        }
    }
}
