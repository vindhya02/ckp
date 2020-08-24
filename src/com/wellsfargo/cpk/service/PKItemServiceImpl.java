package com.wellsfargo.cpk.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.cpk.dao.ProductMasterDao;
import com.wellsfargo.cpk.exception.CPKException;
import com.wellsfargo.cpk.model.ProductMaster;


public class PKItemServiceImpl implements PKItemService {

	ProductMasterDao itemDao;

	public PKItemServiceImpl() {
		itemDao = new ProductMasterDao();
	}

	private boolean isIdValid(Integer id) {
		return id > 0;
	}

	private boolean isNameValid(String name) {
		return name != null && (name.length() >= 3 || name.length() <= 20);
	}
	private boolean isDescValid(String desc) {
		return desc != null && (desc.length() >= 3 || desc.length() <= 20);
	}

	private boolean isCostValid(double cost) {
		return cost >= 0;
	}

	private boolean isValidItem(ProductMaster item) throws CPKException {
		List<String> errMsg = new ArrayList<>();

		boolean isValid = true;

		if (!isIdValid(item.getId())) {
			isValid = false;
			errMsg.add("Id can not be null or negative or zero");
		}

		if (!isNameValid(item.getProductName())) {
			isValid = false;
			errMsg.add("Name can not be blank, title must be of 3 to 20 chars in length");
		}

		if (!isDescValid(item.getProductDescription())) {
			isValid = false;
			errMsg.add("Desc can not be blank, title must be of 3 to 20 chars in length");
		}
		
		if (!isCostValid(item.getCost())) {
			isValid = false;
			errMsg.add("cost can not be zero or negative");
		}

		if (!isValid) {
			throw new CPKException(errMsg.toString());
		}

		return isValid;
	}

	@Override
	public ProductMaster validateAndAdd(ProductMaster item) throws CPKException {
		if(item!=null) {
			if(isValidItem(item)) {
				itemDao.add(item);
			}
		}
		return item;
	}

	@Override
	public ProductMaster validateAndSave(ProductMaster item) throws CPKException {
		if(item!=null) {
			if(isValidItem(item)) {
				itemDao.save(item);
			}
		}
		return item;
	}

	@Override
	public boolean deleteItem(int id) throws CPKException {
		return itemDao.deleteById(id);
	}

	@Override
	public ProductMaster getItemById(int id) throws CPKException {
		return itemDao.getById(id);
	}

	@Override
	public List<ProductMaster> getAllItems() throws CPKException {
		return itemDao.getAll();
	}

}
