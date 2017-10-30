package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtCreateShoppingListResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtDeleteShoppingListResponse;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;

public interface IShoppingListRepository {
    Observable<ShoppingList> loadShoppingListById(long id);
    Observable<ShoppingItem> insertItemToShoppingList(long shoppingListId, long productId);
    Observable<AckResponse> updateShoppingItems(List<AtShoppingItemDTO> shoppingItem);
    Observable<AckResponse> updateShoppingItem(AtShoppingItemDTO shoppingItem);
    Observable<AckResponse> deleteShoppingList(long id);
    Observable<ShoppingList> createShoppingList(String name);
    Observable<List<ShoppingList>> loadAllShoppingLists();
}
