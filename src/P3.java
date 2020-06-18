//PROIECTAREA ALGORITMILOR - PETRE ALEXANDRA ELENA 325CB


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class P3 {
    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("p3.in"); //fisier intrare
        PrintWriter writer = new PrintWriter("p3.out"); // fisier de iesire

        Scanner sc = null;
        ArrayList < Integer > numbers = new ArrayList < > ();
        int n;
        long sumAll = 0;

        try {
            sc = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = sc.nextLine();
        String[] parts = line.split(" ");
        n = Integer.parseInt(parts[0]);
        line = sc.nextLine();
        parts = line.split(" ");

        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(parts[i])); //citire din fisier si inserare in ArrayList
            sumAll += numbers.get(i); //suma totala
        }

        long sumT = maxim(numbers); //apelam functia care calculeaza suma lui Tuzgu
        long sumR = (sumAll - sumT); //in functie de suma toatala calculam suma Ritezi 

        writer.print((int) sumT - sumR); //scriere in fisier			
        writer.close(); //inchidere fisier
    }

    public static long maxim(ArrayList < Integer > numbers) {
        long sum1, sum2;
        long max1, max2, max3;
        long[][] dp = new long[numbers.size()][numbers.size()]; //matricea in care calculez pe parcurs sumele obtinute

        for (int step = 0; step < numbers.size(); step++) {//populez matricea in functie de diagonala principala
            for (int i = 0, j = step; j < numbers.size(); i++, j++) {
            	
            	max1 = 0;
            	max2 = 0;
            	max3 = 0;
            	
            	//se verifca sa avem capetele intervalelor valide
            	//daca adversarul mai are elemente de ales la pasul urmator
            	//in functie de alegerea jucatorului
            	if(i + 2 <= j) {
            		max1 = dp[i+2][j];
            	}
            	if(i+1 <= j-1){
            		max2 = dp[i+1][j-1];
            	}
            	if(i <= j - 2) {
            		max3 = dp[i][j-2];
            	}
            	
                sum1 = numbers.get(i) + Math.min(max1, max2);
                sum2 = numbers.get(j) + Math.min(max2, max3);
                // fiecare jucator joaca optim, deci va alege maximul dintre optiunile pe care le are, 
                //dar si in functie de alegerea adversarului

                dp[i][j] = Math.max(sum1, sum2);
            }
        }

        return dp[0][numbers.size() - 1]; //pe parcurul jocului se vor calcula sume partiale iar
        								 //solutia finala este in coltul din dreapta sus al matricei dp.
    }

}