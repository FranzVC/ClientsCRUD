package com.e.crud_sqlite;

import android.net.Uri;
import android.os.Bundle;

import com.e.crud_sqlite.fragment.DeleteFragment;
import com.e.crud_sqlite.fragment.RegisterFragment;
import com.e.crud_sqlite.fragment.SearchFragment;
import com.e.crud_sqlite.fragment.UpdateFragment;
import com.e.crud_sqlite.fragment.ViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
                                                    RegisterFragment.OnFragmentInteractionListener,
                                                    ViewFragment.OnFragmentInteractionListener,
                                                    DeleteFragment.OnFragmentInteractionListener,
                                                    SearchFragment.OnFragmentInteractionListener,
                                                    UpdateFragment.OnFragmentInteractionListener{
    private RegisterFragment registerFragment;
    private UpdateFragment updateFragment;
    private DeleteFragment deleteFragment;
    private SearchFragment searchFragment;
    private ViewFragment viewFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_register:
                    fragmentTransaction.replace(R.id.fragmentContainer, registerFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_delete:
                    fragmentTransaction.replace(R.id.fragmentContainer, deleteFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_show:
                    fragmentTransaction.replace(R.id.fragmentContainer, viewFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_search:
                    fragmentTransaction.replace(R.id.fragmentContainer, searchFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_update:
                    fragmentTransaction.replace(R.id.fragmentContainer, updateFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        registerFragment = new RegisterFragment();
        updateFragment = new UpdateFragment();
        deleteFragment = new DeleteFragment();
        searchFragment = new SearchFragment();
        viewFragment = new ViewFragment();
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, registerFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
