package com.example.devourery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    public void onSeria(MenuItem item){
        Toast.makeText(this, "CWS:" + GameManager.currentSeria, Toast.LENGTH_SHORT).show();
    }

    public void onMaxSeria(MenuItem item){
        Toast.makeText(this, "MWS:" + GameManager.maxSeria, Toast.LENGTH_SHORT).show();
    }

    public void onAbout(MenuItem item){
        Toast.makeText(this, "Андреенко Денис", Toast.LENGTH_SHORT).show();
    }

    public void onSite(MenuItem item){
        Toast.makeText(this, "https://vk.com/id179523974", Toast.LENGTH_LONG).show();
    }

    public void onExit(MenuItem item){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
