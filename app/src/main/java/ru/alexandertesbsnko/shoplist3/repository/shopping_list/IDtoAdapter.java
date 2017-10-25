package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingListDTO;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;

public interface IDtoAdapter {
    ShoppingList adapt(AtShoppingListDTO atShoppingListDTO);
}
