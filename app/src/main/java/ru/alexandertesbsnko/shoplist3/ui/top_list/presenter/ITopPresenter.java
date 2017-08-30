package ru.alexandertesbsnko.shoplist3.ui.top_list.presenter;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.router.IRouter;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItemDataModel;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;
import ru.alexandertesbsnko.shoplist3.util.DateBuilder;


public interface ITopPresenter extends
        ITopView.OnNewListButtonClickListener
       ,ITopView.OnShopListItemClickListener {

    List<TopListItemDataModel> loadTopList();
    void selectExitingShopingList(int position);
    void deleteShopingList(int position);
    void bindView(ITopView iProfileView);
    void unbindView();

    class Fake implements ITopPresenter{

        private List<TopListItemDataModel> data = new ArrayList<>();
        private ITopView topView;
        @Inject IRouter router = new IRouter.Fake();//TODO bullshit refactor to;


        {   //Add Fake datas
            data.add(new TopListItemDataModel(1L,DateBuilder.timeTitleBuilder(1493701200000L),"Будни", R.drawable.bag_1));
            data.add(new TopListItemDataModel(2L,DateBuilder.timeTitleBuilder(1484629200000L),"День рождения Томы",R.drawable.bag_2));
            data.add(new TopListItemDataModel(3L,DateBuilder.timeTitleBuilder(1502946000000L),"В поход",R.drawable.bag_3));
            data.add(new TopListItemDataModel(4L,DateBuilder.timeTitleBuilder(System.currentTimeMillis()),"Список",R.drawable.bag_1));
//            SLApplication.get(getContext()).applicationComponent().plus(new RouterModule()).inject(this);
            System.out.println(">>Fake Data created");
        }


        @Override
        public List<TopListItemDataModel> loadTopList() {
            return this.data;
        }

        @Override
        public void selectExitingShopingList(int position) {
            System.out.println(">>You has select shoppingList\n" +
                    "id:" + data.get(position).getId() + "\n" +
                    "name:" + data.get(position).getName());
        }

        @Override
        public void deleteShopingList(int position) {
            System.out.println(">>You press delete List" +
                    "id:" + data.get(position).getId() + "\n" +
                    "name:" + data.get(position).getName());
            data.remove(position);
            topView.removeItemFromList(position);
        }

        @Override
        public void bindView(ITopView topView) {
            System.out.println(">>Bind View");
            this.topView = topView;
        }

        @Override
        public void unbindView() {
            System.out.println(">>UnBind View");
            topView = null;
        }

        @Override
        public void onNewListClicked() {
            System.out.println(">>You press create new List");
        }

        @Override
        public void onItemClicked(TopListItemDataModel shopListObj) {
            System.out.println(">>Router " + router);
            System.out.println(">>On Item clicked " + topView);
            System.out.println(">>You has select shoppingList\n" +
                    "id:" + shopListObj.getId() + "\n" +
                    "name:" + shopListObj.getName());
            router.navigate(IRouter.Screen.SHOPING_LIST);

        }
    }
}
