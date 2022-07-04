package com.and.andelectronics.common.util;

import android.support.annotation.NonNull;

/**
 * Created by 원성민 on 2017-11-21.
 */

public class SocketProtocolUtil {

    private static final int DOOR_LOCK_BYTE = 9;    //도어락 프로토콜  ArrayCopy 시 startByte
    public SocketProtocolUtil() {
    }

    /*
    체크섬 로직
     */
    public static String getChecksum(byte[] byteArray){

        int checksum = 0;
        for(byte element : byteArray)
        {
            checksum += element;
        }
        checksum &= 0xFF;
        checksum =~checksum+1;  //보수
        checksum &= 0xFF;

        byte check = (byte)checksum;
        return Integer.toString(checksum, 16);  //16진수로 변환
        //return Integer.valueOf(String.valueOf(checksum), 16);
    }

    public static byte getChecksumToByte(byte[] byteArray){
        int checksum = 0;
        for(byte element : byteArray)
        {
            checksum += element;
        }
        checksum &= 0xFF;
        checksum =~checksum+1;  //보수
        checksum &= 0xFF;

        return (byte)checksum;

    }

    /**
     * MSR Track Type Check (0xC5)
     */
    /*public static byte[] () {
        // MSR Track Type Check
        String waitSecs = "10";

        StringBuffer sb = new StringBuffer();
        sb = sb.append(waitSecs);

        byte ret[] = sb.toString().getBytes();

        byte[] d = new byte[6];
        d[0] = 0x02;
        d[1] = Command.CMD_MSR_CHECK;
        d[2] = 0x00;
        d[3] = 0x02;
        System.arraycopy(ret, 0, d, DOOR_LOCK_BYTE, ret.length);

        return d;
    }*/

    private static final byte STX = (byte)0x02;
    private static final byte ETX = (byte)0x03;
    private static final byte MAIN_ONE = (byte)0xAD;
    private static final byte MAIN_TWO = (byte)0x11;
    private static final byte bhub = (byte)0x60;
    private static final byte bhub2 = (byte)0x01;
    private static final byte bhub3= (byte)0x94;
    private static final byte bhub4 = (byte)0x42;
    private static final byte bhub5 = (byte)0x1E;
    private static final byte bhub6 = (byte)0xFC;
    /**
     * 20171129
     * @return
     */
    public static byte[] openDoorCommand(@NonNull String data) {
        byte[] d = new byte[50];
        d[0] = STX;
        d[1] = MAIN_ONE;
        d[2] = MAIN_TWO;
        d[3] = bhub;
        d[4] = bhub2;
        d[5] = bhub3;
        d[6] = bhub4;
        d[7] = bhub5;
        d[8] = bhub6;

        byte ret[] = data.getBytes();
        //System.arraycopy(ret, 0, d, 3, ret.length);
        System.arraycopy(ret, 0, d, DOOR_LOCK_BYTE, ret.length);

        byte[] makeHub = makeHubSerialNo("600194421EFC");
        LogUtil.d("ST", makeHub.toString());
        return d;
    }

    public static byte[] makeHubSerialNo(String data){
        byte[] d = new byte[6];
        d[0] = data.substring(0, 2).getBytes()[0];
        d[1] = data.substring(2, 4).getBytes()[0];
        d[2] = data.substring(4, 6).getBytes()[0];
        d[3] = data.substring(6, 8).getBytes()[0];
        d[4] = data.substring(8, 10).getBytes()[0];
        d[5] = data.substring(10, 12).getBytes()[0];

        return d;
    }

    // ============== click event ===============
    public static byte[] makeData(byte[] b) {
        byte sendBuffer[] = new byte[b.length + 2];
        System.arraycopy(b, 0, sendBuffer, 0, b.length);
        sendBuffer[b.length] = getChecksumToByte(b);
        sendBuffer[b.length + 1] = ETX;
        return sendBuffer;
    }
}
