package hw1;

public class Problem1 {
    public static void main(String[] args) {
        System.out.print("Input:");
        for (String arg : args) System.out.print(" " + arg);
        System.out.println();
        if(args.length != 1){
            System.out.println("ERROR! Please input 1 argument instead of " + args.length + ".");
            return;
        }
        if (args[0].length() != 11) {
            System.out.println("ERROR! Please input a 11-bit number instead of " + args[0].length() + " bits.");
            return;
        }
        int result = 50;
        char tmp;
        for (int i = 0; i < 11; i++) {
            if (Character.isDigit(tmp = args[0].charAt(i))) {
                if (i % 2 == 0) result -= 3 * Integer.parseInt(String.valueOf(tmp));
                else result -= Integer.parseInt(String.valueOf(tmp));
            } else {
                System.out.println("ERROR! The " + (i + 1) + "th input bit is '" + tmp + "', it's not a digit.");
                return;
            }
        }
        if (result > 9 || result < 0) {
            System.out.println("ERROR! The UPC code is " + result + ", it's invalid.");
            return;
        }
        System.out.print(result + args[0]);
    }
}
