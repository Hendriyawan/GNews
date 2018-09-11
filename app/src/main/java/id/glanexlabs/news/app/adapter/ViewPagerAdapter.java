package id.glanexlabs.news.app.adapter;
import android.support.v4.app.*;
import id.glanexlabs.news.app.*;

public class ViewPagerAdapter extends FragmentPagerAdapter
{
	private String[] TITLE = { "TERBARU", "TEKNOLOGI", "BISNIS", "OLAHRAGA", "KESEHATAN", "HIBURAN" };
	
	public ViewPagerAdapter(FragmentManager fragmentManager){
		super(fragmentManager);
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return TITLE.length;
	}

	@Override
	public Fragment getItem(int position)
	{
		// TODO: Implement this method
		switch(position){
			case 0:
				return new FragmentBeritaTerbaru();
				
			case 1:
				return new FragmentBeritaTeknologi();
				
			case 2:
				return new FragmentBeritaBisnis();
				
			case 3:
				return new FragmentBeritaOlahraga();
				
			case 4:
				return new FragmentBeritaKesehatan();
				
			case 5:
				return new FragmentBeritaHiburan();
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		// TODO: Implement this method
		switch(position){
			case 0:
				return TITLE[0];
			case 1:
				return TITLE[1];
			case 2:
				return TITLE[2];
			case 3:
				return TITLE[3];
			case 4:
				return TITLE[4];
			case 5:
				return TITLE[5];
		}
		return super.getPageTitle(position);
	}
	
}
