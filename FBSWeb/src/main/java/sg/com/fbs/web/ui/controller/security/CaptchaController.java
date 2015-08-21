package sg.com.fbs.web.ui.controller.security;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.com.fbs.services.security.captcha.CaptchaService;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

	private static final Logger logger = Logger.getLogger(CaptchaController.class);
	
	@Autowired
	private CaptchaService captchaService;
	
	@RequestMapping(value="/showCaptcha", method=RequestMethod.GET)
	public void showCaptcha(HttpServletRequest request, HttpServletResponse response){
		ByteArrayOutputStream captchaOutputStream = new ByteArrayOutputStream();
		BufferedImage challenge = captchaService.getImageChallengeForID(request.getSession().getId(),request.getLocale());
	    
		try {
			ImageIO.write(challenge, "jpeg", captchaOutputStream);
		} catch (IOException e) {
			logger.error("I/O exception occured.", e);
		}
		
		response.setHeader("Cache-Control", "no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("image/jpeg");
	    
	    try {
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(captchaOutputStream.toByteArray());
			outputStream.close();
		} catch (IOException e) {
			logger.error("I/O exception occured.", e);
		}
	}
	
	
}
