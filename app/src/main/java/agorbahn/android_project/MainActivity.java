package agorbahn.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

