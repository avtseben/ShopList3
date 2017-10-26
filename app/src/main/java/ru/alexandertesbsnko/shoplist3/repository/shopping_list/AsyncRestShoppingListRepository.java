package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import java.util.ArrayList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingListDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Category;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Merchandise;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Shop;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;
import rx.functions.Func1;

public class AsyncRestShoppingListRepository  {

    public Observable<ShoppingList> loadShoppingListById(long id) {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtFindShoppingListsRequest request = new AtFindShoppingListsRequest();
        request.setId(id);
        Observable<AtFindShoppingListsResponse> observable = service.atFindShoppingListsAsync(request);
        return observable.map(new AsyncDtoAdapter());
    }

    class AsyncDtoAdapter implements Func1<AtFindShoppingListsResponse,ShoppingList> {

        @Override
        public ShoppingList call(AtFindShoppingListsResponse response) {
            AtShoppingListDTO atShoppingListDTO = response.getShoppingLists().get(0);
            List<ShoppingItem > shoppingItems = new ArrayList<>();
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
}
