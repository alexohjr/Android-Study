package com.and.andelectronics.common;

import android.app.Activity;

import java.util.ArrayList;

/**
 * @author  yowonsm
 */
public class ActivityManager {

	private static ActivityManager activityMananger = null;
	private ArrayList<Activity> activityList = null;
	
	private ActivityManager() { 
		activityList = new ArrayList<Activity>();
	}
	
	public static ActivityManager getInstance() {
		
		if( ActivityManager.activityMananger == null ) {
			activityMananger = new ActivityManager();
		}
		return activityMananger;
	}
	
	/**
	 * ��Ƽ��Ƽ ����Ʈ getter.
	 * @return activityList
	 */
	public ArrayList<Activity> getActivityList() {
		return activityList;
	}

	/**
	 * ��Ƽ��Ƽ ����Ʈ�� �߰�.
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}
	
	/**
	 * ��Ƽ��Ƽ ����Ʈ���� ����.
	 * @param activity
	 * @return boolean
	 */
	public boolean removeActivity(Activity activity) {
		return activityList.remove(activity);
	}
	
	/**
	 * ��� ��Ƽ��Ƽ ����.
	 */
	public void finishAllActivity() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}
}
