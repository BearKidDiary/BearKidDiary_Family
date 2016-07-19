package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.activity.ChatActivity;
import bearkid.com.bearkiddiaryfamily.ui.view.RefreshRecyclerView;

/**
 * Created by admin on 2016/7/11.
 * 动态界面
 */
public class MessageFragment extends BaseFragment {
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_message, container, false);
        mContext = layoutInflater.getContext();
        RefreshRecyclerView refreshRecyclerView = (RefreshRecyclerView) view.findViewById(R.id.fragment_message_refresh);
        refreshRecyclerView.setAdapter(new RecyclerViewAdapter());
        return view;
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.item_message, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            //holder.txt.setText("1");//mDatas.get(position)
            holder.root.setTag(position);
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ChatActivity.startActivity(mContext);//v.getTag()
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return 5;//mDatas.size()
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            LinearLayout root;
            TextView txt;
            public MyViewHolder(View view)
            {
                super(view);
                root = (LinearLayout) view.findViewById(R.id.item_message_root);
                txt = (TextView) view.findViewById(R.id.item_message_txt);
            }
        }
    }

}