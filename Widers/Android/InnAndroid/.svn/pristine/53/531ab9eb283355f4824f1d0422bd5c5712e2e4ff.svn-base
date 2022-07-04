package com.and.andelectronics.common.util.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class BitmapToByteArray {

	
	public byte[] bitmapToByteArray( Bitmap $bitmap ) {  
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;  
        $bitmap.compress( CompressFormat.JPEG, 50, stream) ;
        byte[] byteArray = stream.toByteArray() ; 
        return byteArray ;  
    }  
	
	public Bitmap byteArrayToBitmap( byte[] $byteArray ) {  
	    Bitmap bitmap = BitmapFactory.decodeByteArray( $byteArray, 0, $byteArray.length ) ;  
	    return bitmap ;  
	}  
}
