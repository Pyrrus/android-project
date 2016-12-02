package agorbahn.android_project.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import agorbahn.android_project.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Intent myIntent = getIntent();
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

                return true;
            case R.id.itemAdd:
                myIntent = new Intent(DoctorActivity.this, AddActivity.class);

                return true;
            case R.id.itemAbout:
                myIntent = new Intent(DoctorActivity.this, AboutActivity.class);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
