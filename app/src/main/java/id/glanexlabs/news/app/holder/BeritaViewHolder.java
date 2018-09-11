package id.glanexlabs.news.app.holder;

import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import id.glanexlabs.news.app.*;

public class BeritaViewHolder extends RecyclerView.ViewHolder
{
	public ImageView image;
	public TextView title;
	public TextView publishedAt;
	public View view;
	
	public BeritaViewHolder(View view){
		super(view);
		
		image = (ImageView)view.findViewById(R.id.image);
		title = (TextView)view.findViewById(R.id.title);
		publishedAt = (TextView)view.findViewById(R.id.publishedAt);
		
		this.view = view;
	}
}
