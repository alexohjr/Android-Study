package com.and.andelectronics.common.util.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.and.andelectronics.common.ApplicationInformation;
import com.and.andelectronics.common.WebServiceInformation;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageDownLoad {

	public static final String BASIC_DOWNLOAD_URL = WebServiceInformation.getImageDownloadUrl();
	public static final String CACHE_FOLDER = ApplicationInformation.LOCAL_CACHE_PATH;

	public static final int DOWNLOAD_SUCCESS = 0;
	public static final int DOWNLOAD_FAILURE = 99;
	public Bitmap mBitmap = null;

	public String getDownloadFromUrl(String fileName) throws Exception{
	
		File file = new File(CACHE_FOLDER);
	
		if(!file.isDirectory()){
			file.mkdir();
		}
		
		URL url = new URL(BASIC_DOWNLOAD_URL+fileName);
		InputStream input = null;
		int count = 0;						
		
		input = new BufferedInputStream(url.openStream(), 8192);
		OutputStream output = new FileOutputStream(CACHE_FOLDER+fileName);
		byte data[] = new byte[1024];
		while ((count = input.read(data)) != -1) {
		    output.write(data, 0, count);
		}
		
		output.flush();
		output.close();
		input.close();	
		return CACHE_FOLDER+fileName;//�н� ���� 
	}
	
	
	/**
	 * ����Ǿ� �ִ°����� ������ �о�� bitmap���� ����
	 * @param path ���
	 * @return bitmap
	 */
    public Bitmap getBitmapResource(String path){
        return BitmapFactory.decodeFile(path, null);//bitmap �����
    }    
    
	
    /**
     * �̹����信 ���� Bitmap ����
     * @param fileName �����;� �ϴ� ���ϸ� 
     */
	public Bitmap setImageBitmap(final String fileName) {
		mBitmap =null;
	//	new Thread(){
		//	public void run() {
				try {
					//�켱 ��ġ�� ����Ǿ� �ִ� ������ �о������ �õ�
					mBitmap = getBitmapResource(CACHE_FOLDER+fileName);
					if(mBitmap == null){//��Ʈ���� null�̸� ��ġ���� �о�� ���� ����.
						//�ٿ�ε� ����
						mBitmap = getBitmapResource(getDownloadFromUrl(fileName));
						Log.e("Download succ","msg : "+mBitmap.toString());
					}//null�� �ƴϸ� ��ġ�� ���� �о��
					
					//downloadHandler.sendEmptyMessage(DOWNLOAD_SUCCESS);
				} catch (Exception e) {
					Log.e("Download Failure","msg : "+e);
					//downloadHandler.sendEmptyMessage(DOWNLOAD_FAILURE);
				}
		//	};
		//}.run();
		
		return mBitmap;
	}
	
	/**
	 * �ڵ鷯
	 */
/*	Handler downloadHandler = new Handler(new Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOAD_SUCCESS:
				mImage.setImageBitmap(mBitmap);
				mImage.setVisibility(View.VISIBLE);
				mSpinner.setVisibility(View.INVISIBLE);
				
				//�ٿ���� �̹����� ������ ��Ÿ���� anim
				Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.anim_type_image_alpha);
		    	mImage.startAnimation(anim);
				break;
			case DOWNLOAD_FAILURE:
				Toast.makeText(mContext, "�̹��� �ٿ�ε� ����!", Toast.LENGTH_SHORT).show();
				mSpinner.setVisibility(View.INVISIBLE);
				mImage.setVisibility(View.INVISIBLE);
				break;
			}
			return true;
		}		
	});*/
}
