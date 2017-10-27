package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Category;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Merchandise;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Shop;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;

public class DtoAdapter implements IDtoAdapter{

    @Override
    public ShoppingItem adapt(AtShoppingItemDTO atShoppingItemDTO){
        Merchandise merchandise = new Merchandise(
                atShoppingItemDTO.getMerchandise().getId()
                , new Category(
                atShoppingItemDTO.getMerchandise().getCategory().getId()
                , atShoppingItemDTO.getMerchandise().getCategory().getName()
                , "milk")//TODO hardcode. createfield in backend
                , atShoppingItemDTO.getMerchandise().getProduct().getName());
        Shop shop = new Shop(
                atShoppingItemDTO.getShop().getId()
                , atShoppingItemDTO.getShop().getName());
        ShoppingItem shoppingItem = new ShoppingItem(
                atShoppingItemDTO.getId()
                , shop
                , merchandise
                , atShoppingItemDTO.getPrice());
        return shoppingItem;
    }


}
