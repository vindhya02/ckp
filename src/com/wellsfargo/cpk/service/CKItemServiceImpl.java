package com.wellsfargo.cpk.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.cpk.dao.KitDao;
import com.wellsfargo.cpk.dao.ProductMasterDao;
import com.wellsfargo.cpk.exception.CPKException;
import com.wellsfargo.cpk.model.CoronaKit;


public class CKItemServiceImpl implements CKItemService {

	KitDao itemDao;

	public CKItemServiceImpl() {
		itemDao = new KitDao();
	}

	private boolean isIdValid(Integer id) {
		return id > 0;
	}

	private boolean isNameValid(String name) {
		return name != null && (name.length() >= 3 || name.length() <= 20);
	}
	private boolean isEmailValid(String desc) {
		return desc != null && (desc.length() >= 6 || desc.length() <= 20);
	}

	private boolean isContactValid(String contact) {
		return contact != null && contact.length() == 10;
	}

	private boolean isValidItem(CoronaKit item) throws CPKException {
		List<String> errMsg = new ArrayList<>();

		boolean isValid = true;

		if (!isIdValid(item.getId())) {
			isValid = false;
			errMsg.add("Id can not be null or negative or zero");
		}

		if (!isNameValid(item.getPersonName())) {
			isValid = false;
			errMsg.add("Name can not be blank, title must be of 3 to 20 chars in length");
		}

		if (!isEmailValid(item.getEmail())) {
			isValid = false;
			errMsg.add("Email can not be blank, title must be of 3 to 20 chars in length");
		}
		
		if (!isContactValid(item.getContactNumber())) {
			isValid = false;
			errMsg.add("Contact can not be blank");
		}

		if (!isValid) {
			throw new CPKException(errMsg.toString());
		}

		return isValid;
	}

	public CoronaKit cvalidateAndAdd(CoronaKit item) throws CPKException {
		if(item!=null) {
			if(isValidItem(item)) {
				itemDao.add(item);
			}
		}
		return item;
	}

	public CoronaKit cvalidateAndSave(CoronaKit item) throws CPKException {
		if(item!=null) {
			{
				itemDao.save(item);
			}
		}
		return item;
	}
}
