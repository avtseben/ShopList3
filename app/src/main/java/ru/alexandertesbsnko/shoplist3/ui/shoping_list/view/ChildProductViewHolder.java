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
    IShoppingListView superView;

    public ChildProductViewHolder(View itemView,IShoppingListView superView){
        super(itemView);
        this.superView = superView;
        decreaseFrame = (FrameLayout) itemView.findViewById(R.id.decrease_quantity_frame);
        increaseFrame = (FrameLayout) itemView.findViewById(R.id.increase_quantity_frame);
        mProductInstanceView = (TextView) itemView.findViewById(R.id.tv_product_name);
        shoppingItemQuantity = (TextView) itemView.findViewById(R.id.tv_product_quantity);
    }



    public void bind(final ShoppingItem shoppingItem){
        decreaseFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                superView.decrementQuantity(shoppingItem.getId());
                refreshQuantityView(shoppingItem);
            }
        });

        increaseFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                superView.incrementQuantity(shoppingItem.getId());
                refreshQuantityView(shoppingItem);
            }
        });
        mProductInstanceView.setText(shoppingItem.getMerchandise().getName());
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
