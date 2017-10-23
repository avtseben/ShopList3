package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;

public class ChildProductViewHolder extends ChildViewHolder {

    TextView mProductInstanceView;
    TextView shoppingItemQuantity;

    public ChildProductViewHolder(View itemView){
        super(itemView);
        mProductInstanceView = (TextView) itemView.findViewById(R.id.tv_product_name);
        shoppingItemQuantity = (TextView) itemView.findViewById(R.id.tv_product_quantity);
    }
    public void bind(ShoppingItem shoppingItem){
        mProductInstanceView.setText(shoppingItem.getMerchandise().getName());
        if(shoppingItem.getQuantity() > 1) {
            Double doubleVaule = shoppingItem.getQuantity();
            String stringedValue;
            if(doubleVaule.intValue() == doubleVaule){
                stringedValue = String.valueOf(doubleVaule.intValue());
            } else {
                stringedValue = String.valueOf(doubleVaule);
            }
            shoppingItemQuantity.setText("x" + stringedValue);
        }
    }
}
