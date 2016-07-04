package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.Organization;
import bearkid.com.bearkiddiaryfamily.model.bean.Tea_Org;
import bearkid.com.bearkiddiaryfamily.model.bean.Teacher;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 测试专用
 */
public class TestActivity extends BaseActivity{

    TextView tv;
    EditText et;
    Button ok;

    Teacher teacher;
    Tea_Org tea_org;
    Organization organization;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        et = (EditText) findViewById(R.id.et_phoneNum);
        tv = (TextView) findViewById(R.id.tv_content);
        ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = et.getText().toString();

                BmobQuery<Teacher> query = new BmobQuery<Teacher>();
                query.addWhereEqualTo("Tphone",phoneNum);
                query.findObjects(new FindListener<Teacher>() {
                    @Override
                    public void done(List<Teacher> list, BmobException e) {
                        if(list.size()>0)teacher = list.get(0);
                    }
                });
                BmobQuery<Tea_Org> query1 = new BmobQuery<Tea_Org>();
                query1.addWhereEqualTo("teacher",new BmobPointer(teacher));
                query1.include("organization");
                query1.findObjects(new FindListener<Tea_Org>() {
                    @Override
                    public void done(List<Tea_Org> list, BmobException e) {
                        if(list.size()>0)tea_org = list.get(0);
                        tv.setText(tea_org.toString()+"\n"+tea_org.getOrganization().toString());
                    }
                });
            }
        });
    }
}
