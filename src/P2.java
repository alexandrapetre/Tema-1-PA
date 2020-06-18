//PROIECTAREA ALGORITMILOR - PETRE ALEXANDRA ELENA  325 CB

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class P2 {
	public static void main(String[] args) throws FileNotFoundException {
		File inFile = new File("p2.in");							//fisier intrare
		PrintWriter writer = new PrintWriter("p2.out"); 			// fisier de iesire
		Scanner sc = null;
		int n,k;
		ArrayList<Integer> numbers = new ArrayList<>();
			
		try {
			sc = new Scanner(inFile);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = sc.nextLine();
		String[] parts = line.split(" ");
		n = Integer.parseInt(parts[0]);
		k = Integer.parseInt(parts[1]);
		line = sc.nextLine();
		parts = line.split(" ");
		
		for(int i = 0; i < n; i++) {
			numbers.add(Integer.parseInt(parts[i]));
		}
			
		Collections.sort(numbers);// am sortat elementele
		Collections.reverse(numbers);//in ordine descrescatoare
			
			
		int result1 = result(numbers,k); //apelez functia ce calculeaza rezultatul final
		writer.print(result1);//scriere in fisier
		writer.close();//inchidere fisier
	}
	
	
	public static int result(ArrayList<Integer> numbers, int k) {
		int[][] dp = new int[k + 1][numbers.size()];//matricea in care se vor stoca pe parcurs rezultatele
		int pas;
		dp[0][0] = numbers.get(0);
		int k1 = 0;
		
		for(int i = 0; i < k + 1; i++) {
			for(int j = i; j < numbers.size(); j++) {
				
				if(i == 0 && j > 0) {
					if(j % 2 == 0) {//prima linie a matricei, pentru i = 0, nu se scot elemente din matrice
						dp[i][j] = dp[i][j-1] + numbers.get(j);//se calculeaza greedy diferenta dintre cei doi
					}else {
						dp[i][j] = dp[i][j-1] - numbers.get(j);
					}
				}else {
					//pe fiecare k linie (k > 0) se elimina k elemente 
					//se calculeaza elementele de pe fiecare line in functie de ce elemente se scot pana la coloana j
						if(j >= k1 && j != 0) {
						pas = j - k1;
						if(pas % 2 == 0) {
							dp[i][j] = Math.max(dp[i-1][j-1], (dp[i][j-1] + numbers.get(j)));
						}else {
							dp[i][j] = Math.max(dp[i-1][j-1], (dp[i][j-1] - numbers.get(j)));
						}
					}
				}
			}
			k1++;
		}

		return dp[k][numbers.size() - 1];
	}
}