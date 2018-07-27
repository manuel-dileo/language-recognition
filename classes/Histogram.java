import java.util.*;

/** This class allows you to view the model as a series of histograms
@Author Manuel Dileo **/

public class Histogram{
	
	private int width;
	
	/** This constructor creates a histogram where each line has the indicated width. The width must be greater than 2 **/
	public Histogram(int width)throws IllegalArgumentException{
		if(width<=2)
			throw new IllegalArgumentException();
		this.width=width;
			
	}
	
	/** This method creates a String that represents the linguistic model m. **/
	public String toString(LinguisticModel m){
		char[][] graph=new char[26][width];
		int nAsterisks;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<graph.length;i++){
			graph[i][0]=(char)(i+'a');
			graph[i][1]='\t';
		}
		for(int i=0;i<graph.length;i++){
			nAsterisks=(int) (m.relativeFrequence((char)(i+'a'))*1000)/(width-2);
			if(nAsterisks>width-2)
				nAsterisks=width-2;
			for(int j=0;j<nAsterisks;j++)
				graph[i][j+2]='*';
		}
		for(int i=0;i<graph.length;i++){
			for(int j=0;j<graph[0].length;j++)
				sb.append(graph[i][j]);
			sb.append('\n');
		}
		return sb.toString();
	}
	
		
}
