package com.dhanifudin.cashflow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhanifudin.cashflow.R;
import com.dhanifudin.cashflow.models.Transaction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    private List<Transaction> items;
    private OnItemTransactionListener listener;

    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public interface OnItemTransactionListener {
        void onTransactionClicked(int index, Transaction item);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Transaction item = items.get(position);
        holder.bind(position,item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionText;
        TextView amountText;
        TextView jenisText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jenisText = itemView.findViewById(R.id.text_jenis);
            descriptionText = itemView.findViewById(R.id.text_description);
            amountText = itemView.findViewById(R.id.text_amount);
        }

        public  void bind(final int index, final  Transaction item){
            descriptionText.setText(item.getDescription());
            amountText.setText(formatRupiah.format(item.getAmount()));
            jenisText.setText(item.getType().toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTransactionClicked(index,item);
                }
            });
        }
    }

}
