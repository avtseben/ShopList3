package ru.alexandertesbsnko.shoplist3.ui.shoping_list.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.ShoppingItem;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.IShoppingListPresenter;

public class SearchAutoCompleteAdapter extends BaseAdapter implements Filterable{


    private List<ShoppingItem> mResults;
    private final IShoppingListPresenter presenter;
    private final Context mContext;

    public SearchAutoCompleteAdapter(IShoppingListPresenter presenter, Context context) {
        this.presenter = presenter;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public Object getItem(int index) {
        return mResults.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.search_dropdown_item,null);//Инфлэйтим
        }
        ShoppingItem shoppingItem = (ShoppingItem) getItem(position);
        ((TextView) convertView.findViewById(R.id.text1)).setText(shoppingItem.getMerchandise().getName());
        ((TextView) convertView.findViewById(R.id.text2)).setText(shoppingItem.getMerchandise().getCategory().getName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<ShoppingItem> products = presenter.findShoppingItemLike(constraint.toString());
                    filterResults.values = products;
                    filterResults.count = products.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                if (results != null && results.count > 0) {
                    mResults = (List<ShoppingItem>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}

