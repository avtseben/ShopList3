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

public class SearchAutoCompleteAdapter extends BaseAdapter implements Filterable{


    private List<Product> mResults;
    private final IShoppingListView superView;
    private final Context mContext;

    public SearchAutoCompleteAdapter(IShoppingListView superView, Context context) {
        this.superView = superView;
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


    public void setResults(List<Product> findedProducts) {
        this.mResults = findedProducts;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                System.out.println("Perform: " + mResults + " constraint " + constraint);
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    superView.searchShoppingItems(constraint.toString());
//                    try {
//                        Thread.sleep(500);//TODO LAZY HACK.  Искуственнно ждем полсекунды чтобы дождаться когда презентер высавит новый список
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    filterResults.values = mResults;
                    filterResults.count = mResults.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                System.out.println("Publish: " + mResults);
                if (results != null && results.count > 0) {
                    mResults = (List<Product>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}

