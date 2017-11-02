package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText priceValueEdit;
    Button savePrice;
    Button undoPriceEdit;
    IShoppingListView superView;
    ShoppingItem shoppingItem;
    private enum State {
        COLLAPSED,
        EXPANDED
    }
    State state = State.COLLAPSED;

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
                toggleChild();
            }
        });
        shoppingItemQuantity = (TextView) itemView.findViewById(R.id.tv_product_quantity);
        shoppingItemInfoLayout = (GridLayout) itemView.findViewById(R.id.shopping_list_info_layout);
        shoppingItemInfoLayout.setVisibility(View.GONE);//TODO remove
        priceValueEdit = (EditText) itemView.findViewById(R.id.product_price_value_edit);
        priceValue = (TextView) itemView.findViewById(R.id.product_price_value);
        priceValue.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editPriceCalled();
                return true;
            }
        });
        savePrice = (Button) itemView.findViewById(R.id.save_price);
        savePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrice();
            }
        });
        undoPriceEdit = (Button) itemView.findViewById(R.id.undo_edit_price);
        undoPriceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoEditPrice();
            }
        });

    }

    private void toggleChild() {
        if(state == State.COLLAPSED){
            expandChild();
        } else {
            colapseChild();
        }
    }


    public void bind(final ShoppingItem shoppingItem){
        this.shoppingItem = shoppingItem;
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
        state = State.EXPANDED;
        shoppingItemInfoLayout.setVisibility(View.VISIBLE);
    }
    private void colapseChild(){
        state = State.COLLAPSED;
        shoppingItemInfoLayout.setVisibility(View.GONE);

    }

    private void toEditPriceVisibility(){
        savePrice.setVisibility(View.VISIBLE);
        undoPriceEdit.setVisibility(View.VISIBLE);
        priceValue.setVisibility(View.GONE);
        priceValueEdit.setVisibility(View.VISIBLE);
    }

    private void toNormalVisibility(){
        savePrice.setVisibility(View.GONE);
        undoPriceEdit.setVisibility(View.GONE);
        priceValue.setVisibility(View.VISIBLE);
        priceValueEdit.setVisibility(View.GONE);
    }

    private void editPriceCalled(){
        System.out.println(">>EditPrice: " + priceValue.getText());
        priceValueEdit.setText(priceValue.getText());
        toEditPriceVisibility();

    }

    private void savePrice(){
        double newPrice = Double.parseDouble(priceValueEdit.getText().toString());
        priceValue.setText(String.valueOf(newPrice));
        superView.updatePrice(shoppingItem, newPrice);
        toNormalVisibility();
    }
    private void undoEditPrice(){
        priceValueEdit.setText(priceValue.getText());
        toNormalVisibility();
    }
}
