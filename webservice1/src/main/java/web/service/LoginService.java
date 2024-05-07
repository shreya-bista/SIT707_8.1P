package web.service;

/**
 * Business logic to handle login functions.
 * 
 * @author Ahsan.
 */
public class LoginService {

	/**
	 * Static method returns true for successful login, false otherwise.
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password, String dob) {
		// Match a fixed user name and password.
		//
		System.out.println("checking login");
		System.out.println(dob);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 // Check for null values
        if (username == null || password == null || dob == null) {
            System.out.println("Input values cannot be null.");
            return false;
        }
		
		boolean isUsernameValid = "shreya".equals(username);
		boolean isPasswordValid = "shreya_pass".equals(password);
		boolean isDobValid = "2024-01-05".equals(dob.trim());

		if (!isUsernameValid) {
		    System.out.println("Username is invalid.");
		} else if (!isPasswordValid) {
		    System.out.println("Password is invalid.");
		} else if (!isDobValid) {
		    System.out.println("Date of birth is invalid.");
		} else {
		    return true;
		}

		return false;
		
		
		
	}
	
	
}
