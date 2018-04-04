package com.utils.file;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * Description : [类描述]
 * Created on : 2017年10月17日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class ReadWriteBean {


    private static String pathName = "d:/bean.txt";

    public static void writeObjectToFile(Object obj)
    {
        File file =new File(pathName);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }

    public static Object readObjectFromFile()
    {
        Object temp=null;
        File file =new File(pathName);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static void main(String[] args) {

        Cirle cirle = new Cirle(0.01F, 5);
        cirle.setName("name");
        writeObjectToFile(cirle);
        Cirle o = (Cirle)readObjectFromFile();
        System.out.println(o);

    }


}

@Setter
@Getter
class Shape{
    public String name;
}

@Setter
@Getter
class Cirle extends Shape implements Serializable {
    private float radius;
    transient int color;
    public static String type = "Circle";

    public Cirle(float radius, int color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}