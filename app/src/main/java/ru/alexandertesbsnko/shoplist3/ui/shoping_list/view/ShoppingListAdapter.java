package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.Model.ParentWrapper;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;

public class ShoppingListAdapter extends ExpandableRecyclerAdapter<ParentCategoryViewHolder,ChildProductViewHolder> {

    private final LayoutInflater mInflater;

    public ShoppingListAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ParentCategoryViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_category_parent,viewGroup,false);
        return new ParentCategoryViewHolder(view);
    }

    @Override
    public ChildProductViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_product_child,viewGroup,false);
        return new ChildProductViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(ParentCategoryViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        ParentItem prodCat = (ParentItem) parentListItem;
        parentViewHolder.bind(prodCat);
    }

    @Override
    public void onBindChildViewHolder(ChildProductViewHolder childProductViewHolder, int position, Object childListItem) {
        ShoppingItem productInstance = (ShoppingItem) childListItem;
        childProductViewHolder.bind(productInstance);
    }

    @Override
    public Object getListItem(int position) {
        return super.getListItem(position);
    }

    public void deleteProductInstance(int position) {
        //Возможно это и некрасиво но работает.
        //метод возвращает масив из двух об
        Object[] parentItemWithPosition =
                removeChildItem(position);

        ParentItem pI = (ParentItem) parentItemWithPosition[0];
        if(pI.getChildItemList().size() == 0){
            int parentPosition = (Integer)parentItemWithPosition[1];
            getParentItemList().remove(parentPosition);
            notifyParentItemRemoved(parentPosition);
        }
    }
    /**
     * Метод удалаяет child елемент из данных адаптера
     * и уведомляет адаптер об изменениях.
     * В итоге возвращает ссылку на родительский элемент
     * из которого был удален child элемент
     * @param position
     * @return
     */
    private Object[] removeChildItem(int position){
        int childPosition = 0;
        int parentPosition = 0;
        boolean parentFound = false;
        for(int i = position; i > 0; i--) {
            if(mItemList.get(i).getClass().equals(ParentWrapper.class)) {
                if(!parentFound){
                    parentFound = true;
                }
                parentPosition++;
            } else if(!parentFound) {
                childPosition++;
            }
        }
        childPosition--;
        ParentItem p = (ParentItem)getParentItemList().get(parentPosition);
        List<ShoppingItem> childList = p.getChildItemList();
        childList.remove(childPosition);
        notifyChildItemRemoved(parentPosition,childPosition);
        Object[] o = {p,Integer.valueOf(parentPosition)};
        return o;
    }
}

