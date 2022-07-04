package com.and.andelectronics.webservice;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class WebServiceClient {
    private static final String SOAP_ACTION = "http://tempuri.org/";
    // private static final String SOAP_METHOD = "RegistPos";
    private static final String NAMESPACE = "http://tempuri.org/";
    private String URL = "";

    public WebServiceClient(String url){
        this.URL = url;
    }



    public String RequestWebservice(String SOAP_METHOD, String S_SOAP_METHOD, Hashtable hashtable, Hashtable hashtable_s, String ClassName) {
        SoapObject request1 = new SoapObject(NAMESPACE, S_SOAP_METHOD); // Creating_SOAP_object
        SoapObject request = new SoapObject(NAMESPACE, SOAP_METHOD); // Creating_SOAP_object
        Enumeration<String> enumerationKey = hashtable.keys();
        Enumeration<String> enumerationValue = hashtable.elements();
        while (enumerationKey.hasMoreElements()) {
            request.addProperty(enumerationKey.nextElement(), enumerationValue.nextElement());
        }
        Enumeration<String> enumerationKey_s = hashtable_s.keys();
        Enumeration<String> enumerationValue_s = hashtable_s.elements();
        while (enumerationKey_s.hasMoreElements()) {
            request1.addProperty(enumerationKey_s.nextElement(), enumerationValue_s.nextElement());
        }
        request.addProperty(ClassName, request1);
        // Finished Creating SOAP object
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        // Creating SOAP envelope
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(request);
        // Finished Creating SOAP envelope
        HttpTransportSE aht = new HttpTransportSE(URL);
        // aht.debug = true;
        Object output_T = new Object();
        try
        {
            aht.call(SOAP_ACTION + SOAP_METHOD, soapEnvelope); // Creating_SOAP_call
            SoapObject response = (SoapObject) soapEnvelope.bodyIn;
            String result = response.getPropertyAsString(0);
            // 6.Finished Creating SOAP call

            //result, message 넣기 result = [1], message = [2]
           /* if(response != null && response.getPropertyCount() > 0)
            {
                SoapPrimitive soapPrimitiveResult = (SoapPrimitive)response.getProperty("result");
                String resultCode = soapPrimitiveResult.toString();
                result.put("result", resultCode);

                SoapObject soapPrimitiveMessage = (SoapObject)response.getProperty("message");
                String resultMessage = soapPrimitiveMessage.toString();
                result.put("message", resultMessage);

            }*/
            return result;
        }
        catch (Exception e)
        {
            return null;  // ����
        }
        // ����
    }

    public String RequestWebservice(String SOAP_METHOD, Hashtable hashtable) {

        SoapObject request = new SoapObject(NAMESPACE, SOAP_METHOD); // Creating_SOAP_object
        Enumeration<String> enumerationKey = hashtable.keys();
        Enumeration<String> enumerationValue = hashtable.elements();
        while (enumerationKey.hasMoreElements()) {
            request.addProperty(enumerationKey.nextElement(), enumerationValue.nextElement());
        }

        // Finished Creating SOAP object
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        // Creating SOAP envelope
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(request);
        // Finished Creating SOAP envelope
        HttpTransportSE aht = new HttpTransportSE(URL);
        // aht.debug = true;
        try
        {
            aht.call(SOAP_ACTION + SOAP_METHOD, soapEnvelope); // Creating_SOAP_call
            SoapObject response = (SoapObject) soapEnvelope.bodyIn;
            String result = response.getPropertyAsString(0);
            // 6.Finished Creating SOAP call
            return result;
        }
        catch (Exception e)
        {
            return e.getMessage();  // ����
        }
        // ����
    }


    public HashMap RequestWebservice(String SOAP_METHOD,  Class bean,boolean isArray){
        Hashtable hashtable = new Hashtable();
        return RequestWebservice(SOAP_METHOD, hashtable, bean,isArray);
    }

    public HashMap RequestWebservice(String SOAP_METHOD, Hashtable hashtable, Class bean,boolean isArray) {
        return RequestWebservice(SOAP_METHOD,null, hashtable,null,null, bean,isArray);
    }

    public HashMap RequestWebservice(String SOAP_METHOD, String S_SOAP_METHOD, Hashtable hashtable, Hashtable hashtable_s, String ClassName, Class bean,boolean isArray) {
        SoapObject request = new SoapObject(NAMESPACE, SOAP_METHOD); // Creating_SOAP_object
        Enumeration<String> enumerationKey = hashtable.keys();
        Enumeration<String> enumerationValue = hashtable.elements();
        while (enumerationKey.hasMoreElements()) {
            request.addProperty(enumerationKey.nextElement(), enumerationValue.nextElement());
        }
        if(S_SOAP_METHOD != null){
            SoapObject request1 = new SoapObject(NAMESPACE, S_SOAP_METHOD); // Creating_SOAP_object
            Enumeration<String> enumerationKey_s = hashtable_s.keys();
            Enumeration<String> enumerationValue_s = hashtable_s.elements();
            while (enumerationKey_s.hasMoreElements()) {
                request1.addProperty(enumerationKey_s.nextElement(), enumerationValue_s.nextElement());
            }
            request.addProperty(ClassName, request1);
        }
        return CallWebservice(request, SOAP_METHOD, bean,isArray);
    }

    private HashMap CallWebservice(SoapObject request, String SOAP_METHOD, Class outputclass,boolean isArray) {
        boolean errCheck = false;
        HashMap result = new HashMap();
        // Finished Creating SOAP object
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        // Creating SOAP envelope
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(request);
        // Finished Creating SOAP envelope
        HttpTransportSE aht = new HttpTransportSE(URL);

        ArrayList<Object> outputs = new ArrayList<Object>();
        try {
            try {
                aht.call(SOAP_ACTION + SOAP_METHOD, soapEnvelope); // Creating
                //SoapObject response = (SoapObject) soapEnvelope.getResponse();

            }
            catch (Exception e) {
                result.put("output", null);
                result.put("result", -1);
                result.put("message", "웹서비스 에러입니다.");
                e.printStackTrace();
                return result;
            }
            SoapObject response = (SoapObject) soapEnvelope.bodyIn;

            ArrayList<SoapObject> subresponses = new ArrayList<SoapObject>();

            Object outputClass;
            Object output;
            SoapObject subResponse;
            try
            {
                if(isArray)
                {
                    subResponse =(SoapObject) response.getProperty(0);
                    for (int j = 0; j < subResponse.getPropertyCount(); j++)
                    {
                        //subresponses.add((SoapObject) subResponse.getProperty(j).toString());
                        outputClass = outputclass.newInstance();
                        output = new Object();
                        output = Ksoap2ResultParser.parseObject(subResponse.getProperty(j).toString(), outputClass);
                        outputs.add(output);
                    }
                    result.put("output", outputs);
                }
                else
                {
                    subResponse = response;
                    outputClass = outputclass.newInstance();
                    output = new Object();
                    output = Ksoap2ResultParser.parseObject(subResponse.getPropertyAsString(0), outputClass);
                    result.put("output", output);

                }
            }
            catch(Exception ex)
            {
            }

            //result, message 넣기 result = [1], message = [2]
            if(response != null && response.getPropertyCount() > 0)
            {
                SoapPrimitive soapPrimitiveResult = (SoapPrimitive)response.getProperty("result");
                String resultCode = soapPrimitiveResult.toString();
                result.put("result", resultCode);

                SoapObject soapPrimitiveMessage = (SoapObject)response.getProperty("message");
                String resultMessage = soapPrimitiveMessage.toString();
                result.put("message", resultMessage);

            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

}