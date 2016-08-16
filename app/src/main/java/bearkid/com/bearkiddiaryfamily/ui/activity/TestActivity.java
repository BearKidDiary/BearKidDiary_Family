package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
public class TestActivity extends BaseActivity {

    EditText phoneEdit;
    EditText pswEdit;
    Button ok;

    Teacher teacher;
    Tea_Org tea_org;
    Organization organization;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        phoneEdit = (EditText) findViewById(R.id.et_phoneNum);
        pswEdit = (EditText) findViewById(R.id.et_password);
        ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = "http://192.168.191.1:8080/BearKidDiary_Service/regist.jsp";
                        String content = "name=" + phoneEdit.getText().toString() + "&password=" + pswEdit.getText().toString();
                        String msg = doPost(url + content, content);
                        Log.d("Register", msg);
                    }
                }).start();
            }
        });
//        ok.setOnClickListener(view -> {
//            String phoneNum = et.getText().toString();
//
//            BmobQuery<Teacher> query = new BmobQuery<Teacher>();
//            query.addWhereEqualTo("Tphone",phoneNum);
//            query.findObjects(new FindListener<Teacher>() {
//                @Override
//                public void done(List<Teacher> list, BmobException e) {
//                    if(list.size()>0)teacher = list.get(0);
//                }
//            });
//            BmobQuery<Tea_Org> query1 = new BmobQuery<Tea_Org>();
//            query1.addWhereEqualTo("teacher",new BmobPointer(teacher));
//            query1.include("organization.Oname");
//            query1.findObjects(new FindListener<Tea_Org>() {
//                @Override
//                public void done(List<Tea_Org> list, BmobException e) {
////                        if(list.size()>0)
////                            tea_org = list.get(0);
//                    //tv.setText(tea_org.toString()+"\n"+tea_org.getOrganization().toString());
//                    if (e != null){
//                        Log.e("exception" , e.toString());
//                    }if (list.isEmpty()){
//                        Log.d("teacher",list.size() + "");
//                        return;
//                    }
//                    Log.d("teacher",list.get(0).getOrganization().toString() + "");
//                }
//            });
//        });
    }

    public String doPost(String url, String content) {
        try {
            URL httpurl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpurl.openConnection();

            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            OutputStream out = connection.getOutputStream();
            out.write(content.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }

            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
