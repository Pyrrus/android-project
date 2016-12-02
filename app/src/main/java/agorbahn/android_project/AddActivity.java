package agorbahn.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.submitAdd) Button mAddButton;
    @Bind(R.id.Information) EditText mInformation;
    @Bind(R.id.addDoc) EditText mAddDoc;
    private ArrayList<String> mDoctor = new ArrayList<String>();
    private ArrayList<String> mDoctorInfo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent myIntent = getIntent();
        mDoctor = myIntent.getStringArrayListExtra("doctorList");
        mDoctorInfo = myIntent.getStringArrayListExtra("infoList");
        ButterKnife.bind(this);

        mAddButton.setOnClickListener(this);
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
                myIntent = new Intent(AddActivity.this, MainActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
            case R.id.itemAdd:
                myIntent = new Intent(AddActivity.this, AddActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(AddActivity.this, AboutActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String output = "";
        if (mAddDoc.getText().toString().equals("")) {
            output += "Need to add name for Doctor\n";
        }

        if (mInformation.getText().toString().equals("")) {
            output += "Need to add information for Doctor";
        }

        if (output.equals("")) {
            mDoctor.add(mAddDoc.getText().toString());
            mDoctorInfo.add(mInformation.getText().toString());
            Intent myIntent = new Intent(AddActivity.this, MainActivity.class);
            myIntent.putExtra("doctorList", mDoctor);
            myIntent.putExtra("infoList", mDoctorInfo);
            startActivity(myIntent);
        } else {
            Toast.makeText(AddActivity.this, output, Toast.LENGTH_SHORT).show();
        }
    }
}
