package com.JDK.loader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCustom {

	Logger LOG = LoggerFactory.getLogger(TestCustom.class);
	
	
	@Test
	public void testCustomClassLoader(){
		System.out.println("class full path"+CInterfaceImpl.class.getName());
		System.out.println("class simpleName"+CInterfaceImpl.class.getSimpleName());
		System.out.println("class package path"+CInterfaceImpl.class.getPackage().getName());
		
		CInterface cInterface = null;
		String name = CInterfaceImpl.class.getName();
		CustomClassLoader loader = new CustomClassLoader(Thread.currentThread().getContextClassLoader() , name);  
		
        Class<?> clazz = loader.loadClass();  
        /** 
         * 被子加载器加载的类拥有被父加载器加载的类的可见性 
         * Printer是由自定义类加载器加载的， 
         * 而它的父类IPrinter是由系统类加载器加载的， 
         * 因此IPrinter对于Printer具有可见性， 
         * 因此转型成功，并不会因为类加载器不同导致ClassCastException异常 
         */  
        try {
			cInterface = (CInterface) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        /**看看是否热加载成功了**/  
        cInterface.print();  
	}
	
	@Test
	public void test(){
		//String baseDir = "D:\\Workspaces-eclipse\\workspace-ishugui\\com-camelot-util\\target\\classes\\";
        String baseDir = System.class.getResource("/").getPath();
        System.out.println("88");
        System.out.println(baseDir);
        System.out.printf("memeda----%s---%f%n",TestCustom.class.getResource("/").getPath(),0.33);
        System.out.println(System.class.getResource("/").getPath());
//        LOG.info("hahahahh");
	}
}
