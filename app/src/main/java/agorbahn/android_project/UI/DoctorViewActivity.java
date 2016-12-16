package agorbahn.android_project.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import agorbahn.android_project.Constants;
import agorbahn.android_project.R;
import agorbahn.android_project.helpers.FontManager;
import agorbahn.android_project.models.Doctor;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorViewActivity extends AppCompatActivity implements View.OnClickListener  {
    @Bind(R.id.address) TextView mAddress;
    @Bind(R.id.addressNumber) TextView mAddressNumber;
    @Bind(R.id.name) TextView mName;
    @Bind(R.id.phone) TextView mPhone;
    @Bind(R.id.phoneNumber) TextView mPhoneNumber;
    @Bind(R.id.INFO) TextView mINFO;
    @Bind(R.id.saveButton) Button saveButton;
    private Doctor mDoctor;
    int at;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private FirebaseUser user;
    private String uid;
    private  DatabaseReference doctorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);
        Intent myIntent = getIntent();
        mDoctor =  Parcels.unwrap(getIntent().getParcelableExtra("doctor"));
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mName.setText(mDoctor.getName());
        mINFO.setText(mDoctor.getBio());
        mPhoneNumber.setText( mDoctor.getPhone().get(0));
        mAddressNumber.setText(mDoctor.getStreet() + " " + mDoctor.getStreet2() + "\n" + mDoctor.getCity() + ", " + mDoctor.getState() + ", " + mDoctor.getZip());

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        doctorRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.DOCTOR_SAVE).child(uid);

        mPhone.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));
        mAddress.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));
        saveButton.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));

        doctorRef.orderByChild("name").equalTo(mDoctor.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    saveButton.setText(R.string.fa_heart_o);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mPhoneNumber.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPhone || v == mPhoneNumber) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mDoctor.getPhone().get(0)));
            startActivity(phoneIntent);
        }
        if (v == saveButton) {

            doctorRef.orderByChild("name").equalTo(mDoctor.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        DatabaseReference Ref = doctorRef.getRef();
                        Ref.child(mDoctor.getPushID()).removeValue();
                        mDoctor.setPushID(null);
                        saveButton.setText(R.string.fa_heart);
                        Toast.makeText(DoctorViewActivity.this, "Remove", Toast.LENGTH_SHORT).show();
                    } else {
                        DatabaseReference pushRef = doctorRef.push();
                        String pushId = pushRef.getKey();
                        mDoctor.setPushID(pushId);
                        pushRef.setValue(mDoctor);
                        saveButton.setText(R.string.fa_heart_o);
                        Toast.makeText(DoctorViewActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
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
                myIntent = new Intent(DoctorViewActivity.this, MainActivity.class);
                addToSharedPreferences("");
                startActivity(myIntent);
                return true;
            case R.id.itemAbout:
                myIntent = new Intent(DoctorViewActivity.this, AboutActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemFav:
                myIntent = new Intent(DoctorViewActivity.this, SaveDoctorActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.itemLogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DoctorViewActivity.this, LoginActivity.class);
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
