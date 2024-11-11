package com.example.purchaselist.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.purchaselist.R;
import com.example.purchaselist.fragment.ListsFragment;
import com.example.purchaselist.service.MyNotificationManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // создаём канал для уведомлений
        MyNotificationManager.createNotificationChannel(this);

        // начальная установка фрагмента со списком "списка"
        ListsFragment listFrg = new ListsFragment();
        setContFragment(listFrg);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void setContFragment(Fragment frg) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, frg)
                .addToBackStack(null)
                .commit();
    }
}