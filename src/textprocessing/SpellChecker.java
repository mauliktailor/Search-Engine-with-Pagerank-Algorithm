package textprocessing;

import java.util.Set;

public class SpellChecker {
	
	public static void checkRepeatLetters(String mistake, int stringIndex, int recurseCount, String suggestion, boolean found, Set<String> dict) {
		if (recurseCount > 2)
			return;
	
		for (int i = stringIndex; i < mistake.length() - 1; i++) {

			if (mistake.charAt(i) == mistake.charAt(i + 1) && mistake.charAt(i) != 'a' && mistake.charAt(i) != 'e'
					&& mistake.charAt(i) != 'i' && mistake.charAt(i) != 'o' && mistake.charAt(i) != 'u') {

				if (checkVowels(mistake, 0, suggestion, found, dict))
					return;

				checkRepeatLetters(mistake, i + 1, recurseCount + 1, suggestion, found, dict);

				if (!found)
				{
					String singled = mistake.substring(0, i) + mistake.substring(i + 1, mistake.length());
					if (checkVowels(singled, 0, suggestion, found, dict))
						return;
					checkRepeatLetters(singled, i + 1, recurseCount + 1, suggestion, found, dict);
				}
			}
		}
		return; 
	}

	public static boolean checkVowels(String mistake, int stringIndex, String suggestion, boolean found, Set<String> dict) {
		String[] vowels = { "a", "e", "i", "o", "u", "a", "e", "i", "o", "u" };

		for (int i = stringIndex; i < mistake.length(); i++) {

			if (mistake.charAt(i) == 'a' || mistake.charAt(i) == 'e' || mistake.charAt(i) == 'i'
					|| mistake.charAt(i) == 'o' || mistake.charAt(i) == 'u') {
				for (int j = 0; j < 5; j++) {
					if (mistake.substring(i, i + 1).equals(vowels[j])) {
						int localIndex = j;
						for (int k = 0; k < 5; k++) {
							mistake = mistake.substring(0, i) + vowels[localIndex]
									+ mistake.substring(i + 1, mistake.length());
							if (checkSuggestion(mistake, suggestion, found, dict))
								return true;
							checkVowels(mistake, i + 1, suggestion, found, dict);
							localIndex++;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkIfDouble(String word) {
		for (int i = 0; i < word.length() - 1; i++) {
			if (word.charAt(i) == word.charAt(i + 1) && word.charAt(i) != 'a' && word.charAt(i) != 'e'
					&& word.charAt(i) != 'i' && word.charAt(i) != 'o' && word.charAt(i) != 'u') {
				return true;
			}
		}
		return false;
	}

	/**
	 * Reduce any occurrence of 3 repeated characters to 2 repeated characters
	 */
	public static String removeTripleRepeat(String s) {
		for (int i = 0; i < s.length() - 2; i++) {
			if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == s.charAt(i + 2)) {
				s = s.substring(0, i) + s.substring(i + 1, s.length());
				i--;
			}
		}
		return s;
	}
	
	/**
	 * search dictionary to see if a word can be a suitable suggestion must be
	 * in dictionary to be valid suggestion Only processes one suggestion
	 */
	public static boolean checkSuggestion(String word, String suggestion, boolean found, Set<String> dict) {
		if (!found) {
			if (dict.contains(word)) {
				suggestion = word;
				found = true;
				return true;
			} else
				return false;
		} else {
			return false;
		}
	}
}
