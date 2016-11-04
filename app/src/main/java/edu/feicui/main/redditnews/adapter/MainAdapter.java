package edu.feicui.main.redditnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.info.NewsInfo;

/**
 * Created by Administrator on 2016/10/28.
 */

public class MainAdapter extends MyAdapter<NewsInfo>{

       Context mContext;
    public MainAdapter(Context context) {
        super(context);
        this.mContext=context;
    }

    @Override
    public View setView(int position, View convertView, ViewGroup parent) {
        Holder holder=null;
        if (convertView == null) {
            holder=new Holder();
             convertView= LayoutInflater.from(mContext).inflate(R.layout.adapter_main,parent,false);
           holder.mTxt_title= (TextView) convertView.findViewById(R.id.txt_adapter_main_title);
           holder.mTxt_stamp= (TextView) convertView.findViewById(R.id.txt_adapter_main_stamp);
            holder.mTxt_content= (TextView) convertView.findViewById(R.id.txt_adapter_main_content);
            holder.mImg= (CircleImageView) convertView.findViewById(R.id.img_adapter_main);
//            holder.mLyt = (LinearLayout) convertView.findViewById(R.id.lyt_adapter);
            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }
            holder.mTxt_title.setText(mList.get(position).getTitle());
           holder.mTxt_content.setText(mList.get(position).getSummary());
        holder.mTxt_stamp.setText(mList.get(position).getStamp());
//        holder.mLyt.setBackgroundResource(mBackground[position%mBackground.length]);
        Picasso.with(convertView.getContext()).load(mList.get(position).getIcon()).into(holder.mImg);
        return convertView;
    }
    static class Holder{
           CircleImageView mImg;
           TextView mTxt_title;
           TextView mTxt_content;
           TextView mTxt_stamp;
        LinearLayout mLyt;
    }
}
