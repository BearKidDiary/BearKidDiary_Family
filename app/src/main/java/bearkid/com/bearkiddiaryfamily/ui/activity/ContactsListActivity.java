package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.ContactBean;
import bearkid.com.bearkiddiaryfamily.ui.view.ClearEditText;
import bearkid.com.bearkiddiaryfamily.ui.view.SideBar;
import bearkid.com.bearkiddiaryfamily.adapter.ContactsAdapter;
import bearkid.com.bearkiddiaryfamily.utils.ContactsType;
import bearkid.com.bearkiddiaryfamily.utils.PinyinUtils;


/**
 * Created by admin on 2016/7/12.
 */
public class ContactsListActivity extends BaseActivity implements View.OnClickListener {

    private int ListType;//该列表是 查看联系人列表  还是  选择联系人列表

    private ClearEditText mClearEditText;//搜索框
    private SideBar sideBar;//联系人的字母标识栏
    private ListView sortListView;//显示联系人listView
    private ContactsAdapter adapter;
    private TextView dialog;
    private ButtonFlat confirmBtn;

    private ImageView backImg;

    private List<ContactBean> SourceDateList;//联系人列表

    private int countChecked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        ListType = getIntent().getIntExtra("ListType", 0);

        initView();
    }

    public void initView(){

        sideBar = (SideBar) findViewById(R.id.sideBar);
        sortListView = (ListView) findViewById(R.id.list_contacts);
        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
        dialog = (TextView) findViewById(R.id.dialog);

        confirmBtn = (ButtonFlat) findViewById(R.id.btn_contacts_choose_confirm);
        confirmBtn.setOnClickListener(this);

        backImg = (ImageView) findViewById(R.id.img_title_back_contacts);
        backImg.setOnClickListener(this);

        sideBar.setTextView(dialog);
        //设置SideBar监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                sortListView.setSelection(position);
            }
        });

        //联系人列表的Item监听
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //利用adapter.getItem(position)来获取当前position所对应的对象
                //判断是选择联系人列表还是查看联系人列表
                if (ListType == ContactsType.CHECK){
                    Toast.makeText(ContactsListActivity.this, ((ContactBean)adapter.getItem(position)).getName(),Toast.LENGTH_SHORT).show();
                }else {
                    int ischecked = ((ContactBean)adapter.getItem(position)).getIschecked();
                    if (ischecked == ContactsType.CHECKED){
                        ((ContactBean)adapter.getItem(position)).setIschecked(ContactsType.NOCHECKED);
                        countChecked --;
                    }else {
                        ((ContactBean)adapter.getItem(position)).setIschecked(ContactsType.CHECKED);
                        countChecked ++;
                    }
                    confirmBtn.setText("确定（" + countChecked + ")");
                    adapter.notifyDataSetChanged();
                }

            }
        });

        //获取到联系人列表，String[] names
        SourceDateList = filledData(getResources().getStringArray(R.array.names));
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList);
        adapter = new ContactsAdapter(this, SourceDateList, ListType);
        sortListView.setAdapter(adapter);

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if (ListType == ContactsType.CHOOSE){
            confirmBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back_contacts:
                finish();
                break;
            case R.id.btn_contacts_choose_confirm:
                Log.d("contactslist : " , getCheckedContacts().size() + "");
                break;
        }
    }

    /**
     * 获取已被选中的联系人列表
     * @return
     */
    public List<ContactBean> getCheckedContacts(){
        List<ContactBean> checkedList = new ArrayList<>();
        for (ContactBean contactBean : SourceDateList){
            if (contactBean.getIschecked() == ContactsType.CHECKED){
                checkedList.add(contactBean);
            }
        }
        return checkedList;
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<ContactBean> filledData(String [] date){
        List<ContactBean> mSortList = new ArrayList<ContactBean>();

        for(int i=0; i<date.length; i++){
            ContactBean sortModel = new ContactBean();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0,1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setPingyin(sortString.toUpperCase());
            }else{
                sortModel.setPingyin("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<ContactBean> filterDateList = new ArrayList<ContactBean>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(ContactBean sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || PinyinUtils.getPingYin(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList);
        adapter.updateListView(filterDateList);
    }

    public static void startActivity(Context context,int ListType){
        Intent intent = new Intent(context,ContactsListActivity.class);
        intent.putExtra("ListType",ListType);
        context.startActivity(intent);
    }

}
