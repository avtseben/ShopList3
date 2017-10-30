package ru.alexandertesbsnko.shoplist3.repository.shopping_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingListDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtCreateShoppingListRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtDeleteShoppingListRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtInsertItemToShoppingListRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtUpdateShoppingItemsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtCreateShoppingListResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtInsertItemToShoppingListResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;

import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;
import rx.functions.Func1;

public class AsyncRestShoppingListRepository implements IShoppingListRepository {

    @Override
    public Observable<ShoppingList> loadShoppingListById(long id) {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtFindShoppingListsRequest request = new AtFindShoppingListsRequest();
        request.setId(id);
        Observable<AtFindShoppingListsResponse> observable = service.atFindShoppingListsAsync(request);
        return observable.map(new ListDtoAdapter());
    }

    @Override
    public Observable<List<ShoppingList>> loadAllShoppingLists() {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        Observable<AtFindShoppingListsResponse> observable = service.atFindShoppingListsAsync(new AtFindShoppingListsRequest());
        return observable.map(new AllListsAdapter());
    }

    @Override
    public  Observable<ShoppingItem> insertItemToShoppingList(long shoppingListId, long productId){
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtInsertItemToShoppingListRequest request = new AtInsertItemToShoppingListRequest();
        request.setShoppingListId(shoppingListId);
        request.setProductId(productId);
        Observable<AtInsertItemToShoppingListResponse> observable = service.atInsertItemToShoppingList(request);
        return observable.map(new ItemDtoAdapter());
    }

    @Override
    public Observable<AckResponse> updateShoppingItems(List<AtShoppingItemDTO> shoppingItems) {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtUpdateShoppingItemsRequest request = new AtUpdateShoppingItemsRequest();
        request.setShoppingItemList(shoppingItems);
        return service.atUpdateShoppingItems(request);
    }

    @Override
    public Observable<AckResponse> updateShoppingItem(AtShoppingItemDTO shoppingItem) {
        return updateShoppingItems(Arrays.asList(shoppingItem));
    }

    @Override
    public Observable<AckResponse> deleteShoppingList(long id) {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtDeleteShoppingListRequest request = new AtDeleteShoppingListRequest();
        request.setId(id);
        return service.atDeleteShoppingList(request);
    }

    @Override
    public Observable<ShoppingList> createShoppingList(String name) {
        ShoppingListsService service = new ServiceBuilder().buildShoppingListService();
        AtCreateShoppingListRequest request = new AtCreateShoppingListRequest();
        request.setName(name);
        return service.atCreateShoppingList(request).map(new ShoppingListDtoAdapter());
    }

    class ListDtoAdapter implements Func1<AtFindShoppingListsResponse,ShoppingList> {

        @Override
        public ShoppingList call(AtFindShoppingListsResponse response) {
            AtShoppingListDTO atShoppingListDTO = response.getShoppingLists().get(0);
            List<ShoppingItem > shoppingItems = new ArrayList<>();
            for (AtShoppingItemDTO atShoppingItemDTO : atShoppingListDTO.getShoppingItems()) {
                shoppingItems.add(new DtoAdapter().adapt(atShoppingItemDTO));
            }

            return new ShoppingList(
                    atShoppingListDTO.getId()
                    , atShoppingListDTO.getName()
                    , shoppingItems);
        }
    }


    class AllListsAdapter implements Func1<AtFindShoppingListsResponse,List<ShoppingList>> {

        @Override
        public List<ShoppingList> call(AtFindShoppingListsResponse response) {
            List<AtShoppingListDTO> atShoppingListDTOList = response.getShoppingLists();
            List<ShoppingList> shoppingLists = new ArrayList<>();
            for (AtShoppingListDTO atShoppingListDTO : atShoppingListDTOList) {
                List<ShoppingItem> shoppingItems = new ArrayList<>();
                for (AtShoppingItemDTO atShoppingItemDTO : atShoppingListDTO.getShoppingItems()) {
                    shoppingItems.add(new DtoAdapter().adapt(atShoppingItemDTO));
                }
                shoppingLists.add(new ShoppingList(
                        atShoppingListDTO.getId()
                        , atShoppingListDTO.getName()
                        , shoppingItems));

            }
            return shoppingLists;
        }
    }

    class ShoppingListDtoAdapter implements Func1<AtCreateShoppingListResponse,ShoppingList> {

        @Override
        public ShoppingList call(AtCreateShoppingListResponse response) {
            AtShoppingListDTO atShoppingListDTO = response.getNewShoppingList();
            List<ShoppingItem > shoppingItems = new ArrayList<>(atShoppingListDTO.getShoppingItems().size());
            for (AtShoppingItemDTO atShoppingItemDTO : atShoppingListDTO.getShoppingItems()) {
                shoppingItems.add(new DtoAdapter().adapt(atShoppingItemDTO));
            }

            return new ShoppingList(
                    atShoppingListDTO.getId()
                    , atShoppingListDTO.getName()
                    , shoppingItems);
        }
    }

    class ItemDtoAdapter implements Func1<AtInsertItemToShoppingListResponse,ShoppingItem> {

        @Override
        public ShoppingItem call(AtInsertItemToShoppingListResponse response) {
            return new DtoAdapter().adapt(response.getNewShoppingItem());
        }
    }

}
