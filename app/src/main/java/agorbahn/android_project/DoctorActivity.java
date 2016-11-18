package agorbahn.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorActivity extends AppCompatActivity {
    private ArrayList<String> mDoctor = new ArrayList<String>();
    private ArrayList<String> mDoctorInfo = new ArrayList<String>();
    @Bind(R.id.info) TextView mInfo;
    @Bind(R.id.header) TextView mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Intent myIntent = getIntent();
        mDoctor = myIntent.getStringArrayListExtra("doctorList");
        mDoctorInfo = myIntent.getStringArrayListExtra("infoList");
        String header = myIntent.getStringExtra("title");
        String Information = myIntent.getStringExtra("doctorInfo");
        ButterKnife.bind(this);
        mHeader.setText(header);
        mInfo.setText(Information);
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
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
            case R.id.itemAdd:
                myIntent = new Intent(DoctorActivity.this, AddActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(DoctorActivity.this, AboutActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
