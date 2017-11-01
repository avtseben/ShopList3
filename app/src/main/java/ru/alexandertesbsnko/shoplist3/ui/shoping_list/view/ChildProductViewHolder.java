package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;

public class ChildProductViewHolder extends ChildViewHolder implements View.OnLongClickListener {

    FrameLayout decreaseFrame;
    FrameLayout increaseFrame;
    GridLayout shoppingItemInfoLayout;
    TextView mProductInstanceView;
    TextView shoppingItemQuantity;
    TextView priceValue;
    IShoppingListView superView;

    public ChildProductViewHolder(View itemView,IShoppingListView superView){
        super(itemView);
        itemView.setOnLongClickListener(this);
        this.superView = superView;
        decreaseFrame = (FrameLayout) itemView.findViewById(R.id.decrease_quantity_frame);
        increaseFrame = (FrameLayout) itemView.findViewById(R.id.increase_quantity_frame);
        mProductInstanceView = (TextView) itemView.findViewById(R.id.tv_product_name);
        mProductInstanceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colapseChild();
            }
        });
        shoppingItemQuantity = (TextView) itemView.findViewById(R.id.tv_product_quantity);
        shoppingItemInfoLayout = (GridLayout) itemView.findViewById(R.id.shopping_list_info_layout);
        shoppingItemInfoLayout.setVisibility(View.GONE);//TODO remove
        priceValue = (TextView) itemView.findViewById(R.id.product_price_value);
        priceValue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editPriceCalled();
                return true;
            }
        });

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
        priceValue.setText(String.valueOf(shoppingItem.getPrice()));
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

    @Override
    public boolean onLongClick(View v) {
        expandChild();
        return true;
    }

    private void expandChild(){
        shoppingItemInfoLayout.setVisibility(View.VISIBLE);
        System.out.println(">>Expand");
    }
    private void colapseChild(){
        shoppingItemInfoLayout.setVisibility(View.GONE);
        System.out.println(">>Collapse");

    }

    private void editPriceCalled(){
        System.out.println(">>EditPrice");
    }
}
