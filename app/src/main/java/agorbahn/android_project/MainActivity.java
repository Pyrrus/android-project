package agorbahn.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.listView) GridView mList;
    private ArrayList<String> mDoctorInfo = new ArrayList<String>(Arrays.asList(
            "Doctor Strange is a skilled athlete and martial artist with substantial medical and magical knowledge. Though an expert surgeon, Strange's nerve-damaged hands prevent him from performing surgery except when supplemented by magic.",
            "Doctor Fate is a legacy of heroes in the realm of magic who act as agents of the Lords of Order in the battle against chaos, using the powerful Amulet of Anubis, Cloak of Destiny and Helmet of Fate. "
    ));
    private ArrayList<String> mDoctor = new ArrayList<String>(Arrays.asList(
            "Doctor Strange",
            "Doctor Fate"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        if (mIntent.getStringArrayListExtra("doctorList") != null) {
            mDoctor = mIntent.getStringArrayListExtra("doctorList");
            mDoctorInfo = mIntent.getStringArrayListExtra("infoList");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDoctor);

        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(MainActivity.this, DoctorActivity.class);
                myIntent.putExtra("doctorInfo", mDoctorInfo.get(position));
                myIntent.putExtra("title", mDoctor.get(position));
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
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
                myIntent = new Intent(MainActivity.this, MainActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
            case R.id.itemAdd:
                myIntent = new Intent(MainActivity.this, AddActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(MainActivity.this, AboutActivity.class);
                myIntent.putExtra("doctorList", mDoctor);
                myIntent.putExtra("infoList", mDoctorInfo);
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

