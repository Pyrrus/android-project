package agorbahn.android_project.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import agorbahn.android_project.R;

import agorbahn.android_project.helpers.FontManager;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    @Bind(R.id.spinner) Spinner mState;
    @Bind(R.id.City) EditText mEdit;
    @Bind(R.id.findDoctor) Button mButton;
    private String[] states;
    private String[] codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();

        states = getResources().getStringArray(R.array.states_array);
        codes = getResources().getStringArray(R.array.state_postal);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, states);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mState.setAdapter(dataAdapter);

        mButton.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));

        mButton.setOnClickListener(this);

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
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String output = "";
        if (mEdit.getText().toString().equals("")) {
            output += "Need to add City\n";
        }

        if (mState.getSelectedItem().toString().equals("Pick a State")) {
            output += "Need to pick state";
        }

        if (output.equals("")) {
            Intent myIntent = new Intent(MainActivity.this, DoctorActivity.class);
            myIntent.putExtra("place", codes[mState.getSelectedItemPosition()].toLowerCase() + "-" + mEdit.getText().toString().toLowerCase());
            startActivity(myIntent);
        } else {
            Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();
        }
    }
}

