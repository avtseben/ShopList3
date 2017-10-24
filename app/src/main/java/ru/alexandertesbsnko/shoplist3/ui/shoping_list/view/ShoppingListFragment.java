package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.SLApplication;
import ru.alexandertesbsnko.shoplist3.di.shoping_list.ShopingListModule;
import ru.alexandertesbsnko.shoplist3.ui.AbstractFragment;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingList;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.IShoppingListPresenter;


public class ShoppingListFragment extends AbstractFragment implements IShoppingListView {

    public static final String SHOP_LIST_ID = "SHOP_LIST_ID";
    private OnSendButtonClickListener listener;
    private ShoppingListAdapter adapter;
    private RecyclerView mRecyclerView;
    List<ParentItem> mParentItemList;
    ShoppingList shoppingList;
    TextView totalCostTextView;
    TextView totalBoughtCostTextView;
    ImageView totalBoughtCostIcon;

    @Inject
    IShoppingListPresenter presenter;

    public static ShoppingListFragment newInstance(Bundle args) {
        ShoppingListFragment slf = new ShoppingListFragment();
        slf.setArguments(args);
        return slf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        SLApplication.get(getContext()).applicationComponent().plus(new ShopingListModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_shopping_list, container, false);
        listener = router;
        mParentItemList = new ArrayList<>();
        totalCostTextView = (TextView) view.findViewById(R.id.total_sl_cost);
        totalBoughtCostTextView = (TextView) view.findViewById(R.id.total_bought_cost);
        totalBoughtCostIcon = (ImageView)view.findViewById(R.id.total_bought_icon);

        long shoppingLisId = 0;
        shoppingLisId = getArguments().getLong(SHOP_LIST_ID);
        if (shoppingLisId != 0) {
            shoppingList = presenter.loadShoppingListById(shoppingLisId);
            for (ShoppingItem item : shoppingList.getShoppingItems()) {
                smartAdd(item);
            }
        } else {
            shoppingList = presenter.loadNewShoppingList();
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_product_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShoppingListAdapter(getContext(), mParentItemList,this);
        mRecyclerView.setAdapter(adapter);

        presenter.bindView(this);
        setUpItemTouchHelper();
        return view;
    }


    public void setUpItemTouchHelper() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0/*drag drop не нужен*/, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
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
                if (!viewHolder.getClass().equals(ChildProductViewHolder.class)) {
                    return 0;//Если это не ChildItem тоесть не сам продукт
                    //то никаких свайпов. Свайп разрешен только для продуктов
                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (viewHolder.getClass().equals(ChildProductViewHolder.class)) {
                    int position = viewHolder.getAdapterPosition();
                    ShoppingItem si = ((ShoppingItem) adapter.getListItem(position));
                    if (direction == ItemTouchHelper.RIGHT) {
                        adapter.deleteProductInstance(position);
                        presenter.buyShoppingItem(si.getId());
                    } else if (direction == ItemTouchHelper.LEFT) {
                        adapter.deleteProductInstance(position);
                        presenter.deleteShoppingItem(si.getId());
                    }
                    refreshCost();
                }
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroyView() {
        presenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        //Setup search item
        MenuItem searchItem = menu.findItem(R.id.search_item);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) MenuItemCompat.getActionView(searchItem);
        autoCompleteTextView.setAdapter(new SearchAutoCompleteAdapter(presenter, getContext()));
        autoCompleteTextView.setWidth(800);//TODO without this it would wrap input chars
        autoCompleteTextView.setHint("название продукта");
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShoppingItem shoppingItem = (ShoppingItem) parent.getItemAtPosition(position);
                autoCompleteTextView.setText("");
                presenter.addShoppingItem(shoppingItem,shoppingList.getId());
                addProduct(shoppingItem);
            }
        });
        //Setup send item
        MenuItem sendItem = menu.findItem(R.id.send_button_in_menu);
        sendItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (listener != null) {
                    listener.onSendButtonClicked(shoppingList);
                }
                return true;
            }
        });
    }

    public void addProduct(ShoppingItem product) {
        smartAdd(product);
        adapter = new ShoppingListAdapter(getContext(), mParentItemList,this);
        mRecyclerView.setAdapter(adapter);
    }

    private void    smartAdd(ShoppingItem shoppingItem) {
        String category = shoppingItem.getMerchandise().getCategory().getName();
        String imageName = shoppingItem.getMerchandise().getCategory().getImage();
        boolean productAdded = false;
        for (ParentItem pi : mParentItemList) {
            //Если продукты такой категории уже есть в списке
            if (pi.getName().equals(category)) {
                pi.addChild(shoppingItem);
                productAdded = true;
                break;
            }
        }
        //Если в предыдущем цыкле не нашлось в списке катаегории куда "положить"
        //продукт то создаём эту категорию и кладём в неё продукт
        if (!productAdded) {
            mParentItemList.add(new ParentItem(category, imageName, shoppingItem));
        }
        refreshCost();
    }


    @Override
    public void refreshCost() {
        if(shoppingList.getTotalCost() > 0) {
            totalCostTextView.setText(String.valueOf(shoppingList.getTotalCost()) + " p");
        } else {
            totalCostTextView.setText("");
        }
        if(shoppingList.getTotalBoughtCost() > 0) {
            totalBoughtCostTextView.setText(String.valueOf(shoppingList.getTotalBoughtCost()) + " p");
            totalBoughtCostIcon.setVisibility(View.VISIBLE);
        } else {
            totalBoughtCostTextView.setText("");
            totalBoughtCostIcon.setVisibility(View.GONE);
        }
    }
}
