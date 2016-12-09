package agorbahn.android_project.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import agorbahn.android_project.Constants;
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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mLocation;
    private String mplace;
    private String mUser;


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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mLocation = mSharedPreferences.getString(Constants.PLACE, null);
        mEditor = mSharedPreferences.edit();
        mButton.setOnClickListener(this);

        mUser = FirebaseAuth.getInstance()
                .getCurrentUser()
                .getDisplayName();

        setTitle("Welcome back " + mUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mplace = query.toLowerCase();
                addToSharedPreferences(mplace);
                doctorLink();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
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
            case R.id.itemFav:
                myIntent = new Intent(MainActivity.this, SaveDoctorActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemLogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (mLocation != null) {
            doctorLink();
        } else {
            formValidation();
        }
    }

    private void formValidation() {
        String output = "";
        if (mEdit.getText().toString().equals("")) {
            output += "Need to add City\n";
        }
        if (mState.getSelectedItem().toString().equals("Pick a State")) {
            output += "Need to pick state";
        }
        if (output.equals("")) {
            mplace = codes[mState.getSelectedItemPosition()].toLowerCase() + "-" + mEdit.getText().toString().toLowerCase();
            addToSharedPreferences(mplace);
            doctorLink();
        } else {
            Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();
        }
    }

    private void doctorLink() {
        Intent myIntent = new Intent(MainActivity.this, DoctorActivity.class);
        myIntent.putExtra("place", mplace);
        startActivity(myIntent);
    }

    private void addToSharedPreferences(String place) {
        mEditor.putString(Constants.PLACE, place).apply();
    }

}

