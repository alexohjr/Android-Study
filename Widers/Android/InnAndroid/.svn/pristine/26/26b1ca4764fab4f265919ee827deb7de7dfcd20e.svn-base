package com.and.andelectronics.common.jinlib;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class EasyJsonMap {

	//Field
	JSONObject jsonMap;
	
	//Constructor
	public EasyJsonMap() {
	}
	public EasyJsonMap(JSONObject jsonMap) {
		
		if(jsonMap != null){
			this.jsonMap = jsonMap;
		}else if(jsonMap == null){
			this.jsonMap = new JSONObject();
		}
		
	}
	
	//Method
	public JSONObject getInstance(){
		return jsonMap;
	}
	
	public String getValue(String str) throws JSONException{
		
		String mapStr = "";
		try {
			if(!jsonMap.isNull(str)){
				mapStr = jsonMap.getString(str);
			}else if(jsonMap.isNull(str)){
				Log.v("dev", "[EasyJsonMap] doesn't have this key --> " + str);
			}
		} catch (Exception e) {
			Log.v("dev msg", "[EasyJsonMap] exception occurs as below");
			Log.v("[getValue()]", e.toString());
		}
		return mapStr;
	};
	
	public int getLenght(){
		return jsonMap.length();
	}
	
	public void setValue(String name, String str){
		try {
			jsonMap.put(name, str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public boolean containKey(String key){
		try {
			if(jsonMap.isNull(key)){
				return false;
			}
		} catch (Exception e) {
			Log.v("[DEVMsg]", "[EasyJsonMap] exception occurs as below");
			Log.v("[containKey()]", e.toString());
		}
		return true;
	}
		
};