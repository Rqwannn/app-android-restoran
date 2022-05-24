package com.example.myapplication.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.LihatMakanan;
import com.example.myapplication.LihatMinuman;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class DashbordMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_menu);
    }

    public void TambahMenu(View view) {
        Intent intent = new Intent(DashbordMenu.this, TambahMenu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
    }

    public void LihatMakanan(View view) {
        Intent intent = new Intent(DashbordMenu.this, LihatMakanan.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
    }

    public void LihatMinuman(View view) {
        Intent intent = new Intent(DashbordMenu.this, LihatMinuman.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
    }
}