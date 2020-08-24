package com.wellsfargo.cpk.service;

import java.util.List;

import com.wellsfargo.cpk.exception.CPKException;
import com.wellsfargo.cpk.model.CoronaKit;
import com.wellsfargo.cpk.model.ProductMaster;


public interface PKItemService {

	ProductMaster validateAndAdd(ProductMaster item) throws CPKException;

	ProductMaster validateAndSave(ProductMaster item) throws CPKException;

	boolean deleteItem(int icode) throws CPKException;

	ProductMaster getItemById(int icode) throws CPKException;

	List<ProductMaster> getAllItems() throws CPKException;
}
