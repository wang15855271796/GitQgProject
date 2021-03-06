package com.puyue.www.qiaoge.view.selectmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;

import java.util.List;

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/9.
 * 描述：列表适配器
 */

public class TextAdapter extends ArrayAdapter<String> {
    private int selectedDrawble;
    private int normalDrawbleId;
    private List<String> mListData;
    private Context mContext;
    private int selectedPos = 0;
    private View.OnClickListener onClickListener;
    private String selectedText = "";
    private OnItemClickListener mOnItemClickListener;
    private boolean isShow;

    public TextAdapter(Context context, List<String> listData, int sId, int nId) {
        super(context, R.string.no_any, listData);
        mContext = context;
        mListData = listData;
        if (sId != 0) {
            selectedDrawble = sId;
        }
        normalDrawbleId = nId;
        initView();
    }

    private void initView() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = (Integer) view.getTag(R.id.position);
                notifyDataSetChanged();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, selectedPos);
                }
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_list, parent, false);
            holder.llRoot = (RelativeLayout) convertView.findViewById(R.id.ll_root);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvLine = (TextView) convertView.findViewById(R.id.tv_line);
            holder.ivArrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setTag(R.id.position, position);
        holder.tvTitle.setText(mListData.get(position));
      /*  if(position==mListData.size()-1){
            holder.tvLine.setVisibility(View.GONE);
        }else {
            holder.tvLine.setVisibility(View.VISIBLE);
        }*/
        //选中选项
        if (position == selectedPos) {
            holder.llRoot.setBackgroundColor(mContext.getResources().getColor(selectedDrawble));
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.select_title));
            holder.ivArrow.setImageResource(R.mipmap.ic_market_gou);
            isShow = true;
        } else {
            holder.llRoot.setBackgroundColor(mContext.getResources().getColor(normalDrawbleId));
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.color333333));
            holder.ivArrow.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            isShow = false;
        }
        if (isShow) {
            holder.ivArrow.setVisibility(View.VISIBLE);
        } else {
            holder.ivArrow.setVisibility(View.GONE);
        }
        convertView.setOnClickListener(onClickListener);
        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle, tvLine;
        RelativeLayout llRoot;
        ImageView ivArrow;
    }

    public void reSetPosition() {
        selectedPos = 0;
    }

    public void showArrow(boolean isShow) {
        this.isShow = isShow;
    }


    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

}
