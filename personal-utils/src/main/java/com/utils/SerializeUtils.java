package com.utils;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>Description: [序列化工具类-camelot]</p>
 * Created on 2017年3月7日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class SerializeUtils {
	
	/**
	 * <p>Discription:[序列化]</p>
	 * Created on 2017年3月7日
	 * @param object
	 * @return
	 * @author:[左仁智]
	 */
	public static byte[] serialize(Object object) {  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        try {  
	        //序列化  
	        baos = new ByteArrayOutputStream();  
	        oos = new ObjectOutputStream(baos);  
	        oos.writeObject(object);  
	        byte[] bytes = baos.toByteArray();  
	        return bytes;  
        } catch (Exception e) {  
        	e.printStackTrace();  
        }  
        return null;  
    }  
           
	/**
	 * <p>Discription:[反序列化]</p>
	 * Created on 2017年3月7日
	 * @param bytes
	 * @return
	 * @author:[左仁智]
	 */
    public static Object unserialize(byte[] bytes) {  
        ByteArrayInputStream bais = null;  
        try {  
	        //反序列化  
	        bais = new ByteArrayInputStream(bytes);  
	        ObjectInputStream ois = new ObjectInputStream(bais);  
	        return ois.readObject();  
        } catch (Exception e) {  
           e.printStackTrace();
        }  
        return null;
     }
}
