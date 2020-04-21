package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDAO {

	void insert(Department department);
	
	void update(Department department);
	
	void deleteById (Integer id);

	Department findbyid(Integer id);
	
	List<Department> findAll();
	
}
