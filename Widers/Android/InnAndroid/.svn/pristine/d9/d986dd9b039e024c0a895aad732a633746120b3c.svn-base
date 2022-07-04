package com.and.andelectronics;



import android.app.Application;

import com.and.andelectronics.infrastructure.model.lodgement.Lodgement;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveRoom;
import com.and.andelectronics.infrastructure.model.lodgement.ReserveTime;
import com.and.andelectronics.view.main.enterance.EnterType;
import com.ez.EZActivity;

import java.util.ArrayList;

public class Container  extends Application{


	/**
	 * 현재 Activated 된 Activity를 담는 곳
	 */
	private static volatile EZActivity _currentActivity = null;

	/**
	 * 글로벌 어플리케이션 인스턴스
	 */
	private static volatile Container _instance = null;

	/**
	 * 어플리케이션 객체를 반환
	 * @return 어플리케이션 객체
	 */
	public static synchronized Container getInstance() {
		return _instance;
	}

	/**
	 * 현재 Activated 된 Activity를 설정한다.
	 * @param currentActivity 현재 Activated 된 앱
	 */
	public static void setCurrentActivity(EZActivity currentActivity){
		_currentActivity = currentActivity;
	}

	/**
	 * singleton 애플리케이션 객체를 얻는다.
	 * @return singleton 애플리케이션 객체
	 */
	public static Container getGlobalApplicationContext() {
		if(_instance == null)
			throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
		return _instance;
	}


	@Override
    public void onCreate() {
		this.currentReserveRoom = new ReserveRoom();
		this.reserveRoomList = new ArrayList<ReserveRoom>();
        super.onCreate();
		_instance = this;
    }

	/**
	 * 현재 숙박업소, 객실, 예약시간등을 모두 초기화 한다.
	 */
	public void initCurrentItem(){
		//currentLodgement = null;
		currentReserveRoom = null;
		reserveFromDate = null;
		reserveToDate = null;
		currentEnterType = null;
		currentPassword = null;
	}

    private String reserveFromDate;
	private String reserveToDate;
	private ArrayList<ReserveTime> reserveTimeList;

	public ArrayList<ReserveTime> getReserveTimeList() {
		return reserveTimeList;
	}

	public void setReserveTimeList(ArrayList<ReserveTime> reserveTimeList) {
		this.reserveTimeList = reserveTimeList;
	}

	public String getReserveFromDate() {
		return reserveFromDate;
	}

	public void setReserveFromDate(String reserveFromDate) {
		this.reserveFromDate = reserveFromDate;
	}

	public String getReserveToDate() {
		return reserveToDate;
	}

	public void setReserveToDate(String reserveToDate) {
		this.reserveToDate = reserveToDate;
	}

	//region 예약내역 리스트/CurrentReserve
	private ReserveRoom currentReserveRoom;

	public ReserveRoom getCurrentReserveRoom() {
		return currentReserveRoom;
	}

	public void setCurrentReserveRoom(ReserveRoom currentReserveRoom) {
		this.currentReserveRoom = currentReserveRoom;
	}

	private ArrayList<ReserveRoom> reserveRoomList;

	public ArrayList<ReserveRoom> getReserveRoomList() {
		return reserveRoomList;
	}

	public void setReserveRoomList(ArrayList<ReserveRoom> reserveRoomList) {
		this.reserveRoomList = reserveRoomList;
	}

	private Lodgement currentLodgement;

	public Lodgement getCurrentLodgement() {
		return currentLodgement;
	}

	public void setCurrentLodgement(Lodgement currentLodgement) {
		this.currentLodgement = currentLodgement;
	}

	private String currentAddress;

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	private EnterType currentEnterType;

	public EnterType getCurrentEnterType() {
		return currentEnterType;
	}

	public void setCurrentEnterType(EnterType currentEnterType) {
		this.currentEnterType = currentEnterType;
	}
	private String currentPassword;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	//endregion

	
}
