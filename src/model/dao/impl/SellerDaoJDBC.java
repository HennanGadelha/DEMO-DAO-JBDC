package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDAO {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {

		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT seller.*,department.Name as DepName ";
			sql += "FROM seller INNER JOIN department ";
			sql += "ON seller.DepartmentId = Department.Id ";
			sql += "WHERE seller.Id = ?";

			st = conn.prepareStatement(sql);

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {

				Department department = instantiateDepartment(rs);

				Seller seller = instantiateSeller(rs, department);

				return seller;

			}

			return null;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeResultset(rs);

		}

	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		// TODO Auto-generated method stub
		Seller seller = new Seller();

		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));

		seller.setDepartmente(department);

		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));

		return department;
	}

	@Override
	public List<Seller> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT seller.*,department.Name as DepName ";
			sql += "FROM seller INNER JOIN department ";
			sql += "ON seller.DepartmentId = department.Id ";
			sql += "ORDER BY Name";

			st = conn.prepareStatement(sql);

			rs = st.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> deps = new HashMap<>();

			while (rs.next()) {

				Department dep = deps.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs);
					deps.put((rs.getInt("DepartmentId")), dep);

				}

				Seller seller = instantiateSeller(rs, dep);

				sellers.add(seller);

			}

			return sellers;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultset(rs);

		}

	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT seller.*,department.Name as DepName ";
			sql += "FROM seller INNER JOIN department ";
			sql += "ON seller.DepartmentId = department.Id ";
			sql += "WHERE DepartmentId = ? ";
			sql += "ORDER BY Name";

			st = conn.prepareStatement(sql);
			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> deps = new HashMap<>();

			while (rs.next()) {

				Department dep = deps.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs);
					deps.put((rs.getInt("DepartmentId")), dep);

				}

				Seller seller = instantiateSeller(rs, dep);

				sellers.add(seller);

			}

			return sellers;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultset(rs);

		}

	}

}
