package ru.alexandertesbsnko.shoplist3.ui.top_list.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.SLApplication;
import ru.alexandertesbsnko.shoplist3.di.top_list.TopListModule;
import ru.alexandertesbsnko.shoplist3.ui.AbstractFragment;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItem;
import ru.alexandertesbsnko.shoplist3.ui.top_list.presenter.ITopPresenter;


public class TopListFragment extends AbstractFragment implements ITopView {

    private TopListAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    ITopPresenter presenter;

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
        View view = inflater.inflate(R.layout.fmt_top_list, container, false);
        presenter.loadTopList();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.top_list_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_top);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ImageView btn = (ImageView) view.findViewById(R.id.btn_newList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.createShoppingList("Список");
            }
        });
        presenter.bindView(this);
        setUpItemTouchHelper(recyclerView);
        return view;
    }


    @Override
    public void onDestroyView() {
        presenter.unbindView();
        super.onDestroyView();
    }

    public void setUpItemTouchHelper(RecyclerView recyclerView) {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0/*drag drop не нужен*/, ItemTouchHelper.UP
                /*задаём направление свайпа которое слушаем*/) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteShopingList(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void setUpTopList(final List<TopListItem> topList) {
        adapter = new TopListAdapter(topList);
        adapter.setOnItemClickListener(new TopListAdapter.OnClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
               presenter.selectExitingShopingList(position);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void removeItemFromList(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void shopErrorMessage(String message) {//TODO to base class
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }
}
