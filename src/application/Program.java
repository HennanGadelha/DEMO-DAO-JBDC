package application;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SellerDAO sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TESTE 1: Seller by id =====");
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		
	}

}
