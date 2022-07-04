package com.and.andelectronics;

/**
 * Created by yowonsm on 2018-06-01.
 */

public class Constants {
    public static final String TITLE            = "TITLE";
    public static final String VALUE            = "VALUE";
    public static final String URL              = "URL";

    //DATABASE 연동 상수 정리 20190107

    //region 객실상태
    public static final String ROOMEMT  = "EM";
    public static final String ROOMLON  = "LN";
    public static final String ROOMSHT  = "SH";
    public static final String ROOMDAY  = "DA";
    public static final String ROOMLVE  = "LV";
    public static final String ROOMEXT  = "EX";
    public static final String ROOMCVT  = "CV";
    public static final String ROOMCHG  = "CG";
    public static final String ROOMOUT  = "OT";
    public static final String ROOMRSV  = "RV";
    public static final String ROOMCAN  = "CA";
    public static final String ROOMRPI  = "RP";
    public static final String ROOMCLN  = "CL";
    public static final String ROOMEMG  = "EM";
    public static final String ROOMCTL  = "CT";

    public static final String TITLEEMT  = "공실";
    public static final String TITLELON  = "숙박";
    public static final String TITLESHT  = "대실";
    public static final String TITLEDAY  = "장기";
    public static final String TITLELVE  = "외출";
    public static final String TITLEEXT  = "연장";
    public static final String TITLECVT  = "전환";
    public static final String TITLECHG  = "변경";
    public static final String TITLEOUT  = "퇴실";
    public static final String TITLERSV  = "예약";
    public static final String TITLECAN  = "취소";
    public static final String TITLERPI  = "수리";
    public static final String TITLECLN  = "청소";
    public static final String TITLEEMG  = "체크";
    public static final String TITLECTL  = "설정";
    //endregion


    //region 지불방법
    public static final int SALECASH = 1;
    public static final int SALECARD = 2;
    public static final int SALECREDIT = 3;
    public static final int SALEPOINT = 4;
    public static final int SALEGIFT = 5;
    public static final int SALECOUPON = 6;
    public static final int SALEBANK = 7;
    public static final int SALESYSTEM = 8;

    public static final String TITLECASH  = "현금";
    public static final String TITLECARD  = "카드";
    public static final String TITLECREDIT  = "신용";
    public static final String TITLEPOINT  = "포인트";
    public static final String TITLEGIFT  = "상품권";
    public static final String TITLECOUPON  = "쿠폰";
    public static final String TITLEBANK  = "계좌이체";
    public static final String TITLESYSTEM  = "시스템";
    //endregion



    //region 판매
    //어디서 쓸지 정확치 않음
    public static final String SALESTR   = "SS";    //사용
    public static final String SALEFIN   = "SF";    //정상
    public static final String SALECAN   = "SC";    //취소
    public static final String SALESTB  = "SB";     //대기
    //endregion

    //region 예약
    public static final String RSVSTRT    = "RS";    //예약
    public static final String RSVDEPS    = "RD";    //확정
    public static final String RSVSALE   = "RU";    //사용
    public static final String RSVCANC   = "RC";     //취소
    //endregion

    //region 요금분류
    public static final String DETSTY     = "DS";    //객실요금
    public static final String DETADD     = "DA";    //추가요금
    public static final String DETDPS   = "DP";    //예약금
    public static final String DETEXT    = "DX";     //연장요금
    public static final String DETOTH     = "DO";     //기타요금
    public static final String DETDIS    = "DD";     //기본할인
    //endregion


    //region 공통코드
    public static final String CODE_GRADE      = "GR";    //객실등급
    public static final String CODE_ROOMTYPE      = "RT";    //객실형태
    public static final String CODE_CHANGE    = "CG";    //변경사유
    public static final String CODE_CANCEL     = "CC";     //취소사유
    public static final String CODE_REQUEST      = "RQ";     //요청항목
    public static final String CODE_REPAIR     = "RP";     //수리품목
    public static final String CODE_EXPENSE     = "EP";     //지출항목
    //endregion

    //region 요청
    public static final String REQING      = "RS";    //요청시작
    public static final String REQCPL       = "RE";    //요청종료
    public static final String REQEMERGENCY    = "R0";    //객실체크 또는 비상 시
    public static final String REQMORNING      = "R1";     //모닝콜
    public static final String REQCLEAN       = "R2";     //청소요청
    public static final String REQDISTURB      = "R3";     //방해금지
    public static final String REQSTBCAR      = "R4";     //차량대기
    //endregion


    public enum Action {
        I,  //추가
        U,  //갱신
        D   //삭제
    }
}
