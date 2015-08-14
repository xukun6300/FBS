package sg.com.fbs.validator.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author Frank Xu $
 * @Created 5:35:47 pm 9 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValidatorMessages implements Serializable, ValidatorMessageHolder, Iterable<ValidatorMessage>{

	private static final long serialVersionUID = -8426922050784093846L;

	protected List<ValidatorMessage> messages = new ArrayList<ValidatorMessage>();
	
	@Override
	public Iterator<ValidatorMessage> iterator() {
		return this.messages.iterator();
	}
	
	public void add(ValidatorMessage message) {
		this.messages.add(message);
	}

}


































