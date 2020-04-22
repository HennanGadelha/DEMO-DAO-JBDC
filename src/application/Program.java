package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SellerDAO sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== TESTE 1: Seller by id =====");
		Seller seller = sellerDao.findById(3);

		System.out.println(seller);

		System.out.println("\n=== TESTE 2: Seller findBydepartment =====");

		Department department = new Department(2, null);

		List<Seller> sellers = sellerDao.findByDepartment(department);

		for (Seller s : sellers) {

			System.out.println(s);
		}

		System.out.println("\n=== TESTE 3: Seller findBydepartment =====");

	
		List<Seller> sellerss = sellerDao.findAll();

		for (Seller s : sellerss) {

			System.out.println(s);
		}
		
		
	}

}
