package id.glanexlabs.news.app;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import id.glanexlabs.news.app.utils.*;

public class FragmentBeritaOlahraga extends Fragment
{
	private RecyclerView mRecyclerView;
	private LinearLayout mLinearLayout;
	private TextView mTextView;
	private BroadcastReceiver receiver;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.layout_berita_olahraga, container, false);
		
		mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		
		mLinearLayout = (LinearLayout)v.findViewById(R.id.notification_layout);
		mTextView = (TextView)v.findViewById(R.id.text_notification);

		IntentFilter filter = new IntentFilter();
		filter.addAction("NETWORK_ERROR");
		filter.addAction("CONNECTION_TIMEOUT");
		filter.addAction("NO_CONNECTION");

		receiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent){

				if(intent.getAction().equals("NETWORK_ERROR")){
					mTextView.setText(intent.getStringExtra("error"));
					if(mLinearLayout.getVisibility() == View.GONE){
						mLinearLayout.setVisibility(View.VISIBLE);
					}
				}

				if(intent.getAction().equals("CONNECTION_TIMEOUT")){
					mTextView.setText(intent.getStringExtra("error"));
					if(mLinearLayout.getVisibility() == View.GONE){
						mLinearLayout.setVisibility(View.VISIBLE);
					}
				}

				if(intent.getAction().equals("NO_CONNECTION")){
					mTextView.setText(intent.getStringExtra("error"));
					if(mLinearLayout.getVisibility() == View.GONE){
						mLinearLayout.setVisibility(View.VISIBLE);
					}
				}
			}
		};
		getActivity().registerReceiver(receiver, filter);
		
		Berita.load(getActivity(), mRecyclerView, NewsApi.GET_CATEGORY_SPORTS);
		return v;
	}

	@Override
	public void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		getActivity().unregisterReceiver(receiver);
	}
}
