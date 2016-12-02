package agorbahn.android_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;

import agorbahn.android_project.R;
import agorbahn.android_project.models.Doctor;
import agorbahn.android_project.ui.DoctorViewActivity;
import agorbahn.android_project.ui.MainActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Adam on 12/2/2016.
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> Doctors) {
        mContext = context;
        mDoctors = Doctors;
    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
        holder.bindDoctors(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.docName) TextView mName;
        @Bind(R.id.docInfo) TextView mDocInfo;
        private Context mContext;


        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        public void bindDoctors(Doctor doctor) {
            mName.setText(doctor.getName());
            mDocInfo.setText(doctor.getSpecialty());
        }

        @Override
        public void onClick(View v) {
            Log.d("click listener", "working!");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DoctorViewActivity.class);
            intent.putExtra("doctor", Parcels.wrap(mDoctors));
            intent.putExtra("at", itemPosition);
            mContext.startActivity(intent);
        }
    }
}