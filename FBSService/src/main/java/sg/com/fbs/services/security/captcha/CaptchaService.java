package sg.com.fbs.services.security.captcha;

import java.awt.image.BufferedImage;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;


/**
 * @Author Frank Xu $
 * @Created 11:45:19 am 21 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class CaptchaService extends GenericManageableCaptchaService{

	
	public CaptchaService(CaptchaEngine captchaEngine,int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize) {
		super(captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize);
	}

	@Override
	public Boolean validateResponseForID(String ID, Object response) throws CaptchaServiceException {
		Boolean isValid;
		try {
			 isValid = super.validateResponseForID(ID, response);
		} catch (CaptchaServiceException e) {
			isValid = false;
		}	
		return isValid;
	}

	
}
