package com.he.heshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<com.he.heShop.Order> orderList;

    public OrderAdapter(List<com.he.heShop.Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        com.he.heShop.Order order = orderList.get(position);
        holder.orderName.setText(order.getOrderName());
        holder.orderAddress.setText(order.getOrderAddress());
        holder.orderPaymentMethod.setText(order.getOrderPaymentMethod());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        public TextView orderName;
        public TextView orderAddress;
        public TextView orderPaymentMethod;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderName = itemView.findViewById(R.id.order_name);
            orderAddress = itemView.findViewById(R.id.order_address);
            orderPaymentMethod = itemView.findViewById(R.id.order_payment_method);
        }
    }
}
