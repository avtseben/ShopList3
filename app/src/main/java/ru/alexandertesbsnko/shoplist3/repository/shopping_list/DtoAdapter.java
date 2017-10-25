package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import java.util.ArrayList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingListDTO;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Category;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Merchandise;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Shop;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;

public class DtoAdapter implements IDtoAdapter{

    @Override
    public ShoppingList adapt(AtShoppingListDTO atShoppingListDTO){
        List<ShoppingItem> shoppingItems = new ArrayList<>();
        for (AtShoppingItemDTO atShoppingItemDTO : atShoppingListDTO.getShoppingItems()) {
            Merchandise merchandise = new Merchandise(
                    atShoppingItemDTO.getMerchandise().getId()
                    , new Category(
                    atShoppingItemDTO.getMerchandise().getCategory().getId()
                    , atShoppingItemDTO.getMerchandise().getCategory().getName()
                    , "milk")
                    , atShoppingItemDTO.getMerchandise().getProduct().getName());
            Shop shop = new Shop(
                    atShoppingItemDTO.getShop().getId()
                    , atShoppingItemDTO.getShop().getName());
            ShoppingItem shoppingItem = new ShoppingItem(
                    atShoppingItemDTO.getId()
                    , shop
                    , merchandise
                    , atShoppingItemDTO.getPrice());
            shoppingItems.add(shoppingItem);
        }

        return new ShoppingList(
                atShoppingListDTO.getId()
                , atShoppingListDTO.getName()
                , shoppingItems);
    }
}
