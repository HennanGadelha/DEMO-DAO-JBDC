package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println("\n=== TESTE 4: Seller insert =====");
		
		Seller s = new Seller(null, "tobias", "tobias@gmail", new Date(), 4000.0, department);
		sellerDao.insert(s);
		System.out.println("id inserido: " + s.getId());
		
		System.out.println("\n=== TESTE 4: Seller update =====");
		
		s = sellerDao.findById(10);
		
		s.setName("teste");
		sellerDao.update(s);
		System.out.println("updated");
		
		System.out.println("\n=== TESTE 5: Seller insert =====");
		System.out.println("Digite um id pra ser deletado");
		int id = sc.nextInt();
		
		sellerDao.deleteById(id);
		
		sc.close();
		
		
		
	}

}
