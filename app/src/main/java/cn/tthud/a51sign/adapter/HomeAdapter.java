package cn.tthud.a51sign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.tthud.a51sign.R;
import cn.tthud.a51sign.fragment.HomeFragment;

/**
 * Created by yh on 2018/3/26.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private List<String> datas;
    private Context context;

    public HomeAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.homefragment_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        ((MyViewHolder) holder).tv_coinname.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_coinname;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_coinname = itemView.findViewById(R.id.tv_coinname);

        }
    }

}
