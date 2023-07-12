/*
 * Author: Annika Leiseth, aleiseth2018@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2023
 * Project: Molecular Weight
*/
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;
import java.math.BigDecimal;
import java.util.ArrayList;

public final class MolecularWeight {
   // establish record to hold Element symbol & weight
   record Elements (String symbol, String weight){
   }
   public static void main (final String[] args) throws IOException {

      // open path to elements file
      final Path path;
      if (!args[0].isEmpty()) {
         final String file = args[0];
         path = Paths.get (file);
      } else {
         path = Paths.get ("file.csv");
      }
      // scanner for elements file
      final Scanner scanner = new Scanner (path, "US-ASCII");
      // initialize array list
      final ArrayList<Elements> arr = new ArrayList<>();
      scanner.nextLine();
      while (scanner.hasNextLine()) {
         // split file up into array by comma separation
         final String[] tokens = scanner.nextLine().split(",");
         // make record for each line, adding symbol & weight
         final Elements temp = new Elements(tokens[2], tokens[3]);
         // add records to array list
         arr.add(temp);
      }
      // take standard input for chemical eqs
      final Scanner sc = new Scanner (System.in, "US-ASCII");
      String eq = "";
      while (sc.hasNextLine()) {
         eq = sc.nextLine();
         // for each eq, send to solve method
         // to determine weight
         solve(eq, arr);
      }
   }
   private static void solve (final String eq, final ArrayList<Elements> arr) {
      // get space separated elements of eq string
      // put into an array "tokens"
      final String[] tokens = eq.split(" ");
      final int size = tokens.length;
      // initialization
      String symbol = "";
      String mass = "";
      int multiplier = 0;
      BigDecimal sum = new BigDecimal("0");
      BigDecimal total = new BigDecimal("0");

      // traverse the equation 
      for (int i = 0; i < size; i++) {

         // find array string element in char
         // to be able to determine if it is a digit
         final char temp = tokens[i].charAt(0);

         // is character a digit?
         if (!Character.isDigit(temp)) {
            symbol = tokens[i];

            // find element & its weight
            mass = find(symbol, arr);

            // check the next index to see
            // if there is a multiplier following
            for (int j = i; j <= size -1; j++) {

               final char temp2 = tokens[j].charAt(0);

               // if yes, that number is the multiplier
               // if no, the multiplier = 1
               if (Character.isDigit(temp2)) {
                  multiplier = Character.getNumericValue(temp2);
               } else if (!Character.isDigit(temp2)) {
                  multiplier = 1;
               }
            }
         }
         // try to add mass * multiplier
         try {
            sum = add(mass, multiplier);
            total = total.add(sum);
         } catch (NumberFormatException e) {
            System.out.print("Molecular weight of "+eq+" = ");
            System.out.print("??");
         }
         // reset the multiplier
         multiplier = 1;

      }
      final  BigDecimal zero = new BigDecimal("0");

      // print out molecular weight if element
      // is greater than zero
      // because exceptions will = 0
      if (total.compareTo(zero) > 0) {
         System.out.print("Molecular weight of "+eq+" = ");
         System.out.print(total);
      }
   }
   private static String find (final String name, final ArrayList<Elements> arr) {
      String mass = "";
      // find where array Element symbol
      // equals the name being passed in
      for (int i = 0; i < arr.size(); i++) {
         final Elements test = arr.get(i);
         // if it finds a match,
         // find the weight in the Element record
         if (test.symbol.equals(name)) {
            mass = test.weight;
         }

      }
      return mass;
   }

   private static BigDecimal add (final String mass, final int multiplier) {

      final BigDecimal atom = new BigDecimal(mass); // mass
      final BigDecimal mult = new BigDecimal(multiplier); // multiplier
      // mass * multiplier
      final BigDecimal total = atom.multiply(mult);

      return total;
   }
}
