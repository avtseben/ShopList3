package ru.alexandertesbsnko.shoplist3.ui.shoping_list.model;

import java.util.Date;
import java.util.List;

public class ShoppingList {
    private final long id;
    private final String name;
    private final Date date;
    private final List<ShoppingItem> shoppingItems;

    public ShoppingList(long id, String name, Date date, List<ShoppingItem> shoppingItems){
        this.id = id;
        this.name = name;
        this.date = date;
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
                cost += shoppingItem.getCost();
            }
        }
        return cost;
    }

    public double getTotalBoughtCost(){
        double boughtCost = 0;
        for (ShoppingItem shoppingItem : shoppingItems) {
            if(shoppingItem.getState() == ShoppingItem.BOUGHT & shoppingItem.getState() != ShoppingItem.DELETED) {
                boughtCost += shoppingItem.getCost();
            }
        }
        return boughtCost;
    }

    public void addShoppingItem(ShoppingItem item){
        shoppingItems.add(item);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
