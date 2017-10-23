package ru.alexandertesbsnko.shoplist3.ui.shoping_list.model;

import android.view.View;

import java.util.List;

/**
 * Created by avtseben on 22.10.17.
 */

public class ShoppingList {
    private final long id;
    private final List<ShoppingItem> shoppingItems;

    public ShoppingList(long id, List<ShoppingItem> shoppingItems){
        this.id = id;
        this.shoppingItems = shoppingItems;
    }

    public long getId(){
        return this.id;
    }

    public List<ShoppingItem> getShoppingItems(){
        return this.shoppingItems;
    }

    public double getTotalCost(){
        double cost = 0;
        for (ShoppingItem shoppingItem : shoppingItems) {
            if(shoppingItem.getState() != ShoppingItem.DELETED) {
                cost += shoppingItem.getPrice() * shoppingItem.getQuantity();
            }
        }
        return cost;
    }

    public void addShoppingItem(ShoppingItem item){
        shoppingItems.add(item);
    }




}
