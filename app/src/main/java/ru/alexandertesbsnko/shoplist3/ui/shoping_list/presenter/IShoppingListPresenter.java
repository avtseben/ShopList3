package ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter;


import ru.alexandertesbsnko.shoplist3.di.router.RouterProvider;
import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Category;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Merchandise;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Shop;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.view.IShoppingListView;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItemDataModel;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;



public interface IShoppingListPresenter extends
        IShoppingListView.OnSendButtonClickListener {

    ShoppingList loadShoppingListById(long id);
    ShoppingList loadNewShoppingList();
    void selectExitingShopingList(int position);
    void deleteShopingList(int position);
    void bindView(IShoppingListView view);
    void unbindView();
    void updateShoppingItem(long id, int bought);
    List<Merchandise> findMerchandiseLike(String pattern);


    class Fake implements IShoppingListPresenter {

        private Map<Long,ShoppingList> shoppingListFakeStorage = new HashMap<>(1);
        private Map<Long,ShoppingItem> shoppingItemsFakeStorage = new HashMap<>(1);
        private Map<Long,Merchandise> merchandiseFakeStorage = new HashMap<>(1);
        private IShoppingListView view;
        private IRouter router = RouterProvider.INSTANCE.getRouter();


        {   //Add Fake datas
            List<ShoppingItem> shi1 = new ArrayList<>();
            Merchandise milk =new Merchandise(
                    1l
                    ,new Category(1,"Кисломочные продукты","milk")
                    ,"Молоко");
            Shop lenta = new Shop(1l,"Лента");
            ShoppingItem s1 = new ShoppingItem(1,lenta,milk, 49);
            shoppingItemsFakeStorage.put(s1.getId(),s1);
            shi1.add(s1);
            ShoppingList sl1 = new ShoppingList(1l,shi1);
            shoppingListFakeStorage.put(sl1.getId(),sl1);

            System.out.println(">>Fake Data created");
        }


        @Override
        public List<Merchandise> findMerchandiseLike(String pattern){
            List<Merchandise> findedList = new ArrayList<>();
            for (Merchandise merchandise : merchandiseFakeStorage.values()) {
                if(merchandise.getName().contains(pattern)){
                    findedList.add(merchandise);
                }
            }
            return findedList;
        }

        @Override
        public ShoppingList loadNewShoppingList(){
            List<ShoppingItem> shi = new ArrayList<>();
            ShoppingList sl = new ShoppingList(4l,shi);
            shoppingListFakeStorage.put(sl.getId(),sl);
            return sl;
        }

        @Override
        public ShoppingList loadShoppingListById(long id) {
            return this.shoppingListFakeStorage.get(id);
        }

        @Override
        public void selectExitingShopingList(int position) {
//            System.out.println(">>You has select shoppingList\n" +
//                    "id:" + data.get(position).getId() + "\n" +
//                    "name:" + data.get(position).getName());
        }

        @Override
        public void deleteShopingList(int position) {
//            System.out.println(">>You press delete List" +
//                    "id:" + data.get(position).getId() + "\n" +
//                    "name:" + data.get(position).getName());
//            data.remove(position);
//            topView.removeItemFromList(position);
        }

        @Override
        public void bindView(IShoppingListView view) {
            System.out.println(">>Bind View");
//            this.topView = topView;
        }

        @Override
        public void unbindView() {
            System.out.println(">>UnBind View");
//            topView = null;
        }

        @Override
        public void updateShoppingItem(long id, int bought) {
            //

        }

        /**В фрагменте списка нажали кнопку отправить список
         * переходим к фрагменту формирования списка людей кому отправлять
         * список
         */
        @Override
        public void onSendButtonClicked(ShoppingList shopListPojo) {
            System.out.println("Send List Button Clicked");
        }

        }


}
