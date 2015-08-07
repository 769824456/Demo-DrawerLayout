package cn.syl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * PACKAGE_NAME :cn.syl
 * VERSION :[V 1.0.0]
 * AUTHOR : yulongsun
 * CREATE AT : 2015/8/7
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE :
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_main)
    Toolbar toolbar;
    @Bind(R.id.fl_main_content)
    FrameLayout flMainContent;
    @Bind(R.id.lv_main_drawer)
    ListView lvMainDrawer;
    @Bind(R.id.btn_main_exit_app)
    Button btnMainExitApp;
    @Bind(R.id.ll_main_drawer_menu)
    LinearLayout llMainDrawerMenu;
    @Bind(R.id.drawerlayout_main)
    DrawerLayout drawerlayoutMain;
    private int mCurrentPosition = 0;
    private ArrayAdapter<String> mDrawerListAdapter;
    private ArrayList<String> leftMenuItems;
    private String mCurrenttitleStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initDrawerLayout();

    }


    private void initDrawerLayout() {
        //标题数据准备
        leftMenuItems = new ArrayList<String>();
        leftMenuItems.add("item1");
        leftMenuItems.add("item2");
        leftMenuItems.add("item3");
        leftMenuItems.add("item4");

        //初始化设置标题
        mCurrenttitleStr = leftMenuItems.get(mCurrentPosition);
        toolbar.setTitle(mCurrenttitleStr);


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerlayoutMain, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("DrawerLayout");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle(mCurrenttitleStr);
            }

        };

        mDrawerToggle.syncState();//Toolbar和DrawerLayout同步状态

        drawerlayoutMain.setDrawerListener(mDrawerToggle);

        mDrawerListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, leftMenuItems);
        lvMainDrawer.setAdapter(mDrawerListAdapter);

        lvMainDrawer.setItemChecked(mCurrentPosition, true);

        lvMainDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerlayoutMain.closeDrawer(llMainDrawerMenu);
                if (mCurrentPosition != position) {
                    mCurrenttitleStr = leftMenuItems.get(position).toString();
                    toolbar.setTitle(mCurrenttitleStr);

//                    mDrawerListView.setItemChecked(position, true);
//                    changeToSelectNoteType(position);
                    if (2 == position) {
                        Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                        startActivity(intent);
                    }
                    mCurrentPosition = position;
                }
            }
        });

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
