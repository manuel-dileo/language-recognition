import java.util.*;
import java.io.*;

/**This class maintains a list of linguistic models of various languages, and a parallel list of language names. @Author Manuel Dileo **/
public class Trainer{
	
	private ArrayList<LinguisticModel> models;
	private ArrayList<String> languages;

	/** This constructor creates an empty trainer **/
	public Trainer(){
		models=new ArrayList<LinguisticModel>();
		languages=new ArrayList<String>();
	}

	/** If the language 'nameLang' is unknown, this method creates a new linguistic model for that language by training it with the supplied text; if the language is already known, the text is used to further train the corresponding model. **/
	public void train(String nameLang, String text){
		if(!(languages.contains(nameLang))){
			models.add(new LinguisticModel(text));
			languages.add(nameLang);
		}else
			models.get(languages.indexOf(nameLang)).train(text);
	}

	/** This method builds a linguistic model for the past text as an argument, and it compares it to all models of all languages that at this moment the trainer knows. Returns the name of the language with minimum difference.**/
	public String detect(String text){
		LinguisticModel m=new LinguisticModel(text);
		double minDiff=Double.MAX_VALUE;
		double diff;
		int index=-1;
		for(int i=0;i<models.size();i++){
			diff=m.difference(models.get(i));
			if(diff<minDiff){
				minDiff=diff;
				index=i;
			}
		}
		return languages.get(index);
	}

	/** This method prints all the Histogram of all languages the trainer knows, each preceded by the name of the language.**/
	public void print(int width){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<languages.size();i++){
			System.out.println(languages.get(i));
			System.out.println(new Histogram(width).toString(models.get(i)));
		}
	}

	@Override
	public String toString(){
		String aus="Hi! I can detect: ";
		for(int i=0;i<languages.size();i++)
			aus+=languages.get(i) + " | " ;
		return aus;
	}
	
	/** This method reads the text file specified by the argument and stores it into a String **/
	public static String readFile(String filePath){
		StringBuilder sb=new StringBuilder();
		try{
			BufferedReader br= new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			while(line !=null){
				sb.append(line);
				sb.append("\n");
				line=br.readLine();
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return sb.toString();
	}


	public static void main(String[] args){
		Trainer a=new Trainer();
		a.train("ita",Trainer.readFile("../texts/invisible_cities_calvino.txt"));
		a.train("en",Trainer.readFile("../texts/lotr.txt"));
		System.out.println(a.toString());
		String text;
		Scanner in=new Scanner(System.in);
		System.out.println("Insert text");
		while(in.hasNextLine()){
			text=in.nextLine();
			System.out.println("The text I read is: " + a.detect(text));
			System.out.println("Insert text");
		}
		
	}

}
