package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;


import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;

public class ParentItem implements ParentListItem {

    List<ShoppingItem> mShoppingItemList;
    String mName;
    String mDrawableIdName;

    public ParentItem(String name, String drawableIdName, ShoppingItem shoppingItem) {
        mName = name;
        mDrawableIdName = drawableIdName;
        mShoppingItemList = new ArrayList<>();
        addChild(shoppingItem);
    }


    public void addChild(ShoppingItem shoppingItem) {
        mShoppingItemList.add(shoppingItem);
    }
    public void removeChildFromParent(ShoppingItem shoppingItem){
       mShoppingItemList.remove(shoppingItem);
    }

    public String getName() {
        return mName;
    }
    public String getImageName(){
        return mDrawableIdName;
    }

    @Override
    public List<ShoppingItem> getChildItemList() {
        return mShoppingItemList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }
}
