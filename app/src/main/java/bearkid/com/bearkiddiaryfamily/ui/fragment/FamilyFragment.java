package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.Course;
import bearkid.com.bearkiddiaryfamily.model.bean.FamilyKidMsg;
import bearkid.com.bearkiddiaryfamily.model.bean.FamilyUser;
import bearkid.com.bearkiddiaryfamily.presenter.FamilyPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.CourseActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.DataAnalysisActivity;
import bearkid.com.bearkiddiaryfamily.ui.view.CircleImageview;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

public class FamilyFragment extends BaseFragment {

    private RecyclerView rv_family;
    private ImageView iv_menu;
    final private FamilyPresenter presenter = new FamilyPresenter(this);
    final private FamilyAdapter mAdapter = new FamilyAdapter();
    /**
     * 对应家庭的孩子的数据
     */
    private List<FamilyKidMsg> kidList = new ArrayList<>();
    /**
     * 对应家庭成员的数据
     */
    private List<FamilyUser> relativeList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family, container, false);

        initView(view);
        initData();

        return view;
    }

    private final void initView(View v) {
        //RecyclerView列表
        rv_family = (RecyclerView) v.findViewById(R.id.rv_family);
        rv_family.setAdapter(mAdapter);
        rv_family.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_family.addItemDecoration(new FamilyDecoration());

        //家庭管理“+”菜单
        iv_menu = (ImageView) v.findViewById(R.id.iv_family_menu);

        PopupMenu menu = new PopupMenu(getContext(), iv_menu);

    }

    private final void initData() {
        presenter.initData();
    }

    /**
     * 更新所有孩子的信息
     */
    public void updateKidList(List<FamilyKidMsg> list) {
        kidList = list;
    }

    /**
     * 更新整个亲戚列表
     */
    public void updateRelativeList(List<FamilyUser> list) {
        relativeList = list;
    }

    public void notifyUpdate() {
        mAdapter.notifyDataSetChanged();
    }

    public void addKid(FamilyKidMsg msg) {
        kidList.add(msg);
    }

    /**
     * RecyclerView的适配器 适配 孩子（kidList）和家庭成员（RelativeList）的数据
     */
    class FamilyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final static int TYPE_KID = 1;
        private final static int TYPE_RELATIVE = 2;
        private final Context context = FamilyFragment.this.getContext();


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_KID) {
                View view = LayoutInflater.from(FamilyFragment.this.getContext()).inflate(R.layout.item_family_kid, parent, false);
                return new KidViewHolder(view);
            } else if (viewType == TYPE_RELATIVE) {
                View view = LayoutInflater.from(FamilyFragment.this.getContext()).inflate(R.layout.item_family_relative, parent, false);
                return new RelativeViewHolder(view);
            } else {
                Log.e("zy", "FamilyFragment onCreateViewHolder error: unknown viewType");
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //TODO: 根据holder的类型分发到对应的处理函数
            if (holder instanceof KidViewHolder) {
                onBindKidViewHolder((KidViewHolder) holder, position);
            } else if (holder instanceof RelativeViewHolder) {
                onBindRelativeViewHolder((RelativeViewHolder) holder, position);
            } else {
                Log.e("zy", "FamilyFragment onBindViewHolder error: unknown type");
            }
        }

        private void onBindKidViewHolder(KidViewHolder v, int position) {
            //TODO：处理显示孩子的数据
            FamilyKidMsg msg = kidList.get(position);

            v.tv_age.setText(msg.getAge() + "岁");
            v.tv_name.setText(msg.getName());
            v.civ_avatar.setImageResource(R.drawable.avatar);
            v.ll_root.removeAllViews();
            List<Course> courses;
            if ((courses = msg.getCourses()) != null && courses.size() != 0) {
                for (Course course : courses) {
                    View courseView = LayoutInflater.from(FamilyFragment.this.getContext()).inflate(R.layout.item_family_kid_course, v.ll_root, true);
                    ((TextView) courseView.findViewById(R.id.tv_family_kid_course_name)).setText(course.getCname());
                    ((TextView) courseView.findViewById(R.id.tv_family_kid_course_time)).setText(course.getCclasstime().getDate());
                    ((TextView) courseView.findViewById(R.id.tv_family_kid_course_org)).setText("春田花花");
                    courseView.setOnClickListener(view -> CourseActivity.startActivity(FamilyFragment.this.getContext()));
                }
            }
            v.ib_graph.setOnClickListener(view -> {
                DataAnalysisActivity.startActivity(getContext());
            });
            v.ib_show.setOnClickListener(view -> {

            });
        }

        private final void onBindRelativeViewHolder(RelativeViewHolder v, int position) {
            //TODO：处理显示家庭其他成员的数据
            int pos = position - kidList.size();
            FamilyUser member = relativeList.get(pos);

            v.tv_name.setText(member.getFUname());
            v.tv_relation.setText("妈妈");
        }

        @Override
        public int getItemCount() {
            return kidList.size() + relativeList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < kidList.size())
                return TYPE_KID;
            else
                return TYPE_RELATIVE;
        }

        class KidViewHolder extends RecyclerView.ViewHolder {
            CircleImageview civ_avatar;
            TextView tv_name, tv_age;
            IconButton ib_graph, ib_show;
            LinearLayout ll_root;

            public KidViewHolder(View view) {
                super(view);
                civ_avatar = (CircleImageview) view.findViewById(R.id.civ_family_kid_avatar);
                tv_name = (TextView) view.findViewById(R.id.tv_family_kid_name);
                tv_age = (TextView) view.findViewById(R.id.tv_family_kid_age);
                ib_graph = (IconButton) view.findViewById(R.id.ib_family_kid_graph);
                ib_show = (IconButton) view.findViewById(R.id.ib_family_kid_show);
                ll_root = (LinearLayout) view.findViewById(R.id.ll_family_kid_root);
            }
        }

        class RelativeViewHolder extends RecyclerView.ViewHolder {
            CircleImageview civ_avatar;
            TextView tv_name, tv_relation;

            public RelativeViewHolder(View view) {
                super(view);
                civ_avatar = (CircleImageview) view.findViewById(R.id.civ_family_relative_avatar);
                tv_name = (TextView) view.findViewById(R.id.tv_family_relative_name);
                tv_relation = (TextView) view.findViewById(R.id.tv_family_relative_relation);
            }
        }
    }

    /**
     * 普通的列表分割线
     */
    static class FamilyDecoration extends RecyclerView.ItemDecoration {

        final Paint paint = new Paint();

        {
            paint.setColor(0xff000000);
            paint.setStrokeWidth(2.0f);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            int width = parent.getWidth();
            Log.i("zy", "drawLine " + width);
            c.drawLine(0, 0, width, 0, paint);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            //outRect.set(0, 0, parent.getWidth(), 10);
            Log.i("zy", "rect:" + outRect != null ? outRect.toString() : "null");
        }
    }
}
