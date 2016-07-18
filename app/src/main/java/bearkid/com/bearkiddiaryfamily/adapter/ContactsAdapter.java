package bearkid.com.bearkiddiaryfamily.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.ContactBean;
import bearkid.com.bearkiddiaryfamily.ui.view.CircleImageview;
import bearkid.com.bearkiddiaryfamily.utils.ContactsType;

/**
 * Created by admin on 2016/7/12.
 */
public class ContactsAdapter extends BaseAdapter implements SectionIndexer{

    private List<ContactBean> list = null;
    private Context mContext;
    private int ListType;

    public ContactsAdapter(Context mContext, List<ContactBean> list, int ListType) {
        this.mContext = mContext;
        this.list = list;
        this.ListType = ListType;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * @param list
     */
    public void updateListView(List<ContactBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final ContactBean mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, null);
            viewHolder.nameTxt = (TextView) view.findViewById(R.id.txt_contacts_item_name);
            viewHolder.letterTxt = (TextView) view.findViewById(R.id.txt_contacts_item_letter_title);
            viewHolder.avatarImg = (CircleImageview) view.findViewById(R.id.img_contacts_avatar);
            viewHolder.checkCb = (CheckBox) view.findViewById(R.id.cb_contacts_check);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //如果是选择联系人列表，则显示checkBox
        if (ListType == ContactsType.CHOOSE){
            viewHolder.checkCb.setVisibility(View.VISIBLE);
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(section)){
            viewHolder.letterTxt.setVisibility(View.VISIBLE);
            viewHolder.letterTxt.setText(mContent.getPingyin());
        }else{
            viewHolder.letterTxt.setVisibility(View.GONE);
        }

        viewHolder.nameTxt.setText(this.list.get(position).getName());
        viewHolder.avatarImg.setImageResource(R.drawable.avatar);
        if (list.get(position).getIschecked() == ContactsType.CHECKED){
            viewHolder.checkCb.setChecked(true);
            viewHolder.nameTxt.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
//            view.setBackgroundColor(mContext.getResources().getColor(R.color.bg_gray_Me_lv));
        }else {
            viewHolder.checkCb.setChecked(false);
            viewHolder.nameTxt.setTextColor(mContext.getResources().getColor(R.color.base_color_text_black));
//            view.setBackgroundColor(mContext.getResources().getColor(R.color.base_color_text_white));
        }

        return view;

    }



    final static class ViewHolder {
        TextView letterTxt;
        TextView nameTxt;
        CircleImageview avatarImg;
        CheckBox checkCb;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getPingyin().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getPingyin();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String  sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
