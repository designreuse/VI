package testing;

import model.Admin;
import dataManager.AdminDAO;

//this is a test comment
public class test {
	public static void main(String[] args){
	Admin admin = new Admin();
	AdminDAO.addAdmin(admin);
	}
}
