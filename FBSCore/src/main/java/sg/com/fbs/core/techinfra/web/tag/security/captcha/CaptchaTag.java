package sg.com.fbs.core.techinfra.web.tag.security.captcha;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 10:23:55 am 21 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class CaptchaTag extends SimpleTagSupport{

	@Setter
	@Getter
	private String captchaImageUrl;
	
	@Setter
	@Getter
	private String captchaRefreshImageUrl;
	
	@Override
	public void doTag() throws JspException, IOException {
		String captchaImgElement = "<img alt=\"Captcha\" id=\"captchaImage\" name=\"captchaImage\" src=\""+captchaImageUrl+"\">";
		String refreshCaptchaImgElement = "<img alt=\"Refresh Captcha\" src=\"" + captchaRefreshImageUrl + "\" onclick=\"document.getElementById('captchaImage').src='"+captchaImageUrl +"?'+new Date().getTime();\"><br/>";
		String captchaChallengeElement = "<input name=\"captchaResponse\" id=\"captchaResponse\" placeholder=\"Captcha\" class=\"jq_watermark\" maxlength=\"10\" >";
		StringBuilder captchaHtml = new StringBuilder();
		captchaHtml.append(captchaImgElement);
		captchaHtml.append(refreshCaptchaImgElement);
		captchaHtml.append(captchaChallengeElement);
		getJspContext().getOut().write(captchaHtml.toString());
	}
	
}
