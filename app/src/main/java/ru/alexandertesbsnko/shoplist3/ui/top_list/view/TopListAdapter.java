package ru.alexandertesbsnko.shoplist3.ui.top_list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItemDataModel;
import ru.alexandertesbsnko.shoplist3.util.DateBuilder;

public class TopListAdapter extends RecyclerView.Adapter<TopListAdapter.ListViewHolder> {

    public interface OnClickListener {
        void onItemClick(View itemView, int position);
    }

    private OnClickListener listener;
    private List<TopListItemDataModel> mResults;

    public void setOnItemClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public TopListAdapter(List<TopListItemDataModel> shopLists) {
        mResults = shopLists;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_top_list, parent, false);
        ListViewHolder lvHolder = new ListViewHolder(view);
        return lvHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        holder.name.setText(mResults.get(position).getName());
        holder.date.setText(DateBuilder.timeTitleBuilder(mResults
                .get(position)
                .getDateMilis()));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public TopListItemDataModel getListItem(int position) {
        return mResults.get(position);
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView date;

        public ListViewHolder(View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.tv_list_name));
            date = ((TextView) itemView.findViewById(R.id.tv_list_date));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(view, position);
                        }
                    }
                }
            });
        }
    }
}
