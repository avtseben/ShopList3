package ru.alexandertesbsnko.shoplist3.ui.top_list.presenter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItemDataModel;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;

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

        {   //Add Fake datas
            data.add(new TopListItemDataModel(1L,1493701200000L,"Будни"));
            data.add(new TopListItemDataModel(2L,1484629200000L,"День рождения Томы"));
            data.add(new TopListItemDataModel(3L,1502946000000L,"В поход"));
            data.add(new TopListItemDataModel(4L,System.currentTimeMillis(),"Список"));
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
            System.out.println(">>You has select shoppingList\n" +
                    "id:" + shopListObj.getId() + "\n" +
                    "name:" + shopListObj.getName());
        }
    }
}
