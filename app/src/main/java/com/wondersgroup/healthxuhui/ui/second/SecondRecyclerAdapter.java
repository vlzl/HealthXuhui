package com.wondersgroup.healthxuhui.ui.second;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wondersgroup.healthxuhui.R;
import com.wondersgroup.healthxuhui.data.entity.SecondItemEntity;
import com.wondersgroup.healthxuhui.widget.OnItemClickListener;

import java.util.List;

/**
 * Created by yangjinxi on 2016/6/20.
 */
public class SecondRecyclerAdapter extends RecyclerView.Adapter<SecondRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<SecondItemEntity> datas;
    private OnItemClickListener mOnItemClickListener;

    public SecondRecyclerAdapter(Context context, List<SecondItemEntity> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_second, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        SecondItemEntity entity = datas.get(position);
        String pngUrl = "asset://com.wondersgroup.healthxuhui/image/" + entity.getItemName().trim() + ".png";
        holder.simpleDraweeView.setImageURI(Uri.parse(pngUrl));
        holder.textView.setText(entity.getName());

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_item);
            textView = (TextView) itemView.findViewById(R.id.tv_item_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
