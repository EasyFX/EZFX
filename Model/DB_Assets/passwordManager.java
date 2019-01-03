// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package DB_Assets;

public class passwordManager {
	
	//Vigenery cipher + Caesar cipher
	
	private static passwordManager PM;
	
	private passwordManager(){
	}
	
	public passwordManager getinstance() {
		return PM == null ? PM = new passwordManager() : PM;
	}
	
	public String encrypt(String password, String key, int shift){
		return encryptVigenery(encryptCaesar(password,shift), key);
	}
	public String decrypt(String text, String key, int shift){
		return decryptVigenery(decryptCaesar(text,shift), key);
	}
	
	
    private String encryptVigenery(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
    
    private String decryptVigenery(String text, final String key) {
    	String res = "";
    	text = text.toUpperCase();
    	for (int i = 0, j = 0; i < text.length(); i++) {
    		char c = text.charAt(i);
    		if (c < 'A' || c > 'Z') continue;
    		res += (char)((c - key.charAt(j) + 26) % 26 + 'A');
    		j = ++j % key.length();
		}
	return res;
	}

	public static String encryptCaesar(String text, int shift) {
		
		char[] buffer = text.toCharArray();
	
		for (int i = 0; i < buffer.length; i++) {
	
			char letter = buffer[i];
			if(letter != ' ') {
				letter = (char) (letter + shift);
				if (letter > 'z') {
					letter = (char) (letter - 26);
				} else if (letter < 'a') {
					letter = (char) (letter + 26);
				}
			}
			buffer[i] = letter;
	    }
	    return new String(buffer);
	}
	
	public static String decryptCaesar(String text, int shift) {
		
		shift -=26;
		
		char[] buffer = text.toCharArray();
	
		for (int i = 0; i < buffer.length; i++) {
	
			char letter = buffer[i];
			if(letter != ' ') {
				letter = (char) (letter + shift);
				if (letter > 'z') {
					letter = (char) (letter - 26);
				} else if (letter < 'a') {
					letter = (char) (letter + 26);
				}
			}
			buffer[i] = letter;
	    }
	    return new String(buffer);
	}
    

    
}

