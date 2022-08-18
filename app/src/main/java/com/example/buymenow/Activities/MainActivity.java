package com.example.buymenow.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.buymenow.Fragments.CartFragment;
import com.example.buymenow.Fragments.HomeFragment;
import com.example.buymenow.Fragments.SettingsFragment;
import com.example.buymenow.R;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private ChipNavigationBar chipNavigation;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigation = findViewById(R.id.chipNavigation);

        chipNavigation.setItemSelected(R.id.navHome , true);

        getSupportFragmentManager().beginTransaction().replace(R.id.container , new HomeFragment()).commit();

        chipNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.navHome:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navSetting:
                        SharedPreferences.Editor editor = getSharedPreferences("PREFS" , MODE_PRIVATE).edit();
                        editor.putString("AccountId" , FirebaseAuth.getInstance().getCurrentUser().getUid());
                        editor.apply();
                        fragment = new SettingsFragment();
                        break;
                    case R.id.navCart:
                        fragment = new CartFragment();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment).commit();
            }
        });

    }
}