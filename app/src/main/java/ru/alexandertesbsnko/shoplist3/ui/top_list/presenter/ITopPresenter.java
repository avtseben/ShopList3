package ru.alexandertesbsnko.shoplist3.ui.top_list.presenter;


import java.util.ArrayList;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.di.router.RouterProvider;
import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItem;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;
import ru.alexandertesbsnko.shoplist3.util.DateBuilder;


public interface ITopPresenter {


    void loadTopList();
    void selectExitingShopingList(int position);
    void deleteShopingList(int position);
    void createShoppingList(String listName);
    void bindView(ITopView iProfileView);
    void unbindView();

//    class Fake implements ITopPresenter{
//
//        private List<TopListItem> data = new ArrayList<>();
//        private ITopView topView;
//        private IRouter router = RouterProvider.INSTANCE.getRouter();
//
//
//        {   //Add Fake datas
//            data.add(new TopListItem(1L,DateBuilder.timeTitleBuilder(1493701200000L),"Будни", R.drawable.bag_1));
//            data.add(new TopListItem(2L,DateBuilder.timeTitleBuilder(1484629200000L),"День рождения Томы",R.drawable.bag_2));
//            data.add(new TopListItem(3L,DateBuilder.timeTitleBuilder(1502946000000L),"В поход",R.drawable.bag_3));
//            data.add(new TopListItem(4L,DateBuilder.timeTitleBuilder(System.currentTimeMillis()),"Список",R.drawable.bag_1));
//            System.out.println(">>Fake Data created");
//        }
//
//
//        @Override
//        public  loadTopList() {
//            return this.data;
//        }
//
//        @Override
//        public void selectExitingShopingList(int position) {
//            System.out.println(">>You has select shoppingList\n" +
//                    "id:" + data.get(position).getId() + "\n" +
//                    "name:" + data.get(position).getName());
//        }
//
//        @Override
//        public void deleteShopingList(int position) {
//            System.out.println(">>You press delete List" +
//                    "id:" + data.get(position).getId() + "\n" +
//                    "name:" + data.get(position).getName());
//            data.remove(position);
//            topView.removeItemFromList(position);
//        }
//
//        @Override
//        public void bindView(ITopView topView) {
//            System.out.println(">>Bind View");
//            this.topView = topView;
//        }
//
//        @Override
//        public void unbindView() {
//            System.out.println(">>UnBind View");
//            topView = null;
//        }
//
//        @Override
//        public void createShoppingList(String listName) {
//
//        }
//    }
}
