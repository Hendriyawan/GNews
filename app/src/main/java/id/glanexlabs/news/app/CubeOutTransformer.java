package id.glanexlabs.news.app;
import android.view.View.*;
import android.view.*;

public class CubeOutTransformer extends BaseTransformer
{

	@Override
	protected void onTransform(View view, float position)
	{
		// TODO: Implement this method
		view.setPivotX(position < 0.5f ? view.getWidth() : 0f);
		view.setPivotY(view.getHeight() * 0.5f);
		view.setRotationY(90f * position);
	}

	@Override
	protected boolean isPagingEnabled()
	{
		// TODO: Implement this method
		return true;
	}
}
