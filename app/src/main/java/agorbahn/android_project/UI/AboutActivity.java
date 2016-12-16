package agorbahn.android_project.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import agorbahn.android_project.Constants;
import agorbahn.android_project.R;

public class AboutActivity extends AppCompatActivity {
    private ArrayList<String> mDoctor = new ArrayList<String>();
    private ArrayList<String> mDoctorInfo = new ArrayList<String>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Intent myIntent = getIntent();
        mDoctor = myIntent.getStringArrayListExtra("doctorList");
        mDoctorInfo = myIntent.getStringArrayListExtra("infoList");
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
                myIntent = new Intent(AboutActivity.this, MainActivity.class);
                addToSharedPreferences("");
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemFav:
                myIntent = new Intent(AboutActivity.this, SaveDoctorActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemLogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AboutActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String place) {
        mEditor.putString(Constants.PLACE, place).apply();
    }
}
