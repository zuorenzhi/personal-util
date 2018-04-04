package com.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Description: xml字符串转对象 <br/>
 * Created on: 2017/6/7 11:10 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class XmlUtils {

    /**
     *
     * <p>Discription:[解析xml字符串返回值]</p>
     * Created on 2017年1月12日
     * @param strxml
     * @return
     * @throws Exception
     * @author:[hanshixiong]
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    private static <T> T doXMLParse(Class<T> clazz, String strxml) throws Exception {
        if(null == strxml || "".equals(strxml)) {
            return null;
        }
        T object = clazz.newInstance();
        InputStream is = new ByteArrayInputStream(strxml.getBytes());
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);
        Element rootEle = document.getRootElement();
        List<Element> eles = rootEle.elements();

        for ( Element ele: eles ) {
            ReflectionUtils.setFiledValue(object, ele.getName(), ele.getText());
        }
        return object;
    }

}
