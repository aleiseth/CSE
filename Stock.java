/*
 * Author: Annika Leiseth, aleiseth@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2023
 * Project: Stock
*/
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;


class Stock {
   public static void main (final String[] args) {
      final Scanner sc = new Scanner (System.in, "US-ASCII");
      // skip header
      sc.nextLine();
      // create array lists for dates and adjusted close values
      final ArrayList<String> dates = new ArrayList<>();
      final ArrayList<String> arr = new ArrayList<>();
      // use comma seperation to access certain index values
      while (sc.hasNextLine()) {
         final String line = sc.next();
         final String[] tokens = line.split(",");
         final String date = tokens[0];
         final String adjc = tokens[5];
         // add each value to array, using while loop
         dates.add(date);
         arr.add(adjc);
      }
      getValues(arr, dates);
   }
   // get values for probabilities
   private static void getValues (final ArrayList<String> arr, final ArrayList<String> dates) {
      final ArrayList<BigDecimal> gain = new ArrayList<>();
      for (int i = 1; i < arr.size(); i++) {
         final String temp = arr.get(i-1);
         final String temp2 = arr.get(i);
         final BigDecimal day1 = new BigDecimal(temp);
         final BigDecimal day2 = new BigDecimal(temp2);
         final BigDecimal prob = calc(day1, day2);
         gain.add(prob);
      }
      compare(gain, dates);

   }
   // calculate probability
   // ((y-x)/y)*100%
   private static BigDecimal calc (final BigDecimal day1, final BigDecimal day2) {

      final BigDecimal diff = day2.subtract(day1);
      final BigDecimal diff2 = diff.divide(day2, RoundingMode.DOWN);
      final BigDecimal percent = new BigDecimal("100");
      final BigDecimal diff3 = diff2.multiply(percent);
      return diff3;

   }
   // compare each day to find max gain and loss
   private static void compare (final ArrayList<BigDecimal> gain, 
         final ArrayList<String> dates) {
      final BigDecimal max = Collections.max(gain);
      final BigDecimal min = Collections.min(gain);
      int maxday = 0;
      int minday = 0;
      for (int i = 1; i < gain.size(); i++) {
         if (gain.get(i).equals(max)) {
            maxday = i;
         } else if (gain.get(i).equals(min)) {
            minday = i;
         }
   
      }
      // put days & percent values in strings
      final String maxdate = dates.get(maxday+1);
      final String mindate = dates.get(minday+1);
      final BigDecimal mgain = gain.get(maxday);
      final BigDecimal mloss = gain.get(minday);
      final String newmaxdate = format(maxdate);
      final String newmindate = format(mindate);
      
      //formatted output
      System.out.printf("%s %.2f %s %s%n", "Max gain: ", mgain, "on", newmaxdate);
      System.out.printf("%s %.2f %s %s%n", "Max loss: ", mloss, "on", newmindate);
      streak(gain, dates);

   }
   // determine the streak
   private static void streak (final ArrayList<BigDecimal> gain, 
         final ArrayList<String> dates) {

      int index = 0, streak = 0, temp = 0, currentIdx = 0;
      final BigDecimal zero = new BigDecimal("0");
      // compare current max to the next
      for (int i = 0; i < gain.size(); i++) {
         if (gain.get(i).compareTo(zero) > 0) {
            temp++;
            if (temp == 1) {
               currentIdx = i;
            }
         } else {
            if (temp > streak) {
               streak = temp;
               index = currentIdx;
            }
            temp = 0;
         }
      }
      if (temp > streak) {
         streak = temp;
         index = currentIdx;
      }

      final String startdate = dates.get(index+1);
      final String enddate = dates.get(index+streak);
      final String newstart = format(startdate);
      final String newend = format(enddate);
      // print out the formatted streak
      if (streak > 0) {
         System.out.printf("%s %d %s", "Longest growth streak:", streak, "days");
         System.out.printf("%s%s %s %s%s", " (", newstart, "to", newend, ")");
      } else {
         System.out.printf("%s %d %s", "Longest growth streak: 0 days");
      }
      return;
   }
   // date formatting
   private static String format (final String date) {
      final DateTimeFormatter old = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      final DateTimeFormatter newform = DateTimeFormatter.ofPattern("E, dd MMM yyyy");
      String newdate = "";
      final TemporalAccessor d = old.parse(date);
      newdate = newform.format(d);

      return newdate;
   }
}
