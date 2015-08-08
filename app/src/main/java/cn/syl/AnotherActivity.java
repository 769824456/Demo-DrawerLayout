package cn.syl;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AnotherActivity extends AppCompatActivity {

    @Bind(R.id.another)
    Toolbar anotherToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(anotherToolbar);
        anotherToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        //添加返回键
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_another, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        //这边要特别注意：是android.R.id.home不是R.id.home

        if (id == android.R.id.home) {
            finish();
            Log.e("AnotherActivity", "home");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
