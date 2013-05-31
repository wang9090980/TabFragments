package net.libsoftigran.tabfragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * Superclass for activities containing tabs.
 * */
public class TabContainer extends FragmentActivity {
	
	protected TabManager tabManager;
	protected ArrayList<View> tabs;
	protected Fragment tabContent;
	protected int containerId;
	
	@Override
	protected void onCreate(Bundle data) {
		super.onCreate(data);
		tabs = new ArrayList<View>();
		tabManager = new TabManager();
	}
	
	public void addTabClickListeners()
	{
		for(int i = 0; i < tabs.size(); i++)
		{
			final int index = i;
			tabs.get(i).setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					if(tabManager.getCurrentTabIndex() == index)
						return;
					switchTab(index);
				}
			});
		}
	}
	
	/**
	 * Switches tab with specified number (starts at 0)
     * Removes current tab and keeps blank tab container if no tab with given index was found.
	 */
	public void switchTab(int index)
	{
		int currentIndex = tabManager.getCurrentTabIndex();
		Fragment currentFragment = tabManager.getCurrentVisibleFragment();
		if(currentFragment != null)
			getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();

		Fragment fragment = tabManager.switchTab(index);
		if(fragment == null)
			return;
		
		getSupportFragmentManager().beginTransaction().replace(containerId, fragment).commit();
		if(currentIndex >= 0)
			this.tabs.get(currentIndex).setSelected(false);
		this.tabs.get(index).setSelected(true);
	}

    /**
     * Adds a fragment to current tab's fragments queue.
     * @param fragment fragment to be added.
     * */
	public void addFragment(TabFragment fragment)
	{
		if(fragment == null)
			return;

		getSupportFragmentManager().beginTransaction().hide(tabManager.getCurrentVisibleFragment()).add(containerId, fragment).commit();
		tabManager.addFragment(fragment);
	}

    /**
     * This method will be called when back button is pressed on first fragment of the tab.
     * Subclass should override this method for custom behaviour.
     * */
	protected void onFirstFragmentBackPressed()
	{
        //make a toast for example
		//super.onBackPressed();
	}

    /**
     * Overrided method of activity.
     * Previous fragment is shown instead of standard back logic of android.
     * */
	@Override
	public void onBackPressed()
	{		
		TabFragment current = tabManager.popFragment();
		if(current == null)
		{
			onFirstFragmentBackPressed();
			return;
		}
		TabFragment fragment = tabManager.getCurrentVisibleFragment();
		fragment.update(current.getReturnData());

		getSupportFragmentManager().beginTransaction().remove(current).commit();
		getSupportFragmentManager().beginTransaction().show(fragment).commit();
	}

    /**
     * Adds a tab to tabs.
     * @param v switcher button for tab.
     * @param fragment first fragment of new tab.
     * */
	public void addTab(View v, TabFragment fragment)
	{
		tabs.add(v);
		tabManager.addTab(fragment);
	}

    /**
     * Overrided method of activity.
     * This will be called when another activity was started for result from one of fragments.
     * Note that startActivityForResult must be called for this activity (fragment's parent activity), not fragment. Otherwise onActivityResul of fragment will be called.
     * Updates fragment for data from activity result.*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//super.onActivityResult(requestCode, resultCode, data);
		Bundle params = (data != null && data.getExtras() != null) ? data.getExtras() : new Bundle();
		params.putInt("requestCode", requestCode);
		params.putInt("resultCode", resultCode);
		tabManager.getCurrentVisibleFragment().update(params, data == null ? null : data.getData());
	}
}