package com.wellsfargo.cpk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.cpk.exception.CPKException;
import com.wellsfargo.cpk.model.CoronaKit;
import com.wellsfargo.cpk.model.ProductMaster;



public class KitDao {


	
	public static final String INS_ITEM_QRY = "INSERT INTO citems(userid,username,email,address,contact) values(?,?,?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE citems SET address=? WHERE userid=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM citems WHERE userid=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT userid,username,email,address,contact FROM citems WHERE userid=?";
	public static final String SEL_ALL_ITEMS_QRY = "SELECT userid,username,email,address,contact FROM citems";

	public CoronaKit add(CoronaKit item) throws CPKException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				pst.setInt(1, item.getId());
				pst.setString(2, item.getPersonName());
				pst.setString(3, item.getEmail());
				
				pst.setString(4, null);
				
				pst.setString(5, item.getContactNumber());
				

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CPKException("Saving the item failed!");
			}
		}
		return item;
	}

	public CoronaKit save(CoronaKit item) throws CPKException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY)) {

			
				pst.setInt(2, item.getId());
				pst.setString(1, item.getDeliveryAddress());
				
				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CPKException("Saving the item failed!");
			}
		}
		return item;
	}

}
