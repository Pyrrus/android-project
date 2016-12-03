package agorbahn.android_project.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

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
    private ArrayList<Doctor> mDoctor = new ArrayList<Doctor>();
    int at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);
        Intent myIntent = getIntent();
        mDoctor = Parcels.unwrap(getIntent().getParcelableExtra("doctor"));
        ButterKnife.bind(this);
        at = myIntent.getExtras().getInt("at");

        mName.setText(mDoctor.get(at).getName());
        mINFO.setText(mDoctor.get(at).getBio());
        mPhoneNumber.setText( mDoctor.get(at).getPhone().get(0));
        mAddressNumber.setText(mDoctor.get(at).getStreet() + " " + mDoctor.get(at).getStreet2() + "\n" + mDoctor.get(at).getCity() + ", " + mDoctor.get(at).getState() + ", " + mDoctor.get(at).getZip());

        mPhone.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));
        mAddress.setTypeface(FontManager.getTypeface(this,"fontawesome-webfont.ttf"));

        mPhoneNumber.setOnClickListener(this);
        mPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + mDoctor.get(at).getPhone().get(0)));
        startActivity(phoneIntent);
    }
}
