package id.glanexlabs.news.app;
import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import id.glanexlabs.news.app.adapter.*;
import id.glanexlabs.news.app.utils.*;
import java.util.*;

import id.glanexlabs.news.app.utils.ThemeUtils;

public class MainActivity extends AppCompatActivity 
{
	private Toolbar mToolbar;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private DrawerLayout mDrawerLayot; 
	private NavigationView mNavigationView;
	private ViewPagerAdapter mViewPagerAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		//create theme
		ThemeUtils.createTheme(this);
        setContentView(R.layout.main);
		
		//android toolbar
		setupToolbar(R.id.toolbar);
		
		//ViewPager & TabLayout
		mViewPager = (ViewPager)findViewById(R.id.view_pager);
		mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
		mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		mViewPager.setPageTransformer(true, new CubeOutTransformer());
		mViewPager.setAdapter(mViewPagerAdapter);
		
		mTabLayout.addTab(mTabLayout.newTab().setText("TERBARU"));
		mTabLayout.addTab(mTabLayout.newTab().setText("TEKNOLOGI"));
		mTabLayout.addTab(mTabLayout.newTab().setText("BISNIS"));
		mTabLayout.addTab(mTabLayout.newTab().setText("OLAHRAGA"));
		mTabLayout.addTab(mTabLayout.newTab().setText("KESEHATAN"));
		mTabLayout.addTab(mTabLayout.newTab().setText("HIHURAN"));
		//setup with view pager
		mTabLayout.setupWithViewPager(mViewPager);
		
		mDrawerLayot = (DrawerLayout)findViewById(R.id.drawer_layout);
		mNavigationView = (NavigationView)findViewById(R.id.nav_view);
		mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
			@Override
			public boolean onNavigationItemSelected(MenuItem item){
				if(item.isChecked())
					item.setChecked(false);
				else
					item.setChecked(true);
					mDrawerLayot.closeDrawers();

					switch(item.getItemId()){
						case R.id.terbaru:
							mViewPager.setCurrentItem(0);
							break;
							
						case R.id.teknologi:
							mViewPager.setCurrentItem(1);
							break;
							
						case R.id.bisnis:
							mViewPager.setCurrentItem(2);
							break;
							
						case R.id.olahraga:
							mViewPager.setCurrentItem(3);
							break;
							
						case R.id.kesehatan:
							mViewPager.setCurrentItem(4);
							break;
							
						case R.id.hiburan:
							mViewPager.setCurrentItem(5);
							break;
							
						case R.id.about:
							AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
							builder.setTitle("Tentang Aplikasi");
							builder.setMessage("GNews adalah aplikasi berita sederhana menggunakan API https://newsapi.org");
							builder.setNegativeButton("KEMBALI", new DialogInterface.OnClickListener(){
								@Override
								public void onClick(DialogInterface dialog, int id){
									dialog.cancel();
								}
							});
							builder.show();
					}
				return false;
			}
		});
		
		
    }
	
	//android OptionsMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.invert_colors:
				if(ThemeUtils.isThemeLight(this)){
					ThemeUtils.setTheme(this, false, "dark");
					recreateActivity();
				}
				else {
					ThemeUtils.setTheme(this, true, "light");
					recreateActivity();
				}
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//android setup toolbar
	private void setupToolbar(int id){
		mToolbar = (Toolbar)findViewById(id);
		setSupportActionBar(mToolbar);
	}
	
	//recreate activity
	private void recreateActivity(){
		Intent i = getIntent();
		this.finish();
		this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		startActivity(i);
	}
}
