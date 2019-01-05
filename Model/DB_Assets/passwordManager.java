// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package DB_Assets;

public class passwordManager {
	
	//Vigenery cipher + Caesar cipher
	
	private static passwordManager PM;
	
	private passwordManager(){
	}
	
	public static passwordManager getinstance() {
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
    
	public double checkPasswordStrength(String Pass){
		
		
		double strength = 0.00;
		int num = 0;
		int spec= 0;
		int cap = 0;

		for(char c : Pass.toCharArray()) {
			
			if( (int)c < 33) { //white space
				return -1;
			}
			else if ((int)c < 48) { //special char
				if(spec == 0) {
					strength += 0.25;
				}
				spec = 1;
				strength += 0.1;
			}
			else if((int)c < 58) { //number
				if(num == 0) {
					strength += 0.15;
				}
				num = 1;
				strength+=0.1; 
			}
			else if((int)c < 65) { //special char
				if(spec == 0) {
					strength += 0.25;
				}
				spec = 1;
				strength += 0.1;
			}
			else if((int)c < 91) { //capital
				if(cap == 0) {
					strength += 0.1;
				}
				cap = 1;
				strength += 0.1;
			}
			else if((int)c < 97) { //special char
				if(spec == 0) {
					strength += 0.25;
				}
				spec = 1;
				strength += 0.1;
			}
			else if((int)c < 123) { //char
				if(cap == 0) {
					strength += 0.1;
				}
				cap = 1;
				strength += 0.1;
			}
			else{ //special char
				if(spec == 0) {
					strength += 0.25;
				}
				spec = 1;
				strength += 0.1;
			}
		}
		
		return strength<=1 ? strength : 1;
	}
    
}

