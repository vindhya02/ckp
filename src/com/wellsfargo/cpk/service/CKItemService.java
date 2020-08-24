package com.wellsfargo.cpk.service;

import java.util.List;

import com.wellsfargo.cpk.exception.CPKException;
import com.wellsfargo.cpk.model.CoronaKit;
import com.wellsfargo.cpk.model.ProductMaster;


public interface CKItemService {

	CoronaKit cvalidateAndAdd(CoronaKit item) throws CPKException;

	CoronaKit cvalidateAndSave(CoronaKit item) throws CPKException;

	/**boolean deleteItem(int icode) throws ImsException;

	ProductMaster getItemById(int icode) throws ImsException;

	List<ProductMaster> getAllItems() throws ImsException;**/
}
