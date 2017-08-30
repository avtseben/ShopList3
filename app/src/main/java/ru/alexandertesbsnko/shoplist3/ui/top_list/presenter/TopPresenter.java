package ru.alexandertesbsnko.shoplist3.ui.top_list.presenter;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItemDataModel;
import ru.alexandertesbsnko.shoplist3.ui.top_list.view.ITopView;

/**
 * Created by avtseben on 30.08.17.
 */

public class TopPresenter implements ITopPresenter{
    @Override
    public List<TopListItemDataModel> loadTopList() {
        return null;
    }

    @Override
    public void selectExitingShopingList(int position) {

    }

    @Override
    public void deleteShopingList(int position) {

    }

    @Override
    public void bindView(ITopView iProfileView) {

    }

    @Override
    public void unbindView() {

    }

    @Override
    public void onNewListClicked() {

    }

    @Override
    public void onItemClicked(TopListItemDataModel shopListObj) {

    }
}
