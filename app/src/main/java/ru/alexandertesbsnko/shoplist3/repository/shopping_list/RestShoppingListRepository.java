package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import java.io.IOException;

import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;

public class RestShoppingListRepository implements IShoppingListRepository {

    private IDtoAdapter adapter = new DtoAdapter();

    @Override
    public ShoppingList loadShoppingListById(long id) throws IOException {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtFindShoppingListsRequest request = new AtFindShoppingListsRequest();
        request.setId(id);
        return adapter.adapt(service
                        .atFindShoppingLists(request)
                        .execute()
                        .body()
                        .getShoppingLists()
                        .get(0));
    }
}
