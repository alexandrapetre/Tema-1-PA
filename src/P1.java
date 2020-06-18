//PROIECTAREA ALGORITMILOR - PETRE ALEXANDRA ELENA 325CB

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class P1 {

    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("p1.in"); //fisier intrare
        PrintWriter writer = new PrintWriter("p1.out"); // fisier de iesire
        Scanner sc = null;
        int n;
        String s;
        ArrayList < Integer > numbers = new ArrayList < > (); //am stocat elementele intr-un ArrayList
        int ok = 0; //ok = 0 este tura lui Tuzgu
        			//ok = 1 este tura Ritzei

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
            numbers.add(Integer.parseInt(parts[i])); //citirea numerelor si inserare in ArrayList
        }

        Collections.sort(numbers); //am sortat elementele din sir
        int sumT = 0;
        int sumR = 0;

        for (int i = n - 1; i >= 0; i--) { //parcurgere de la final la inceput

            if (ok == 0) { //calculam suma lui Tuzgu setez ok = 1 pentru ca
                		  //urmeaza tura Ritzei
                sumT += numbers.get(i);
                ok = 1;
            } else {
            							//calculam suma Ritzei, urmatoarea tura este a lui Tuzgu
                sumR += numbers.get(i); // modificam ok = 0;
                ok = 0;
            }
        }

        writer.println(sumT - sumR); //scrierea rezultatului in fisier
        writer.close(); //inchidere fisier

    }
}