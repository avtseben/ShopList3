package ru.alexandertesbsnko.shoplist3.repository.products;

import java.util.ArrayList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.data_source.net.common.ServiceBuilder;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtProductDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingItemDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.dto.AtShoppingListDTO;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.products.AtFindProductRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.request.shopping_lists.AtFindShoppingListsRequest;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.prices.AtFindPricesResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.products.AtFindProductResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.model.response.shopping_lists.AtFindShoppingListsResponse;
import ru.alexandertesbsnko.shoplist3.data_source.net.products.ProductsService;
import ru.alexandertesbsnko.shoplist3.data_source.net.shopping_list.ShoppingListsService;
import ru.alexandertesbsnko.shoplist3.repository.shopping_list.AsyncRestShoppingListRepository;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Category;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Merchandise;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Shop;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import rx.Observable;
import rx.functions.Func1;


public class ProductsRepository implements IProductsRepository {

    @Override
    public Observable<List<Product>> searchProductsByNameLike(String pattern) {
        ProductsService service = new ServiceBuilder().buildProductsService();
        AtFindProductRequest request = new AtFindProductRequest();
        request.setNameLike(pattern);
        Observable<AtFindProductResponse> observable = service.atFindProductAsync(request);
        return observable.map(new DtoAdapter());
    }

    class DtoAdapter implements Func1<AtFindProductResponse,List<Product>> {

        @Override
        public List<Product> call(AtFindProductResponse response) {
            List<AtProductDTO> inputItems = response.getProductList();
            if(inputItems == null || inputItems.size() == 0){
                return new ArrayList<>(0);
            }
            List<Product> outItems = new ArrayList<>();

            for (AtProductDTO inputItem : inputItems) {
                outItems.add(new Product(
                        inputItem.getId()
                       ,inputItem.getName()
                       ,inputItem.getProductCategoryName())
                );
            }
            return outItems;
        }
    }
}
