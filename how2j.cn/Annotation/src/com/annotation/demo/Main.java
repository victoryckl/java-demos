package com.annotation.demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Callable;

@What(description="An annotation")
@MyAnno(str="Annotation Example1", val=1)
public class Main {
	@MyAnno(str="Annotation Example66", val=66)
	public void myMeth() {
		try {
			Method m = Main.class.getMethod("myMeth");
			MyAnno anno = m.getAnnotation(MyAnno.class);
			System.out.println(anno.str()+" -- "+anno.val());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Annotation[] annos = Main.class.getAnnotations();
		for (Annotation a : annos) {
			System.out.println(a);
		}
	}
	
	@MyAnno(val=86)
	public void myMeth2() {
		try {
			Method m = Main.class.getMethod("myMeth2");
			Annotation a = m.getAnnotation(MyAnno.class);
			System.out.println(a.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new Date().toString());
		Main main = new Main();
		main.myMeth();
		main.myMeth2();
		Callable<V>
	}
}
