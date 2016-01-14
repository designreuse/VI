package testing;

import org.json.simple.JSONObject;

import controller.AdminCtrl;
import controller.BranchManagerCtrl;
import system.Key;
import model.Admin;
import dataManager.AdminDAO;

public class test {
	public static void main(String[] args){
		
		JSONObject adminJson = new JSONObject();
		adminJson.put(Key.NAME, "admin2");
		adminJson.put(Key.EMAIL, "admin2@email.com");
		adminJson.put(Key.PASSWORD, "admin");
		
		AdminCtrl.createAdmin(adminJson);
		
//		JSONObject bmJson = new JSONObject();
//		bmJson.put(Key.NAME, "xxx");
//		bmJson.put(Key.EMAIL, "xxx@xxx.xxx");
//		bmJson.put(Key.PASSWORD, "xxx");
//		bmJson.put(Key.CONTACT, "xxx");
//		bmJson.put(Key.BRANCHMANAGERNRIC, "xxx");
//		bmJson.put(Key.BRANCHID, "the branch id put here must exist in db, and remove the double quote after you replace with the id");
//		
//		BranchManagerCtrl.createBranchManager(adminJson);
		
	}
}
