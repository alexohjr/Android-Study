package com.and.andelectronics.view.main.enterance;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Won on 2017-09-02.
 */
public class EntranceSocket {

    private static String data;

    private byte[] sendData;
    private static final byte[] STX = {(byte)0x02};
    private static final byte[] ETX = {(byte)0x03};

    String sStx = new String(STX);
    String hubSN = "A1234567";
    String deviceSN = "android123456789";
    String cmd = "DO";
    String inputData = "20170902121212";
    String chkSum = "1";
    String sEtx = new String(ETX);
    String appendData = sStx + hubSN + deviceSN + cmd + inputData + chkSum + sEtx;

    private static Socket socket;
    static BufferedReader socket_in;
    static PrintWriter socket_out;


    public EntranceSocket(){
        Thread worker = new Thread() {
            public void run() {
                try {
                    socket = new Socket("192.168.1.8", 14631);
                    socket_out = new PrintWriter(socket.getOutputStream(), true);
                    socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    while (true) {
                        data = socket_in.readLine();
//                        output.post(new Runnable() {
//                            public void run() {
//                                //output.setText(data); 리절트메시지
//                            }
//                        });
                    }
                } catch (Exception e) {
                }
            }
        };
        worker.start();
    }
    /**
     *
     * @param command DO, DC, SP 도어락 오픈/클로즈 비밀번호설정
     * @param data DO/DC 일경우 시간 SP 일경우 비밀번호 값
     * @return
     */
    public String makePacketData(String command, String data)
    {
        byte[] temp = appendData.getBytes();
        String packetData = null;

        try{
            packetData = new String(temp);
        }catch(Exception ex){

        }

        return packetData;
    }

    public static void sendData(String packetData){
        String data = packetData;
        Log.w("NETWORK", " " + data);
        if(data != null) {
            socket_out.println(data);
        }
    }


//    public static void setSocket(){
//        Thread worker = new Thread() {
//            public void run() {
//                try {
//                    socket = new Socket("192.168.1.8", 14631);
//                    socket_out = new PrintWriter(socket.getOutputStream(), true);
//                    socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    while (true) {
//                        data = socket_in.readLine();
////                        output.post(new Runnable() {
////                            public void run() {
////                                //output.setText(data); 리절트메시지
////                            }
////                        });
//                    }
//                } catch (Exception e) {
//                }
//            }
//        };
//        worker.start();
//
//
//    }
}
