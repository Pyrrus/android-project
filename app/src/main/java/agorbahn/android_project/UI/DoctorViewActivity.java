package agorbahn.android_project.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);
        Intent myIntent = getIntent();
        mDoctor =  Parcels.unwrap(getIntent().getParcelableExtra("doctor"));
        ButterKnife.bind(this);

        mName.setText(mDoctor.getName());
        mINFO.setText(mDoctor.getBio());
        mPhoneNumber.setText( mDoctor.getPhone().get(0));
        mAddressNumber.setText(mDoctor.getStreet() + " " + mDoctor.getStreet2() + "\n" + mDoctor.getCity() + ", " + mDoctor.getState() + ", " + mDoctor.getZip());

        mPhone.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));
        mAddress.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));
        saveButton.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));

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
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.DOCTOR_SAVE);
            restaurantRef.push().setValue(mDoctor);
            Toast.makeText(DoctorViewActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
