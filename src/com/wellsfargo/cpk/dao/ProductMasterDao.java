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
import com.wellsfargo.cpk.model.ProductMaster;



public class ProductMasterDao {


	
	public static final String INS_ITEM_QRY = "INSERT INTO pitems(pid,pname,pdesc,cost) values(?,?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE pitems SET pname=?,pdesc=?,cost=? WHERE pid=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM pitems WHERE pid=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT pid,pname,pdesc,cost FROM pitems WHERE pid=?";
	public static final String SEL_ALL_ITEMS_QRY = "SELECT pid,pname,pdesc,cost FROM pitems";

	public ProductMaster add(ProductMaster item) throws CPKException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				pst.setInt(1, item.getId());
				pst.setString(2, item.getProductName());
				pst.setString(3, item.getProductDescription());
				pst.setDouble(4, item.getCost());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CPKException("Saving the item failed!");
			}
		}
		return item;
	}

	public ProductMaster save(ProductMaster item) throws CPKException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY)) {

			
				pst.setString(1, item.getProductName());
				pst.setString(2, item.getProductDescription());
				pst.setDouble(3, item.getCost());
				pst.setInt(4, item.getId());

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CPKException("Saving the item failed!");
			}
		}
		return item;
	}

	public boolean deleteById(Integer id) throws CPKException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_ITEM_QRY)) {

			pst.setInt(1, id);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CPKException("Deleting the item failed!");
		}

		return isDeleted;
	}

	public ProductMaster getById(Integer id) throws CPKException {
		ProductMaster item = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID)) {

			pst.setInt(1,id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				item = new ProductMaster();
				item.setId(rs.getInt(1));
				item.setProductName(rs.getString(2));
				item.setProductDescription(rs.getString(3));
				item.setCost(rs.getDouble(4));
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CPKException("Retrival the item failed!");
		}

		return item;
	}

	public List<ProductMaster> getAll() throws CPKException {
		List<ProductMaster> items = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_ITEMS_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ProductMaster item = new ProductMaster();
				item.setId(rs.getInt(1));
				item.setProductName(rs.getString(2));
				item.setProductDescription(rs.getString(3));
				item.setCost(rs.getDouble(4));
				
				items.add(item);
			}
			
			if(items.isEmpty()) {
				items=null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CPKException("Retrival the item failed!");
		}
		return items;
	}

}
