package com.example.buymenow.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buymenow.Models.PopularModel;
import com.example.buymenow.R;
import com.example.buymenow.RecyclerViewInterface;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PopularityAdapter extends RecyclerView.Adapter<PopularityAdapter.viewholder> {

    private final RecyclerViewInterface recyclerViewInterface;

    private final List<PopularModel> list;
    private Context context;

    public PopularityAdapter(Context context, List<PopularModel> list , RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_list , parent , false);
        return new viewholder(view , recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.popName.setText(list.get(position).getName());
        holder.popPrice.setText(list.get(position).getPrice());
        String str = list.get(position).getImage();
        Picasso.get().load(str).into(holder.popImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        private final ImageView popImg;
        private final TextView popName;
        private final TextView popPrice;

        public viewholder(@NonNull View itemView , RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            popName = itemView.findViewById(R.id.pop_name);
            popPrice= itemView.findViewById(R.id.pop_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
