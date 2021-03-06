package com.example.msrit.ratethefarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class GetFarmerDetails extends AppCompatActivity {

    DatabaseReference mDatabase;
    UserData userData = new UserData();

    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_farmer_details);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);

        mDatabase.child("Current Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int userID = dataSnapshot.getValue(Integer.class);
                userID++;
                userData.setUserID(userID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Personal Details

        final EditText mName = findViewById(R.id.name);
        final EditText mVillage = findViewById(R.id.village);
        final EditText mTaluq = findViewById(R.id.taluq);
        final EditText mAge = findViewById(R.id.age);

        //Total no. of Workers

        final EditText mMaleWorkers = findViewById(R.id.workers_men);
        final EditText mFemaleWorkers = findViewById(R.id.workers_women);

        //Land Details

        final EditText mLandArea = findViewById(R.id.land_area);
        final RadioGroup rgLandType = findViewById(R.id.land_type);

        //Animals Owned

        final EditText mCow = findViewById(R.id.cow);
        final EditText mBuffalo = findViewById(R.id.buffalo);
        final EditText mCock = findViewById(R.id.cock);
        final EditText mHen = findViewById(R.id.hen);
        final EditText mSheep = findViewById(R.id.sheep);
        final EditText mGoat = findViewById(R.id.goat);
        final EditText mOtherAnimals = findViewById(R.id.others);

        //Sericulture

        final EditText mMulberryYield = findViewById(R.id.mulberry_yield);
        final RadioGroup rgMulberry = findViewById(R.id.sell_mulberry);

        //Machines Owned

        final CheckBox cTractor = findViewById(R.id.tractor);
        final CheckBox cPowerTiller = findViewById(R.id.power_tiller);
        final CheckBox cPlougher = findViewById(R.id.plougher);
        final CheckBox cRotomator = findViewById(R.id.rotomator);
        final CheckBox cBullock = findViewById(R.id.bullock_cart);
        final CheckBox cSprayer = findViewById(R.id.sprayer);
        final CheckBox cSprinkler = findViewById(R.id.sprinkler);

        //Crop Details

        final EditText mCrop = findViewById(R.id.crop_name);
        final EditText mCropArea = findViewById(R.id.crop_area);
        final EditText mCropYield = findViewById(R.id.crop_yield);

        //Miscellaneous

        final RadioGroup rgOnlineSales = findViewById(R.id.sell_online);

        final RadioGroup rgScientificSuggestions = findViewById(R.id.scientific_suggestions);

        final RadioGroup rgNursery = findViewById(R.id.own_nursery);

        final CheckBox cLocal = findViewById(R.id.sale_local);
        final CheckBox cAPMC = findViewById(R.id.sale_apmc);

        final EditText mPesticidesUsed = findViewById(R.id.pesticides_used);
        final EditText mFertilizersUsed = findViewById(R.id.fertilizers_used);

        Button mSubmitBtn = findViewById(R.id.submit);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String mEmail = Objects.requireNonNull(user).getEmail();

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userData.setEmail(mEmail);

                if (mName.getText().toString().matches("")) {
                    mName.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setName(mName.getText().toString());
                }


                if (mVillage.getText().toString().matches("")) {
                    mVillage.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setVillage(mVillage.getText().toString());
                }

                userData.setTaluq(mTaluq.getText().toString());


                if (mAge.getText().toString().matches("")) {
                    mAge.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setAge(Integer.parseInt(mAge.getText().toString()));
                }

                if (TextUtils.isEmpty(mMaleWorkers.getText().toString())) {
                    mMaleWorkers.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setMaleWorkers(Integer.parseInt(mMaleWorkers.getText().toString()));
                }

                if (mFemaleWorkers.getText().toString().matches("")) {
                    mFemaleWorkers.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setFemaleWorkers(Integer.parseInt(mFemaleWorkers.getText().toString()));
                }

                if (mLandArea.getText().toString().matches("")) {
                    mLandArea.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setLand(Double.parseDouble(mLandArea.getText().toString()));
                }

                int SelectedID = rgLandType.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(SelectedID);
                if (radioButton == null) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setIrrigatedOrRainfed(radioButton.getText().toString());
                }

                if (mCow.getText().toString().matches("")) {
                    mCow.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setCow(Integer.parseInt(mCow.getText().toString()));
                }

                if (mBuffalo.getText().toString().matches("")) {
                    mBuffalo.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setBuffalo(Integer.parseInt(mBuffalo.getText().toString()));
                }

                if (mCock.getText().toString().matches("")) {
                    mCock.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setCock(Integer.parseInt(mCock.getText().toString()));
                }

                if (mHen.getText().toString().matches("")) {
                    mHen.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setHen(Integer.parseInt(mHen.getText().toString()));
                }

                if (mSheep.getText().toString().matches("")) {
                    mSheep.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setSheep(Integer.parseInt(mSheep.getText().toString()));
                }

                if (mGoat.getText().toString().matches("")) {
                    mGoat.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setGoat(Integer.parseInt(mGoat.getText().toString()));
                }

                if (mOtherAnimals.getText().toString().matches("")) {
                    mOtherAnimals.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setOtherAnimals(Integer.parseInt(mOtherAnimals.getText().toString()));
                }

                if (mMulberryYield.getText().toString().matches("")) {
                    mMulberryYield.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setMulberryYield(Double.parseDouble(mMulberryYield.getText().toString()));
                }


                SelectedID = rgMulberry.getCheckedRadioButtonId();
                if (findViewById(SelectedID) == null) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    radioButton = findViewById(SelectedID);
                    String Value = radioButton.getText().toString();
                    if (Value.equals("Yes")) {
                        userData.setSellMulberry(true);
                    } else {
                        userData.setSellMulberry(false);
                    }
                }

                if (cTractor.isChecked()) {
                    userData.setOwnsTractor(true);
                }
                if (cPowerTiller.isChecked()) {
                    userData.setOwnsPowerTiller(true);
                }
                if (cPlougher.isChecked()) {
                    userData.setOwnsPlougher(true);
                }
                if (cRotomator.isChecked()) {
                    userData.setOwnsRotomator(true);
                }
                if (cBullock.isChecked()) {
                    userData.setOwnsBullockCart(true);
                }
                if (cSprayer.isChecked()) {
                    userData.setOwnsSprayer(true);
                }
                if (cSprinkler.isChecked()) {
                    userData.setOwnsSprinkler(true);
                }

                if (mCrop.getText().toString().matches("")) {
                    mCrop.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setCropName(mCrop.getText().toString());
                }

                if (mCropArea.getText().toString().matches("")) {
                    mCropArea.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setCropArea(Double.parseDouble(mCropArea.getText().toString()));
                }

                if (mCropYield.getText().toString().matches("")) {
                    mCropYield.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userData.setCropYield(Double.parseDouble(mCropYield.getText().toString()));
                }

                SelectedID = rgOnlineSales.getCheckedRadioButtonId();

                if (findViewById(SelectedID)==null) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    radioButton = findViewById(SelectedID);
                    String Value = radioButton.getText().toString();
                    if (Value.equals("Yes")) {
                        userData.setOnlineSale(true);
                    } else {
                        userData.setOnlineSale(false);
                    }
                }

                SelectedID = rgScientificSuggestions.getCheckedRadioButtonId();
                if (findViewById(SelectedID)==null) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    radioButton = findViewById(SelectedID);
                    String Value = radioButton.getText().toString();
                    if (Value.equals("Yes")) {
                     userData.setScientificSuggestions(true);
                    } else {
                      userData.setScientificSuggestions(false);
                    }
                 }

                SelectedID = rgNursery.getCheckedRadioButtonId();

                if (findViewById(SelectedID)==null) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    radioButton = findViewById(SelectedID);
                    String Value = radioButton.getText().toString();
                    if (Value.equals("Yes")) {
                        userData.setOwnsNursery(true);
                    } else {
                        userData.setOwnsNursery(false);
                    }
                }

                if(cLocal.isChecked()){
                    userData.setSalesLocal(true);
                }

                if(cAPMC.isChecked()){
                    userData.setSalesAPMC(true);
                }

                if (mPesticidesUsed.getText().toString().matches("")) {
                    mPesticidesUsed.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setPesticidesUsed(mPesticidesUsed.getText().toString());
                }


                if (mFertilizersUsed.getText().toString().matches("")) {
                    mFertilizersUsed.setError("Field cannot be empty");
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    userData.setFertilizersUsed(mFertilizersUsed.getText().toString());
                }



                userData.setCalculatedValues();


                mDatabase.child("Users").child(Integer.toString(userData.getUserID())).setValue(userData);
                mDatabase.child("Current Users").setValue(userData.getUserID());


                Toast.makeText(GetFarmerDetails.this, "Response Submitted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), FarmersList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();
            }
        });
    }


    //Logout Button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_button_get_farmer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent myIntent = new Intent(this, LoginAndSignUp.class);
            startActivity(myIntent);

            Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {


        Toast.makeText(this, "Failed to create farmer profile", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), FarmersList.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}