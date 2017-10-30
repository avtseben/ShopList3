package ru.alexandertesbsnko.shoplist3.ui.top_list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.alexandertesbsnko.shoplist3.R;
import ru.alexandertesbsnko.shoplist3.ui.top_list.model.TopListItem;

public class TopListAdapter extends RecyclerView.Adapter<TopListAdapter.ListViewHolder> {

    public interface OnClickListener {
        void onItemClick(View itemView, int position);
    }

    private OnClickListener listener;
    private List<TopListItem> data;

    public void setOnItemClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public TopListAdapter(List<TopListItem> shopLists) {
        data = shopLists;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_top_list, parent, false);
        ListViewHolder lvHolder = new ListViewHolder(view);
        return lvHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        final TopListItem.SizeState sizeState = data.get(position).getSizeState();

        if(sizeState == TopListItem.SizeState.EMPTY){
            holder.image.setImageResource(0);//TODO add picture for empty list
        } else if(sizeState == TopListItem.SizeState.SMALL){
            holder.image.setImageResource(R.drawable.bag_1);
        } else if(sizeState == TopListItem.SizeState.MEDIUM){
            holder.image.setImageResource(R.drawable.bag_2);
        } else if(sizeState == TopListItem.SizeState.BIG){
            holder.image.setImageResource(R.drawable.bag_3);
        }
        holder.name.setText(data.get(position).getName());
        holder.date.setText(data.get(position).getDateTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView date;

        public ListViewHolder(View itemView) {
            super(itemView);
            image = ((ImageView) itemView.findViewById(R.id.image_in_shopList_title));
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
