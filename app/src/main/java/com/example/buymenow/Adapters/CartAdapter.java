package com.example.buymenow.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.buymenow.Models.CartModel;
import com.example.buymenow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {

    Context context;
    List<CartModel> list;
    String totalAmount;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    public CartAdapter(Context context, List<CartModel> list) {
        this.context = context;
        this.list = list;
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        position = holder.getAdapterPosition();

        holder.date.setText(list.get(position).getCurrentDate());
        holder.time.setText(list.get(position).getCurrentTime());
        holder.price.setText(list.get(position).getProductPrice());
        holder.name.setText(list.get(position).getProductName());

        int finalPosition = position;
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fStore.collection("Add To Cart").document(fAuth.getCurrentUser().getUid())
                        .collection("user")
                        .document(list.get(finalPosition).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    list.remove(list.get(finalPosition));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Total amount of all the products in the Cart
        totalAmount =  list.get(position).getProductPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount" , totalAmount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView name, price , date , time ;
        Button removeItem;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            removeItem = itemView.findViewById(R.id.removeItem);

        }
    }
}
