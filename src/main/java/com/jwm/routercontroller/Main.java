package com.jwm.routercontroller;

import com.jwm.routercontroller.util.StringArgParserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main 
{
	public static String[] args;
    public static void main( String[] args )
    {
		Main.args = args;
		new ClassPathXmlApplicationContext("beans.xml");
    }
}
