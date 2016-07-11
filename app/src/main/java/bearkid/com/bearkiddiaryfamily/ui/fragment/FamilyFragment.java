package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.Course;
import bearkid.com.bearkiddiaryfamily.model.bean.FamilyKidMsg;
import bearkid.com.bearkiddiaryfamily.presenter.FamilyPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.CourseActivity;
import bearkid.com.bearkiddiaryfamily.ui.view.CircleImageview;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

public class FamilyFragment extends BaseFragment {

    private FamilyPresenter presenter;
    private RecyclerView rv_family;
    private List<FamilyKidMsg> kidList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family, container, false);

        initPresenter();
        initView(view);
        presenter.initDate();

        return view;
    }

    private final void initPresenter() {
        presenter = new FamilyPresenter(this);
    }

    private final void initView(View v) {
        rv_family = (RecyclerView) v.findViewById(R.id.rv_family);

        rv_family.setAdapter(new FamilyAdapter());
        rv_family.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void updateKidList(List<FamilyKidMsg> list) {
        this.kidList = list;
    }

    class FamilyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        final static int TYPE_KID = 1;
        final static int TYPE_RELATIVE = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View view = LayoutInflater.from(FamilyFragment.this.getContext()).inflate(R.layout.item_family_kid, parent, false);
                return new KidViewHolder(view);
            } else {
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof KidViewHolder) {
                onBindKidViewHolder((KidViewHolder) holder, position);
            }
        }

        private void onBindKidViewHolder(KidViewHolder v, int position) {
            FamilyKidMsg msg = kidList.get(position);

            v.tv_age.setText(msg.getAge() + "岁");
            v.tv_name.setText(msg.getName());
            v.civ_avatar.setImageResource(R.drawable.avatar);

            List<Course> courses;
            if ((courses = msg.getCourses()) != null && courses.size() != 0) {
                for (Course course : courses) {
                    View courseView = LayoutInflater.from(FamilyFragment.this.getContext()).inflate(R.layout.item_family_kid_course, v.ll_root, true);
                    ((TextView) courseView.findViewById(R.id.tv_family_kid_course_name)).setText(course.getCname());
                    ((TextView) courseView.findViewById(R.id.tv_family_kid_course_time)).setText(course.getCclasstime().getDate());
                    ((TextView) courseView.findViewById(R.id.tv_family_kid_course_org)).setText("春田花花");
                    courseView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CourseActivity.startActivity(getContext());
                        }
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            return kidList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return TYPE_KID;
//            if (position < kidList.size()) return TYPE_KID;
//            return TYPE_RELATIVE;
        }

        class KidViewHolder extends RecyclerView.ViewHolder {
            CircleImageview civ_avatar;
            TextView tv_name, tv_age;
            IconButton ib_graph, ib_show;
            LinearLayout ll_root;
            View courseView;

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
            public RelativeViewHolder(View view) {
                super(view);
            }
        }
    }
}
