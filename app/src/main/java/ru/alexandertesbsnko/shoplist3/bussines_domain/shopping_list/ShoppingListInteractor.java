package ru.alexandertesbsnko.shoplist3.bussines_domain.shopping_list;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.common.AckResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.di.products.ProductsRepositoryProvider;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShoppingListsRepositoryProvider;
import ru.alexandertesbsnko.shoplist3.repository.products.IProductsRepository;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.IShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;

public class ShoppingListInteractor implements IShoppingListInteractor{

    IProductsRepository productsRepository = ProductsRepositoryProvider.INSTANCE.provide();
    IShoppingListRepository shoppingListRepository = ShoppingListsRepositoryProvider.INSTANCE.provide();

    @Override
    public Observable<ShoppingList> loadShoppingListById(long id) {
        return shoppingListRepository.loadShoppingListById(id);
    }

    @Override
    public Observable<List<Product>> searchProductsByName(String pattern) {
        pattern = pattern.trim().toLowerCase();
        String capitalizedPattern = pattern.substring(0,1).toUpperCase() + pattern.substring(1);
        final Observable<List<Product>> observableCapitalized = productsRepository.searchProductsByNameLike("%" + capitalizedPattern + "%");
        return observableCapitalized;
    }

    @Override
    public Observable<ShoppingItem> insertItemToShoppingList(long shoppingListId, long productId){
        return shoppingListRepository.insertItemToShoppingList(shoppingListId,productId);
    }

    @Override
    public Observable<AckResponse> buyShoppingItem(long id) {
        AtShoppingItemDTO updateDto = new AtShoppingItemDTO();
        updateDto.setId(id);
        updateDto.setState(ShoppingItem.BOUGHT);
        return shoppingListRepository.updateShoppingItem(updateDto);
    }

    @Override
    public Observable<AckResponse> deleteShoppingItem(long id) {
        AtShoppingItemDTO updateDto = new AtShoppingItemDTO();
        updateDto.setId(id);
        updateDto.setState(ShoppingItem.DELETED);
        return shoppingListRepository.updateShoppingItem(updateDto);
    }

    @Override
    public Observable<AckResponse> updateShoppingItemQuantity(ShoppingItem shoppingItem) {
        AtShoppingItemDTO updateDto = new AtShoppingItemDTO();
        updateDto.setId(shoppingItem.getId());
        updateDto.setQuantity(shoppingItem.getQuantity());
        return shoppingListRepository.updateShoppingItem(updateDto);
    }
}
