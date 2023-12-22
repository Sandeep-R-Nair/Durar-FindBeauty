package com.durar.findbeauty.view;

import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.durar.findbeauty.AppController;
import com.durar.findbeauty.R;
import com.durar.findbeauty.databinding.ActivityMainBinding;
import com.durar.findbeauty.utils.ConnectivityReceiver;
import com.durar.findbeauty.view.home.HomeFragment;
import com.durar.findbeauty.view.menu.MenuFragment;
import com.durar.findbeauty.view.notifications.NotificationsFragment;
import com.durar.findbeauty.view.reports.ReportsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    private ActivityMainBinding mainBinding;
    private int flag=0;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        int itemId = item.getItemId();
        Fragment selectedFragment = null;

        if (itemId == R.id.navigation_home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.navigation_reports) {
            selectedFragment = new ReportsFragment();
        } else if (itemId == R.id.navigation_notifications) {
            selectedFragment = new NotificationsFragment();
        } else if (itemId == R.id.navigation_menu) {
            selectedFragment = new MenuFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }

        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Set the default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

        registerReceiver(new ConnectivityReceiver(),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color,txtcolor= Color.WHITE;
        if (isConnected) {
            message = "We are back...";
            color = ContextCompat.getColor(getApplicationContext(), R.color.green);

            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.mainlayout), message, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(color);
            if(flag==1)
                snackbar.show();
        } else {
            message = "Please connect to internet.";
            color = ContextCompat.getColor(getApplicationContext(), R.color.red);
            flag=1;

            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.mainlayout), message, Snackbar.LENGTH_INDEFINITE);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(color);
            if(flag==1)
                snackbar.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
