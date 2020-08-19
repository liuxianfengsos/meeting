package com.chinasofti.meeting.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.chinasofti.meeting.dao.CounterDao;

public class CounterListener implements ServletContextListener {
	
	//服务器初始化（开启） 将数据从数据库中读取出来并写入上下文
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctxt = sce.getServletContext();
		CounterDao dao = new CounterDao();
		int visitcount = dao.select();
		ctxt.setAttribute("visitcount", visitcount);
	}
	//关闭服务器前 将数据存入服务器
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext ctxt = sce.getServletContext();
		int visitcount = Integer.parseInt(ctxt.getAttribute("visitcount").toString());
		CounterDao dao = new CounterDao();
		dao.update(visitcount);
	}

}
