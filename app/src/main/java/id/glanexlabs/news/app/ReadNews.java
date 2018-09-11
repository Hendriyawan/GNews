package id.glanexlabs.news.app;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.preference.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import id.glanexlabs.news.app.utils.*;

import android.support.v7.widget.Toolbar;
import id.glanexlabs.news.app.utils.ThemeUtils;

public class ReadNews extends AppCompatActivity
{
	private ProgressBar mProgressBar;
	private WebView mWebView;
	
	String url = "https://www.google.com";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//android theme
		ThemeUtils.createTheme(this);
		setContentView(R.layout.layout_read_news);
		
		//android toolbar
		setupToolbar(R.id.toolbar);
	
		
		mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
		mProgressBar.setMax(100);
		
		url = getIntent().getStringExtra("url");
		
		mWebView = (WebView)findViewById(R.id.read_news);
		//mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl(url);
		mProgressBar.setProgress(0);
		mWebView.setWebViewClient(new WebViewClient(){
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String newUrl){
				view.loadUrl(newUrl);
				mProgressBar.setProgress(0);
				return true;
			}
			
			@Override
			public void onPageStarted(WebView view, String urlStart, Bitmap favicon){
				//mProgressBar.setProgress(0);
				url = urlStart;
				invalidateOptionsMenu();
				
			}
			
			@Override
			public void onPageFinished(WebView view, String urlPage){
				mProgressBar.setVisibility(View.GONE);
				invalidateOptionsMenu();
			}
		});
	}
	
	//android optionsMenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu_read_news, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.share:
				//share news
				shareUrl(url);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//back pressed
	@Override
	public void onBackPressed(){
		if(mWebView.canGoBack()){
			mWebView.goBack();
		} else {
			super.onBackPressed();
		}
	}
	
	//setup toolbar
	private void setupToolbar(int id){
		Toolbar toolbar = (Toolbar)findViewById(id);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(mWebView.canGoBack()){
					mWebView.goBack();
				} else {
					finish();
				}
			}
		});
	}
	
	//share news 
	private void shareUrl(String url){
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain");
		share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		share.putExtra(Intent.EXTRA_TEXT, url);
		startActivity(Intent.createChooser(share, "share to"));
	}
}
