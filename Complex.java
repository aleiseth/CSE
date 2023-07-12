/*
 * Author: Annika Leiseth, aleiseth2018@my.fit.edu
 * Course: CSE 1002, Section 02, Spring 2023
 * Project: Newtons Chaos
*/

record Complex (double real, double image) {
   // allows main class to use individual components of Complex record
   public double getReal () {
      return this.real;
   }
   public double getImage () {
      return this.image;
   }
   // calculate absolute value of 1 complex number
   public static double abs (final Complex a) {
      final double square = Math.pow(a.real, 2) + Math.pow(a.image, 2);
      final double abs = Math.sqrt(square);
      return abs;
   }
   // add  complex numbers
   public static Complex plus (final Complex a, final Complex b) {
      final double addreal = a.real + b.real;
      final double addimage = a.image + b.image;
      final Complex plus = new Complex(addreal, addimage);
      return plus;
   }
   // multiply a complex number by a constant
   public static Complex constant (final Complex a, final double m) {
      final double timesreal = a.real*m;
      final double timesimage = a.image*m;
      final Complex times = new Complex(timesreal, timesimage);
      return times;
   }
   // multiply 2 complex numbers
   public static Complex times (final Complex a, final Complex b) {
      final double timesreal = a.real*b.real - a.image*b.image;
      final double timesimage = a.real*b.image + a.image*b.real;
      final Complex times = new Complex(timesreal, timesimage);
      return times;
   }
   // subtract 2 complex numbers and/or a constant
   public static Complex minus (final Complex a, final Complex b, final double x) {
      final double subreal = a.real - b.real - x;
      final double subimage = a.image - b.image;
      final Complex minus = new Complex(subreal, subimage);
      return minus;
   }
   // divide 2 complex numbers
   public static Complex divides (final Complex a, final Complex b) {
      final double divreal = ((a.real*b.real)+(a.image*b.image))
            /(Math.pow(b.real, 2)+Math.pow(b.image, 2));
      final double divimage = ((b.real*a.image)-(a.real*b.image))
            /(Math.pow(b.real, 2)+Math.pow(b.image, 2));
      final Complex divides = new Complex(divreal, divimage);
      return divides;
   }
   // take a complex number to a power n
   public static Complex power (final Complex a, final int n) {
      Complex power = a;
      for (int i = 1; i < n; i++) {
         power = times(power, a);
      }
      return power;
   }
   // test runs
   public static void tests () {
      // 1. The absolute value (the magnitude, distance from origin) of 1-2i
      final Complex test1 = new Complex(1, -2);
      final double ans1 = abs(test1);
      System.out.printf("%.3f%n", ans1);
      // 2. The sum of 4-3i and 2+9i
      final Complex test2a = new Complex(4, -3);
      final Complex test2b = new Complex(2, 9);
      final Complex ans2 = plus(test2a, test2b);
      System.out.print(ans2.real+ " + "+ ans2.image+"i"+"\n");
      // 3. The product of... same conditions as (2)
      final Complex ans3 = times(test2a, test2b);
      System.out.print(ans3.real+ " + "+ ans3.image+"i"+"\n");
      // 4. The difference of...same conditions as (2)
      final Complex ans4 = minus(test2a, test2b, 0);
      System.out.print(ans4.real+ " + "+ ans4.image+"i"+"\n");
      // 5. The quotient of...same conditions as (2)
      final Complex ans5 = divides(test2a, test2b);
      System.out.printf("%.3f%s%.3f%s%n", ans5.real, " + ", ans5.image, "i");
      // 6. 7 - 3.3i to power of 3
      final Complex test6 = new Complex(7, -3.3);
      final Complex ans6 = power(test6, 3);
      System.out.printf("%.3f%s%.3f%s%n", ans6.real, " + ", ans6.image, "i");
   }
}
