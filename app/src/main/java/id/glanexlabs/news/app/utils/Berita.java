package id.glanexlabs.news.app.utils;

import android.app.*;
import android.content.*;
import android.support.v7.widget.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import id.glanexlabs.news.app.*;
import id.glanexlabs.news.app.adapter.*;
import id.glanexlabs.news.app.model.*;
import java.util.*;
import org.json.*;

public class Berita
{
	
	public static void load(final Context context, final RecyclerView recyclerView, final String url){
		final ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("Harap Tunggu...");
		progressDialog.setIndeterminate(false);
		progressDialog.show();
		
		RequestQueue queue = Volley.newRequestQueue(context);
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
			@Override
			public void onResponse(String response){
				progressDialog.dismiss();
				
				ArrayList<BeritaModel> listData = new ArrayList<>();
				try {
					JSONObject json = new JSONObject(response);
					JSONArray array = json.getJSONArray("articles");
					
					for (int i = 0; i < array.length(); i++){
						JSONObject value = array.getJSONObject(i);
						BeritaModel berita = new BeritaModel(value.getString("title"), value.getString("publishedAt"), value.getString("url"), value.getString("urlToImage"));
						listData.add(berita);
						
						BeritaRecyclerViewAdapter adapter = new BeritaRecyclerViewAdapter(context, listData);
						adapter.setOnBeritaDiKlik(new BeritaRecyclerViewAdapter.OnBeritaDiKlik(){
							@Override
							public void onBeritaDiKlik(String url){
								//Toast.makeText(context, url, Toast.LENGTH_LONG).show();
								Intent intent = new Intent(context, ReadNews.class);
								intent.putExtra("url", url);
								context.startActivity(intent);
							}
						});
						recyclerView.setAdapter(adapter);
					}
				}
				catch (JSONException e){
					//handle exception
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener(){
			@Override
			public void onErrorResponse(VolleyError error){
				if(error instanceof NetworkError){
					progressDialog.dismiss();
					//network error
					Intent intent = new Intent();
					intent.setAction("NETWORK_ERROR");
					intent.putExtra("error", "Koneksi tidak tersedia !");
					context.sendBroadcast(intent);
				}
				else if(error instanceof TimeoutError){
					progressDialog.dismiss();
					Intent intent = new Intent();
					intent.setAction("CONNECTION_TIMEOUT");
					intent.putExtra("error", "Koneksi timeout !");
					context.sendBroadcast(intent);
				}
				else if(error instanceof NoConnectionError){
					progressDialog.dismiss();
					Intent intent = new Intent();
					intent.setAction("NO_CONNECTION");
					intent.putExtra("error", "Koneksi tidak tersedia !");
					context.sendBroadcast(intent);
				}
			}
		});
		queue.add(stringRequest);
	}
}
