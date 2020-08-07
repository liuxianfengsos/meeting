package com.chinasofti.meeting.controller;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1111111111111");
		Random random  = new Random();
		BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		
		String str = "";
		for(int i=0;i<4;i++) {
			String rand = String.valueOf(random.nextInt(10));
			str += rand;
			g.drawString(rand, 13*i+6, 16);
		}
		//System.out.println(str);
		
		g.dispose();
		request.getSession().setAttribute("rand", str);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

}
