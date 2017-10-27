package ru.alexandertesbsnko.shoplist3.ui.shoping_list.model;

public class ShoppingItem {
    public static final int DELETED = 0;
    public static final int IN_LIST = 1;
    public static final int IN_BASKET = 2;
    public static final int BOUGHT = 3;
    private int state = IN_LIST;
    private double quantity = 1;
    private final long id;
    private final Shop shop;
    private final Merchandise merchandise;
    private final double price;


    public ShoppingItem(long id, Shop shop, Merchandise merchandise, double price){
        this.id = id;
        this.shop = shop;
        this.merchandise = merchandise;
        this.price = price;
    }

    public void increaseQuantity(){
        quantity++;
    }

    public boolean decreaseQuantity(){
        if(quantity == 1){
            return false;
        }
        quantity--;
        return true;
    }

    public double getCost(){
        return price * quantity;
    }


    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setState(int newState){
        this.state = newState;
    }

    public int getState() {
        return state;
    }

    public long getId(){
        return this.id;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public double getQuantity() {
        return quantity;
    }

    public Shop getShop(){
        return this.shop;
    }

    public double getPrice() {
        return price;
    }

}
