package com.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.IOUtils;

/**
 * gzip 压缩工具
 * @author liangbl
 *
 */
public class GzipCompressorUtils {
	
	/**
	 * 压缩
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public static byte[] compress(byte[] bytes) throws IOException{
		if(bytes == null || bytes.length < 1){
			return null;
		}
		
		ByteArrayOutputStream byteArrayOutputStream = null;
		GzipCompressorOutputStream gzippedOut = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			gzippedOut = new GzipCompressorOutputStream(byteArrayOutputStream);
			
			gzippedOut.write(bytes);
			gzippedOut.flush();
			byteArrayOutputStream.flush();
			
		} finally{
			if(gzippedOut != null){
				gzippedOut.close();
			}
			if(byteArrayOutputStream != null){
				byteArrayOutputStream.close();
			}
		}
		return byteArrayOutputStream.toByteArray();
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public static byte[] uncompress(byte[] bytes) throws IOException{
		if(bytes == null || bytes.length < 1){
			return null;
		}
		
		byte[] returnBytes = null;
		GzipCompressorInputStream in = null;
		try {
			in = new GzipCompressorInputStream(new ByteArrayInputStream(bytes));
			returnBytes = IOUtils.toByteArray(in);
		}  finally {
			if(in != null){
				in.close();
			}
		}
		return returnBytes;
	}
}
