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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.listView) GridView mListTask;
    private ArrayList<String> mTaskInfo = new ArrayList<String>(Arrays.asList(
            "Need to do research for the make best app using api.",
            "Need to clear the 175 sq. feet place",
            "Build app using java and other tools"
    ));
    private ArrayList<String> mTask = new ArrayList<String>(Arrays.asList(
            "Homework",
            "Cleaning",
            "Make the App"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        if (mIntent.getStringArrayListExtra("taskList") != null) {
            mTask = mIntent.getStringArrayListExtra("taskList");
            mTaskInfo = mIntent.getStringArrayListExtra("taskInfo");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mTask);

        mListTask.setAdapter(adapter);
        mListTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(MainActivity.this, TaskActivity.class);
                myIntent.putExtra("taskInfo", mTaskInfo.get(position));
                myIntent.putExtra("title", mTask.get(position));
                myIntent.putExtra("taskList", mTask);
                myIntent.putExtra("infoList", mTaskInfo);
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
        switch (item.getItemId()){
            case R.id.itemMain:
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                Toast.makeText(getApplicationContext(),"Home selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemAdd:
                startActivity(new Intent(MainActivity.this,AddActivity.class));
                Toast.makeText(getApplicationContext(),"Home selected",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

