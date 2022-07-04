package com.and.andelectronics.common.util.image;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageResize {
private String path = "/mnt/sdcard/DogPlus/";
	public ImageResize() {
		
	}

	public void saveBitmapToFileCache(Bitmap bitmap, String strFilePath) {

		Log.e("sdcardIput", "file Create");
		String full_fileName = strFilePath+".jpg";
		File dir = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath(), "/DogPlus/");

		if (!dir.exists()) {
			dir.mkdirs();
			Log.w("NoDirec", "dir Create");
		}


		File fileCacheItem = new File(path + full_fileName);
		OutputStream out = null;

		try {
			fileCacheItem.createNewFile();
			out = new FileOutputStream(fileCacheItem);

			bitmap.compress(CompressFormat.JPEG, 50, out);
			Log.e("sdcardIput", "     bitmap.compress(CompressFormat");

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("sdcardIput", "     e.printStackTrace();");
		} finally {
			try {
				out.close();
				Log.e("sdcardIput", "  out.close();");
			} catch (IOException e) {
				Log.e("sdcardIput", "IOException");
				e.printStackTrace();
			}
		}
	}

	@SuppressLint("NewApi")
	public Bitmap bitmapResize(String fileName) {
		//int MAX_IMAGE_SIZE = 800;
		Bitmap bm = null;
		BitmapFactory.Options bfo = new BitmapFactory.Options();
		int scale = 1;
		int imgWidth = getBitmapOfWidth(fileName);
		int imgHeight = getBitmapOfHeight(fileName);
		Log.e("widthxHeight", imgWidth + "x" + imgHeight);

		/*if (imgHeight > MAX_IMAGE_SIZE || imgWidth > MAX_IMAGE_SIZE) {
			scale = (int) Math.pow(2,(int) Math.round(Math.log(MAX_IMAGE_SIZE
							/ (double) Math.max(imgHeight, imgWidth))
							/ Math.log(0.5)));
		}

		bfo.inSampleSize = scale;*/
		Log.v("what scale", "waht salce" + scale);
		if (imgWidth > 8000 || imgHeight > 8000) {
			bfo.inSampleSize = 16;
		} else if (imgWidth > 4000 || imgHeight > 4000) {
			bfo.inSampleSize = 8;
		} else if (imgWidth > 2000 || imgHeight > 2000) {
			bfo.inSampleSize = 4;
		} else if (imgWidth > 1000 || imgHeight > 1000) {
			bfo.inSampleSize = 2;
		} else {
			bfo.inSampleSize = 1;
		}

		bfo.inScaled = true;
	
		bm = BitmapFactory.decodeFile(fileName, bfo);
		int degree = GetExifOrientation(fileName);
		Bitmap orientBitmap = GetRotatedBitmap(bm, degree);
		// Bitmap resized = Bitmap.createScaledBitmap(orientBitmap, 200, 200,
		// true);
		Log.i("widthxHeight",
				orientBitmap.getWidth() + "x" + orientBitmap.getHeight()/*
																		 * +
																		 * "thumbnail count ="
																		 * +
																		 * orientBitmap
																		 * .
																		 * getByteCount
																		 * ()
																		 */);

		return orientBitmap;
	}

	
	public Bitmap upLoadBitmap(String fileName) {
		Bitmap bm = null;
		bm = BitmapFactory.decodeFile(path+fileName+".jpg");
		return bm;
	}
	@SuppressLint("NewApi")
	public static Bitmap thumbnailResize(Bitmap bitmap) {
		Bitmap thumnail = null;
		if (bitmap != null)
			thumnail = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
		if (bitmap != null)
			Log.w("widthxHeight", thumnail.getWidth() + "x" /*
															 * +
															 * thumnail.getHeight
															 * (
															 * )+"thumbnail count ="
															 * +
															 * thumnail.getByteCount
															 * ()
															 */);

		return thumnail;

	}
	
	public static Bitmap newPetResize(Bitmap bitmap) {
		Bitmap thumnail = null;
		if (bitmap != null)
			thumnail = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
		if (bitmap != null)
			Log.w("widthxHeight", thumnail.getWidth() + "x" /*
															 * +
															 * thumnail.getHeight
															 * (
															 * )+"thumbnail count ="
															 * +
															 * thumnail.getByteCount
															 * ()
															 */);

		return thumnail;

	}

	/** Get Bitmap's Width **/
	public static int getBitmapOfWidth(String fileName) {
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(fileName, options);
			return options.outWidth;
		} catch (Exception e) {
			return 0;
		}
	}

	/** Get Bitmap's height **/
	public static int getBitmapOfHeight(String fileName) {

		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(fileName, options);

			return options.outHeight;
		} catch (Exception e) {
			return 0;
		}
	}

	/*
	 * �̹��������� ������Ż�� ������ �Լ�
	 */
	public synchronized static int GetExifOrientation(String filepath) {
		int degree = 0;
		ExifInterface exif = null;

		try {
			exif = new ExifInterface(filepath);
		} catch (IOException e) {
			// Log.e(TAG, "cannot read exif");
			e.printStackTrace();
		}

		if (exif != null) {
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION, -1);

			if (orientation != -1) {
				// We only recognize a subset of orientation tag values.
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;

				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;

				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
				}

			}
		}

		return degree;
	}

	/*
	 * ��Ʈ�������� ���ϴ� �������� ȸ����Ű�� �Լ� �⺻ ������Ż ��Ʋ��
	 */
	public synchronized static Bitmap GetRotatedBitmap(Bitmap bitmap,
			int degrees) {
		if (degrees != 0 && bitmap != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) bitmap.getWidth() / 2,
					(float) bitmap.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != b2) {
					bitmap.recycle();
					bitmap = b2;
				}
			} catch (OutOfMemoryError ex) {
				// We have no memory to rotate. Return the original bitmap.
			}
		}

		return bitmap;
	}

}
