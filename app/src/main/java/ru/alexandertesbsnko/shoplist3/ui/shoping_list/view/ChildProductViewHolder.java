package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;

public class ChildProductViewHolder extends ChildViewHolder {

    FrameLayout decreaseFrame;
    FrameLayout increaseFrame;
    TextView mProductInstanceView;
    TextView shoppingItemQuantity;

    public ChildProductViewHolder(View itemView){
        super(itemView);
        decreaseFrame = (FrameLayout) itemView.findViewById(R.id.decrease_quantity_frame);
        increaseFrame = (FrameLayout) itemView.findViewById(R.id.increase_quantity_frame);
        mProductInstanceView = (TextView) itemView.findViewById(R.id.tv_product_name);
        shoppingItemQuantity = (TextView) itemView.findViewById(R.id.tv_product_quantity);
    }
    public void bind(final ShoppingItem shoppingItem){
        decreaseFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingItem.decreaseQuantity();
                refreshQuantityView(shoppingItem);
            }
        });

        increaseFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingItem.increaseQuantity();
                refreshQuantityView(shoppingItem);
            }
        });

        mProductInstanceView.setText(shoppingItem.getMerchandise().getName());
        refreshQuantityView(shoppingItem);

    }

    private void refreshQuantityView(ShoppingItem shoppingItem){
        if(shoppingItem.getQuantity() > 1) {
            Double doubleVaule = shoppingItem.getQuantity();
            String stringedValue;
            if(doubleVaule.intValue() == doubleVaule){
                stringedValue = String.valueOf(doubleVaule.intValue());
            } else {
                stringedValue = String.valueOf(doubleVaule);
            }
            shoppingItemQuantity.setText("x" + stringedValue);
        } else {
            shoppingItemQuantity.setText("");
        }
    }




}
