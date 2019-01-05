// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package DB_Assets;

public class User {

	private String name;
	private String email;
	private String pref_path;
	private String password;
	private static boolean newsLetter;
	private static boolean remindMe;
	
	public boolean isSet = false;
	
	private User() {
	}
	
    private static class LazyHolder {
        static final User INSTANCE = new User();
    }
    
    public static User getInstance(String name, String email, String pref_path, String password, boolean newsletter, boolean remindme) {
		LazyHolder.INSTANCE.setName(name);
		LazyHolder.INSTANCE.setEmail(email);
		LazyHolder.INSTANCE.setPref_path(pref_path);
		LazyHolder.INSTANCE.setPassword(password);
		User.setNewsLetter(newsletter);
		User.setremindMe(remindme);
		LazyHolder.INSTANCE.isSet = true;
    	
    	return LazyHolder.INSTANCE;
    }
    
    public static void unSet() {
    	LazyHolder.INSTANCE.isSet = false;
    }
    
    public static boolean isSet() {
    	return LazyHolder.INSTANCE.isSet;
    }

	public static String getPref_path() {
		return LazyHolder.INSTANCE.pref_path;
	}

	private void setPref_path(String pref_path) {
		this.pref_path = pref_path;
	}

	public static String getPassword() {
		return LazyHolder.INSTANCE.password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public static String getEmail() {
		return LazyHolder.INSTANCE.email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	public static String getName() {
		return LazyHolder.INSTANCE.name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public static boolean getNewsLetter() {
		return newsLetter;
	}
	
	private static void setNewsLetter(boolean value) {
		User.newsLetter = value;
	}
	
	public static boolean getremindMe() {
		return remindMe;
	}
	
	private static void setremindMe(boolean value) {
		User.remindMe = value;
	}
	
	
	
	
    
}
