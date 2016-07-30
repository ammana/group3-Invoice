package dataManagement;

import java.util.HashMap;
import basicClasses.UserProfile;

public class SystemData {
  
        
    private UserProfile currentUser;
    private HashMap<String, String> userCredentials;   	

    public HashMap<String, String> getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(HashMap<String, String> userCredentials) {
        this.userCredentials = userCredentials;
    }  
    
    
    public UserProfile getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserProfile currentUser) {
        this.currentUser = currentUser;
    }   
  
}
