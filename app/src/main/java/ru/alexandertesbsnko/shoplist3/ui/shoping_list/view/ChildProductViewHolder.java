package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;

public class ChildProductViewHolder extends ChildViewHolder {
    TextView mProductInstanceView;
    public ChildProductViewHolder(View itemView){
        super(itemView);
        mProductInstanceView = (TextView) itemView.findViewById(R.id.tv_product_name);
    }
    public void bind(ShoppingItem shoppingItem){
        mProductInstanceView.setText(shoppingItem.getMerchandise().getName());
    }
}
