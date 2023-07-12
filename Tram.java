/*
 * Author: Annika Leiseth, annika.seaotter@gmail.com
 * Course: CSE 1002, Section 02, Spring 2023
 * Project: Tram
*/
import java.util.Scanner;
class Tram {
   public static void main (final String[] args) {
      // scan in C, N aka capacity & number of stops
      final Scanner sc = new Scanner (System.in);
      int ccurrent;
      ccurrent = sc.nextInt();
      // terminate program if parameters are not met
      if (ccurrent > Math.pow(10, 9) || ccurrent < 0) {
         Runtime.getRuntime().halt(0);
         // found this on a blog as an alternative to System.exit
      }
      final int ctotal = ccurrent;
      final int n = sc.nextInt();
      // terminate program if parameters are not met
      if (n < 2 || n > 100) {
         Runtime.getRuntime().halt(0);
      }
      int entry, exit, wait;
      int ppl = 0;

      for (int i = 0; i < n; i++) {
         // take in data
         exit = sc.nextInt(); 
         entry = sc.nextInt(); 
         wait = sc.nextInt(); 
         // check tram is empty to begin with
         if (i == 0 && exit != 0) {
            System.out.print("impossible");
            break;
         }
         // adjust for ppl entering
         ccurrent = ccurrent - entry;
         ppl = ctotal - ccurrent;
         // check if more people entered than capacity
         if (ppl > ctotal) {
            System.out.print("impossible");
            break;
         }
         // adjust capacity after accounting for exits
         ccurrent = ccurrent + exit;
         // check if more exits than ppl there to exit
         if (exit > ccurrent || exit > ppl) {
            System.out.print("impossible");
            break;
         }
         // check if more people waited than needed
         if (wait <= ccurrent && wait != 0) {
            System.out.print("impossible");
            break;
         }
         ppl = ctotal - ccurrent;
         // when last line reach & no one remaining
         if (i == n-1 && ppl == 0) {
            System.out.print("possible");
         }
      }
   }
}
