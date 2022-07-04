package com.and.andelectronics.webservice;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Ksoap2ResultParser {
	/**
	 * Parses a single business object containing primitive types from the
	 * response
	 * 
	 * @param input
	 *            soap message, one element at a time
	 * @param theClass
	 *            your class object, that contains the same member names and
	 *            types for the response soap object
	 * @return the values parsed
	 * @throws NumberFormatException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	
	    public static Object parseObject(String input, Object output) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, InstantiationException{
	 
	        Class theClass = output.getClass();
	        Field[] fields = theClass.getDeclaredFields();
	        
	        for (int i = 0; i < fields.length; i++) {
	            Type type=fields[i].getType();
	            fields[i].setAccessible(true);
	 
	            //detect String
	            if (fields[i].getType().equals(String.class)) {
	                String tag = tagAttach(fields[i].getName(),input); 
	                if(input.contains(tag)){
	                    String strValue = input.substring(input.indexOf(tag)+tag.length(), input.indexOf(";", input.indexOf(tag)));
	                    if(strValue.length()!=0){
	                        fields[i].set(output, strValue);
	                    } 
	                } 
	            }
	 
	            //detect int or Integer
	            if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
	                String tag = tagAttach(fields[i].getName(),input); 
	                if(input.contains(tag)){
	                    String strValue = input.substring(input.indexOf(tag)+tag.length(), input.indexOf(";", input.indexOf(tag)));
	                    if(strValue.length()!=0){
	                        fields[i].setInt(output, Integer.valueOf(strValue)); 
	                    }
	                }
	            }
	            
	          //detect int or Integer
	            if (type.equals(Long.TYPE) || type.equals(Long.class)) {
	                String tag = tagAttach(fields[i].getName(),input); 
	                if(input.contains(tag)){
	                    String strValue = input.substring(input.indexOf(tag)+tag.length(), input.indexOf(";", input.indexOf(tag)));
	                    if(strValue.length()!=0){
	                        fields[i].setLong(output, Long.valueOf(strValue)); 
	                    }
	                }
	            }
	 
	            //detect float or Float
	            if (type.equals(Float.TYPE) || type.equals(Float.class)) {
	                String tag = tagAttach(fields[i].getName(),input);
	                if(input.contains(tag)){
	                    String strValue = input.substring(input.indexOf(tag)+tag.length(), input.indexOf(";", input.indexOf(tag)));
	                    if(strValue.length()!=0){
	                        fields[i].setFloat(output, Float.valueOf(strValue));
	                    } 
	                }
	            }
	        }
	        return output;
	       
	    }
	    
		public static String tagAttach(String field, String input) {
			String tag = "";
			tag = "{" + field + "=";
			//tag = tag.toLowerCase();
			if (input.indexOf(tag) == -1) {
				tag = " " + field + "=";
			}
			return tag;
		}
	}

/*	public static Object ResultParser(String input, Object output) throws NumberFormatException, IllegalAccessException{

		Parser parser = new Parser();
		return parser.parser(input, output, ";");
	}

}*/
