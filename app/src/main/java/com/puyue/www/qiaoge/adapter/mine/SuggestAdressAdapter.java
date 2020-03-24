package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;

import java.util.List;

/**
 * Created by ${王文博} on 2019/6/25
 */
public class SuggestAdressAdapter extends RecyclerView.Adapter<SuggestAdressAdapter.MySuggestViewHolde> {

    private List<String> list;
    private Context context;

    private onClick onClick;


    public SuggestAdressAdapter(List<String> list, Context context,onClick  onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MySuggestViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.suggest_address, null);
        return new MySuggestViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySuggestViewHolde holder, int position) {
        holder.tv_suggest_address.setText(list.get(position));
        holder.tv_suggest_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.setLocation(holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


  /*  public SuggestAdressAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_suggest_address, item);
        Log.i("adsq", "convert: "+item);
    }*/


     class MySuggestViewHolde extends RecyclerView.ViewHolder {
        private TextView tv_suggest_address;

        public MySuggestViewHolde(View itemView) {
            super(itemView);
            tv_suggest_address = itemView.findViewById(R.id.tv_suggest_address);
        }
    }

    public interface onClick{
         void setLocation(int pos);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
