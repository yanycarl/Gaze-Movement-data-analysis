package multimodule_analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import Jama.Matrix;

public class Main {
	public static String readFile(String fileName) throws Exception {
        BufferedReader bufread;  
        String read;
        StringBuilder readAll = new StringBuilder();  
        File file = new File(fileName);  
        bufread = new BufferedReader(new FileReader(file));   
        while ((read = bufread.readLine()) != null) {  
            readAll.append(read); 
        }  
        bufread.close();  
        read=readAll.toString();
		return read;  
	}
	
	
	public static void main(String[] args) throws Exception {

		HashMap<String, Object> m=new HashMap<String, Object>();
		String s;
		int j=0;
		int Counter=0;
		int i=0;
		s=readFile("1.tsv");
		Counter=1;
		i=i+1;
		j=0;
		int k=-1;
		int l=0;

		Matrix m2=new Matrix(6995,19);
		
		for(;i<s.length();i++) {
			
			if(s.charAt(i+1)=='	'&&s.charAt(i+2)=='('){
				i=i+2;
			}
			else if(s.charAt(i+1)=='	'&&s.charAt(i+2)=='0') {
				break;
			}
			else if(s.charAt(i+1)=='	'&&s.charAt(i+2)!='(') {
				if(Counter==27 || Counter==45 || Counter==49 || Counter==50 
						|| Counter==51 || Counter==52 || Counter==53 || Counter==54
						|| Counter==57 || Counter==58 || Counter==59 || Counter==60
						|| Counter==61 || Counter==62 || Counter==77 || Counter==78
						|| Counter==80 || Counter==84 || Counter==87) {
					m.put(s.substring(j, i+1),Counter);
				}
				Counter++;
				j=i+2;
			}
		}
		
		Counter=0;
		s=s.replaceAll("\n", "	");
		
		for(;i<s.length()-1;i++) {
				if(s.charAt(i)=='	') {
					if(Counter==87) {
						Counter=1;
						l++;
						k=-1;
					}
					else {
						Counter++;
						if(Counter==27 || Counter==45 || Counter==49 || Counter==50 
								|| Counter==51 || Counter==52 || Counter==53 || Counter==54
								|| Counter==57 || Counter==58 || Counter==59 || Counter==60
								|| Counter==61 || Counter==62 || Counter==77 || Counter==78
								|| Counter==80 || Counter==84 || Counter==87) {
								k++;
						}
					}
				}
				
				if(s.charAt(i)=='	' && s.charAt(i+1)!='	') {
					j=i+1;
				}
				if(s.charAt(i)!='	'&&s.charAt(i+1)=='	') {
					if(Counter==27 || Counter==45 || Counter==49 || Counter==50 
							|| Counter==51 || Counter==52 || Counter==53 || Counter==54
							|| Counter==57 || Counter==58 || Counter==59 || Counter==60
							|| Counter==61 || Counter==62 || Counter==77 || Counter==78
							|| Counter==80 || Counter==84 || Counter==87) {
							m2.set(l,k, Double.valueOf(s.substring(j,i+1)).doubleValue());
					}
				}
		}
		System.out.println(m);
		m2.getMatrix(0, 99, 1, 1).print(6, 2);
	return;
	}
}
