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
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.model.Product;
import ru.alexandertesbsnko.shoplist3.ui.shoping_list.presenter.IShoppingListPresenter;

public class SearchAutoCompleteAdapter extends BaseAdapter implements Filterable{


    private List<Product> mResults;
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
        Product product = (Product) getItem(position);
        ((TextView) convertView.findViewById(R.id.product_name)).setText(product.getName());
        ((TextView) convertView.findViewById(R.id.product_category_name)).setText(product.getCategoryName());

        return convertView;
    }


    private void setResults(List<Product> findedProducts) {
        this.mResults = findedProducts;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<Product> founded = presenter.searchProductsByNameSync(constraint.toString());
                    setResults(founded);
                    filterResults.values = founded;
                    filterResults.count = founded.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                if (results != null && results.count > 0) {
                    mResults = (List<Product>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}

