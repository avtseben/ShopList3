package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Subscriber;

public class AsyncRestShoppingListRepository implements IShoppingListRepository {

    private IDtoAdapter adapter = new DtoAdapter();

    @Override
    public ShoppingList loadShoppingListById(long id) {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtFindShoppingListsRequest request = new AtFindShoppingListsRequest();
        request.setId(id);
        final ShoppingList output;
        service.atFindShoppingListsAsync(request)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AtFindShoppingListsResponse>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(AtFindShoppingListsResponse response) {
                        ShoppingList output = adapter.adapt(response.getShoppingLists().get(0));
                    }
                });

        return null;
    }
}
