package com.demo.code;

import com.demo.index.IndexController;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.servlet.http.HttpSession;

import com.demo.code.CreateCheckService;

public class VerificationCodeController extends IndexController {

	private static final long serialVersionUID = 1L;
	
	//对验证码进行比对
	public void checkCode(){
		String clientCheckcode = getPara("clientcode").toUpperCase();//接受客户端的参数，实现不区分大小写
		String serverCheckcode = (String)getSession().getAttribute("servercode");//从session中获取生成额验证码
		if(serverCheckcode.equals(clientCheckcode)){
			renderText("验证通过");
		}else{
			renderText("验证不通过");
		}
	}
	
	//生成验证码，并将验证码存入session
	public void createCodeSession(){
		String createTypeFlag = getPara("createTypeFlag");
		HttpSession session = getSession();
		String bufferedImagePath = CreateCheckService.me.createCode(createTypeFlag,session);
		renderFile(new File(bufferedImagePath));
		return;
	}
}
