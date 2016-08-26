package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.presenter.FamilyPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IFamilyView;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;
import bearkid.com.bearkiddiaryfamily.ui.view.TagView;

public class FamilyActivity extends BaseActivity implements IFamilyView {

    private RecyclerView rv_familyMember;
    private ImageView iv_back;
    private IconButton ib_add;
    private FamilyPresenter presenter;
    private FamilyMemberAdapter memberAdapter;
    private List<User> members = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        initPresenter();
        initView();
    }

    private void initPresenter() {
        presenter = new FamilyPresenter(this);
    }

    private void initView() {
        rv_familyMember = (RecyclerView) findViewById(R.id.rv_family_member);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        ib_add = (IconButton) findViewById(R.id.ib_family_member_add);

        //家庭成员列表
        rv_familyMember.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_familyMember.setAdapter(memberAdapter = new FamilyMemberAdapter());

        //返回按钮
        iv_back.setOnClickListener(v -> finish());

        //添加家庭成员
        ib_add.setOnClickListener(v -> {
            //TODO: 跳转添加家庭成员界面
        });

        presenter.init();
    }

    @Override
    public void setMemberList(List<User> members) {
        this.members = members;
    }

    @Override
    public void addMemberList(List<User> members) {
        this.members.addAll(members);
    }

    @Override
    public void addMember(User member) {
        this.members.add(member);
    }

    @Override
    public void notifiyChanged() {
        memberAdapter.notifyDataSetChanged();
    }

    public static final void startActivity(Context context) {
        context.startActivity(new Intent(context, FamilyActivity.class));
    }

    class FamilyMemberAdapter extends RecyclerView.Adapter<FamilyMemberAdapter.MemberViewHolder> {
        @Override
        public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_family_members, parent, false);
            return new MemberViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return members.size();
        }

        @Override
        public void onBindViewHolder(MemberViewHolder holder, int position) {
            User user = members.get(position);

            if (user.getUname() == null) holder.tv_name.setText("未设置");
            else holder.tv_name.setText(user.getUname());
            holder.tv_phone.setText(user.getUphone());
            holder.ib_delete.setOnClickListener(v -> {
                members.remove(position);
                presenter.deleteMember(user);
            });
        }

        class MemberViewHolder extends RecyclerView.ViewHolder {
            TextView tv_name, tv_phone;
            TagView tv_relation;
            IconButton ib_delete;

            public MemberViewHolder(View v) {
                super(v);
                tv_name = (TextView) v.findViewById(R.id.tv_family_member_name);
                tv_phone = (TextView) v.findViewById(R.id.tv_family_member_phone);
                tv_relation = (TagView) v.findViewById(R.id.tv_family_member_relation);
                ib_delete = (IconButton) v.findViewById(R.id.ib_family_member_delete);
            }
        }
    }
}
