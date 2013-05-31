package net.libsoftigran.tabfragments;

import java.util.ArrayList;
import java.util.Stack;

import android.support.v4.app.Fragment;

public class TabManager {

	private ArrayList<Stack<TabFragment>> stacks;
	private int currentTabIndex;
	
	public TabManager()
	{
		stacks = new ArrayList<Stack<TabFragment>>();
		currentTabIndex = -1;
	}
	
	public int getCurrentTabIndex()
	{
		return currentTabIndex;
	}

	/**
	 * Adds a new tab with given default fragment.
	 * @param defaultFragment
	 * default fragment of the tab
	 */
	public void addTab(TabFragment defaultFragment)
	{
		this.addTab(stacks.size(), defaultFragment);
	}
	
	/**
	 * Adds a new tab to specified index with given default fragment.
	 * @param index
	 * new tab's index.
	 * @param defaultFragment
	 * default fragment of the tab
	 */
	public void addTab(int index, TabFragment defaultFragment)
	{
		Stack<TabFragment> newTabStack = new Stack<TabFragment>();
		newTabStack.push(defaultFragment);
		stacks.add(index, newTabStack);
	}
	
	public void clearCurrentTabStack()
	{
		if(currentTabIndex >= 0)
			this.clearTabStack(currentTabIndex);
	}
	
	public void clearTabStack(int index)
	{
		if(index >= stacks.size() || index < 0)
			return;
		
		Stack<TabFragment> s = stacks.get(index);
		TabFragment firstElement = s.get(0);
		s.clear();
		s.push(firstElement);
	}
	
	public void clearAllTabStacks()
	{
		for(int i = 0 ; i < stacks.size(); i++)
			clearTabStack(i);
	}
	
	public Fragment switchTab(int index)
	{
		if(index >= stacks.size() || index < 0)
			return null;
		
		clearCurrentTabStack();
		currentTabIndex = index;
		
		return stacks.get(index).get(0);
	}
	
	/**
	 * @param fragment to push
	 * @return the current visible fragment before pushing.
	 */
	public void addFragment(TabFragment fragment)
	{
		stacks.get(currentTabIndex).push(fragment);
	}
	
	public TabFragment popFragment()
	{
		if(stacks.get(currentTabIndex).size() <= 1)
			return null;
		
		return stacks.get(currentTabIndex).pop();
	}
	
	public TabFragment getCurrentVisibleFragment()
	{
		return currentTabIndex >= 0 ? stacks.get(currentTabIndex).peek() : null;
	}
}