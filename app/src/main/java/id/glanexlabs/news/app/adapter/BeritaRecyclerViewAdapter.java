package id.glanexlabs.news.app.adapter;

import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import com.squareup.picasso.*;
import id.glanexlabs.news.app.*;
import id.glanexlabs.news.app.holder.*;
import id.glanexlabs.news.app.model.*;
import id.glanexlabs.news.app.utils.*;
import java.util.*;

public class BeritaRecyclerViewAdapter extends RecyclerView.Adapter<BeritaViewHolder>
{
	private Context context;
	private ArrayList<BeritaModel> listData;
	private OnBeritaDiKlik listener;
	
	public void setOnBeritaDiKlik(OnBeritaDiKlik listener){
		this.listener = listener;
	}
	
	public BeritaRecyclerViewAdapter(Context context, ArrayList<BeritaModel> listData){
		this.context = context;
		this.listData = listData;
	}
	
	@Override
	public BeritaViewHolder onCreateViewHolder(ViewGroup parent, int typeView)
	{
		// TODO: Implement this method
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
		BeritaViewHolder holder = new BeritaViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(BeritaViewHolder holder, final int position)
	{
		// TODO: Implement this method
		final BeritaModel berita = listData.get(position);
		try {
		    Picasso.get().load(berita.getUrlToImage()).into(holder.image);
		} catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		holder.title.setText(berita.getTitle());
		holder.publishedAt.setText(TimeUnits.getTimeAgo(berita.getPublishedAt()));
		
		//set listener when news clicked
		holder.view.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				listener.onBeritaDiKlik(berita.getUrl());
			}
		});
		
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return listData.size();
	}
	
	public static interface OnBeritaDiKlik {
		void onBeritaDiKlik(String url);
	}
}
