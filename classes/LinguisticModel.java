import java.util.*;

/** This class provides a linguistic model that tracks how many times each letter
of the alphabet appeared in the analyzed texts (absolute frequency) and how many letters in all they are
views. 
@Author Manuel Dileo
**/

public class LinguisticModel{
	
	private int[] absoluteFrq; //Absolute Frequencies
	private int nLetters;
	private int nTexts;

	/** This method creates an untrained linguistic model on any text. **/
	public LinguisticModel(){
		absoluteFrq=new int[26];
		nLetters=0;
		nTexts=0;
	}

	/** This method creates a linguistic model trained on the given text. **/
	public LinguisticModel(String text){
		absoluteFrq=new int[26];
		this.train(text);
	}
	
	@Override
	public String toString(){
		return "Absolute Frequencies[a-z] : " + Arrays.toString(absoluteFrq) + "\n " + "Total number of letters: " + nLetters + " \n Analyzed texts: " + nTexts;
	}

	/** This method trains the linguistic model on the given text. **/
	public void train(String text){
		text=text.toLowerCase();
		char c;
		for(int i=0;i<text.length();i++){
			c=text.charAt(i);
			if(Character.isLetter(c)){
				if(c-'a'>=0 && c-'a'<absoluteFrq.length){
					absoluteFrq[c-'a']++;
					nLetters++;
				}
			}
		}
		nTexts++;
	}
	
	public int nTexts(){
		return nTexts;
	}

	public int nLetters(){
		return nLetters;
	}

	/**This method returns the absolute frequency of the character c if c it's a lowercase alphabetic letter('a'..'z'). If c is any other character, this method returns -1. **/

	public int absoluteFrequence(char c){
		int aus=-1;
		if(c-'a'>=0 && c-'a'<absoluteFrq.length)
			aus=absoluteFrq[c-'a'];
	 	return aus;
	}

	/** if at least one character has been seen, this method returns the relative frequency of the character (its absolute frequency divided by the total number of characters); otherwise, returns 1.0 / 26. **/
	public double relativeFrequence(char c){
		double aus=1.0/26;
		if(nLetters()>0)
			aus=(double)(absoluteFrequence(c))/nLetters;
		return aus;
	}

	/** This method calculates the difference in norm L2 between this linguistic model and the linguistic model m **/
	public double difference(LinguisticModel m){
		double sum=0;
		double diff;
		for(int i=0;i<absoluteFrq.length;i++){
			diff=this.relativeFrequence((char)(i+'a'))-m.relativeFrequence((char)(i+'a'));
			diff*=diff;
			sum+=diff;
		}
		return Math.sqrt(sum);
	}
}
