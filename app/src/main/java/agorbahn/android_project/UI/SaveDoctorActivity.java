package agorbahn.android_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import agorbahn.android_project.Constants;
import agorbahn.android_project.R;
import agorbahn.android_project.adapters.FirebaseDoctorListViewHolder;
import agorbahn.android_project.models.Doctor;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SaveDoctorActivity extends AppCompatActivity {
    private DatabaseReference mDoctorReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    public static final String TAG = SaveDoctorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctor);

        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mDoctorReference = FirebaseDatabase.getInstance().getReference(Constants.DOCTOR_SAVE).child(uid);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Doctor, FirebaseDoctorListViewHolder>
                (Doctor.class, R.layout.cardview, FirebaseDoctorListViewHolder.class,
                        mDoctorReference) {

            @Override
            protected void populateViewHolder(FirebaseDoctorListViewHolder viewHolder,
                                              Doctor model, int position) {
                viewHolder.bindRestaurant(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        switch (item.getItemId()){
            case R.id.itemMain:
                myIntent = new Intent(SaveDoctorActivity.this, MainActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(SaveDoctorActivity.this, AboutActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemFav:
                myIntent = new Intent(SaveDoctorActivity.this, SaveDoctorActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemLogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SaveDoctorActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
