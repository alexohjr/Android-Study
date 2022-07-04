package com.and.andelectronics.common.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

public class LocationUtil {
	//Context context;
	Geocoder geocoder;

	public LocationUtil(Context context) {
		geocoder = new Geocoder(context, Locale.KOREAN);
	}

	public LatLng searchLocation(String searchStr) {
		List<Address> addressList = null;
		LatLng latlng = null;

		try {

			addressList = geocoder.getFromLocationName(searchStr, 5);

			if (addressList != null) {


				for (int i = 0; i < addressList.size(); i++) {
					Address address = addressList.get(i);

					Log.w("lat", "lati=" + address.getLatitude() + "longi="+address.getLongitude());
					latlng = new LatLng(address.getLatitude(),address.getLongitude());
				}

			}

		} catch (Exception e) {
		}
		return latlng;
	}

	public String searchAddress(double latitude, double longitude) {
		String returnAddress=null;
		List<Address> addressList = null;
		try {
			addressList = geocoder.getFromLocation(latitude, longitude, 3);

			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					Address address = addressList.get(i);
					Log.i("lat", "lati=" + address.getLatitude() + "longi="+address.getLongitude());
					returnAddress = address.getAddressLine(0);

				}
			}
		} catch (Exception e) {
		}
		return returnAddress;
	}

	/**
	 * 구 단위 조회
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public String getSubLocality(double latitude, double longitude) {
		String returnAddress=null;
		List<Address> addressList = null;
		try {
			addressList = geocoder.getFromLocation(latitude, longitude, 3);

			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					Address address = addressList.get(i);
					Log.i("lat", "lati=" + address.getLatitude() + "longi="+address.getLongitude());
					returnAddress = address.getSubLocality();
					if(returnAddress != null)break;

				}
			}
		} catch (Exception e) {
		}
		return returnAddress;
	}

	/**
	 * 동 단위 조회
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public String getSubThoroughfare(double latitude, double longitude) {
		String returnAddress=null;
		List<Address> addressList = null;
		try {
			addressList = geocoder.getFromLocation(latitude, longitude, 3);

			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					Address address = addressList.get(i);
					Log.i("lat", "lati=" + address.getLatitude() + "longi="+address.getLongitude());
					returnAddress = address.getThoroughfare();
					if(returnAddress != null)break;

				}
			}
		} catch (Exception e) {
		}
		return returnAddress;
	}

}
