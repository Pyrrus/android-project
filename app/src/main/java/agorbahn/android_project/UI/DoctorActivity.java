package agorbahn.android_project.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

import agorbahn.android_project.Constants;
import agorbahn.android_project.R;
import agorbahn.android_project.adapters.DoctorListAdapter;
import agorbahn.android_project.models.Doctor;
import agorbahn.android_project.services.DoctorServices;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorActivity extends AppCompatActivity {
    public static final String TAG = DoctorActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private String mLocation;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;

    public ArrayList<Doctor> mDoctor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("place");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mLocation = mSharedPreferences.getString(Constants.PLACE, null);
        Log.d("Shared Pref Location", mLocation);

        if (mLocation != null) {
            getDoctors(mLocation);
        }
    }

    private void getDoctors(String location) {
        final DoctorServices doctorservices = new DoctorServices();

        doctorservices.findDoctors(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctor = doctorservices.processResults(response);

                DoctorActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctor);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(DoctorActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
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
                myIntent = new Intent(DoctorActivity.this, MainActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(DoctorActivity.this, AboutActivity.class);
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
