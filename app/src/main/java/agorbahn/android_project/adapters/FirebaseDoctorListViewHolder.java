package agorbahn.android_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import agorbahn.android_project.Constants;
import agorbahn.android_project.R;
import agorbahn.android_project.models.Doctor;
import agorbahn.android_project.ui.DoctorViewActivity;
import butterknife.Bind;

/**
 * Created by Adam on 12/9/2016.
 */

public class FirebaseDoctorListViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    private DatabaseReference mRef;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    final ArrayList<Doctor> doctor = new ArrayList<>();
    DatabaseReference ref;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.empty_view) TextView mEmpty;

    public FirebaseDoctorListViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

       ref = FirebaseDatabase.getInstance().getReference(Constants.DOCTOR_SAVE).child(uid);

        itemView.setOnTouchListener(new onTouchListenerTool(mContext) {

            public void onClick(){

                ref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            doctor.add(snapshot.getValue(Doctor.class));
                        }

                        int itemPosition = getLayoutPosition();
                        Intent intent = new Intent(mContext, DoctorViewActivity.class);
                        intent.putExtra("doctor", Parcels.wrap(doctor.get(itemPosition)));

                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }

            public void onDoubleClick() {
                mRef = ref.getRef();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            doctor.add(snapshot.getValue(Doctor.class));
                        }

                        int itemPosition = getLayoutPosition();
                        mRef.child(doctor.get(itemPosition).getPushID()).removeValue();

                        Toast.makeText(mContext, "Remove", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
    }

    public void bindDoctor(Doctor doc) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.docName);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.docInfo);

        nameTextView.setText(doc.getName());
        categoryTextView.setText(doc.getSpecialty());
    }

    public ArrayList<Doctor> getDoctor() {
        return doctor;
    }
}