package hw1;

import static java.lang.Math.abs;

public class Problem2 {
    public static void main(String[] args) {
        int in = Integer.parseInt(args[0]);
        if (in == 0) {
            return;
        } else if (in < 0) {
            System.out.print("negative ");
            in = abs(in);
        }
        int low = in % 1000, middle = in / 1000 % 1000, high = in / 1000000;
        if (high != 0) {
            print_3digit(high);
            System.out.print(" million ");
        }
        if (middle != 0) {
            print_3digit(middle);
            System.out.print(" thousand ");
        }
        print_3digit(low);
    }

    // print a(a 3-bit decimal number)'s literal form.
    public static void print_3digit(int a) {
        boolean mark = false;
        if (a / 100 != 0) {
            System.out.print(literal_digit(a / 100) + " hundred");
        }
        if ((a %= 100) != 0) print_2digit(a);
    }

    // print a(a 2-bit decimal number)'s literal form.
    public static void print_2digit(int a) {
        if (a >= 20) {
            System.out.print(switch (a / 10) {
                case 2 -> " twenty";
                case 3 -> " thirty";
                case 4 -> " forty";
                case 5 -> " fifty";
                case 6 -> " sixty";
                case 7 -> " seventy";
                case 8 -> " eighty";
                default -> " ninety";
            });
            if (a % 10 != 0) System.out.print(" " + literal_digit(a % 10));
        } else if (a >= 10) {
            System.out.print(switch (a % 10) {
                case 0 -> " ten";
                case 1 -> " eleven";
                case 2 -> " twelve";
                case 3 -> " thirteen";
                case 4 -> " fourteen";
                case 5 -> " fifteen";
                case 6 -> " sixteen";
                case 7 -> " seventeen";
                case 8 -> " eighteen";
                default -> " nineteen";
            });
        } else {
            System.out.print(literal_digit(a));
        }
    }

    // return a string which is a(digit)'s literal form.
    public static String literal_digit(int a) {
        return switch (a) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            default -> "nine";
        };
    }
}
