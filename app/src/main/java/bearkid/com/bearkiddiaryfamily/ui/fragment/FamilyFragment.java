package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.FamilyKidMsg;
import bearkid.com.bearkiddiaryfamily.ui.view.CircleImageview;

public class FamilyFragment extends BaseFragment {

    private RecyclerView rv_family;
    private List<FamilyKidMsg> kidList = new ArrayList<FamilyKidMsg>() {
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family, container, false);

        initView(view);

        return view;
    }

    private final void initView(View v) {
        rv_family = (RecyclerView) v.findViewById(R.id.rv_family);

        rv_family.setAdapter(new FamilyAdapter());
        rv_family.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    class FamilyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        final static int TYPE_KID = 1;
        final static int TYPE_RELATIVE = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View view = LayoutInflater.from(FamilyFragment.this.getContext()).inflate(R.layout.item_family_kid, parent, false);
                return new RelativeViewHolder(view);
            } else {
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            return TYPE_KID;
//            if (position < kidList.size()) return TYPE_KID;
//            return TYPE_RELATIVE;
        }

        class KidViewHolder extends RecyclerView.ViewHolder {
            CircleImageview civ_avatar;

            public KidViewHolder(View view) {
                super(view);
                civ_avatar = (CircleImageview) view.findViewById(R.id.civ_family_kid_avatar);
            }
        }

        class RelativeViewHolder extends RecyclerView.ViewHolder {

            public RelativeViewHolder(View view) {
                super(view);
            }
        }
    }
}
