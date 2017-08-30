package ru.alexandertesbsnko.shoplist3.ui.top_list.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.SLApplication;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListModule;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItemDataModel;
import ru.alexandertesbsnko.shoplist3.ui.top_list.presenter.ITopPresenter;


public class TopListFragment extends Fragment implements ITopView {

    public OnShopListItemClickListener listenerShopListSelected;
    public OnNewListButtonClickListener listenerNewList;

    @Inject
    ITopPresenter iTopPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//
        SLApplication.get(getContext()).applicationComponent().plus(new TopListModule()).inject(this);
        }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_top_list,container, false);
        final List<TopListItemDataModel> topList = iTopPresenter.loadTopList();

        if(listenerShopListSelected == null) {
            this.listenerShopListSelected = (OnShopListItemClickListener) iTopPresenter;
        }
        if(listenerNewList == null) {
            this.listenerNewList = (OnNewListButtonClickListener) iTopPresenter;
        }

        ImageView btn = (ImageView) view.findViewById(R.id.btn_newList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerNewList.onNewListClicked();
            }
        });
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv_top);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        TopListAdapter adapter = new TopListAdapter(topList);
        adapter.setOnItemClickListener(new TopListAdapter.OnClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                listenerShopListSelected.onItemClicked(topList.get(position));
            }
        });
        recyclerView.setAdapter(adapter);
        iTopPresenter.bindView(this);
        setUpItemTouchHelper(recyclerView);
        return view;
    }

    @Override
    public void onDestroyView() {
        iTopPresenter.unbindView();
        super.onDestroyView();
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if(listenerShopListSelected == null) {
//            this.listenerShopListSelected = (OnShopListItemClickListener) context;
//        }
//        if(listenerNewList == null) {
//            this.listenerNewList = (OnNewListButtonClickListener) context;
//        }
//    }

    public void setUpItemTouchHelper(RecyclerView recyclerView){

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0/*drag drop не нужен*/, ItemTouchHelper.UP
                /*задаём направление свайпа которое слушаем*/) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                //Нам не нужен Drag&Prop
                return false;
            }
            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            }
            @Override
            public void onSwiped (RecyclerView.ViewHolder viewHolder, int direction) {
                iTopPresenter.deleteShopingList(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
