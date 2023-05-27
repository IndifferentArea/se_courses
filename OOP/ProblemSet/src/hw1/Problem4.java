package hw1;

public class Problem4 {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[1]);
        if (m == 0) return;
        switch (args[0]) {
            case "a" -> print_a(m);
            case "b" -> print_b(m);
            case "c" -> print_c(m);
            case "d" -> print_d(m);
            case "e" -> print_e(m);
            default -> System.out.print("The 1st argument is incorrect.");
        }
    }

    /*
     * print figure in a form with n*n width.
     */
    public static void print_a(int n) {
        int m = 2 * n - 1;
        boolean[][] pixel = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0 || j == m - 1) System.out.print("#");
                else if ((i == 0 || i == n - 1) && j % 2 == 0) System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
     * print figure in b form with n*n width.
     */
    public static void print_b(int n) {
        int m = 2 * n - 1;
        boolean[][] pixel = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || i == n - 1 || j / 2 == i) && (j % 2 == 0))
                    System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
     * print figure in c form with n*n width.
     */
    public static void print_c(int n) {
        int m = 2 * n - 1;
        boolean[][] pixel = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || i == n - 1 || (m - j) / 2 == i) && (j % 2 == 0))
                    System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
     * print figure in d form with n*n width.
     */
    public static void print_d(int n) {
        int m = 2 * n - 1;
        boolean[][] pixel = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || i == n - 1 || (m - j) / 2 == i || j / 2 == i) && (j % 2 == 0))
                    System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
     * print figure in e form with n*n width.
     */
    public static void print_e(int n) {
        int m = 2 * n - 1;
        boolean[][] pixel = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0 || j == m - 1) System.out.print("#");
                else if ((i == 0 || i == n - 1 || (m - j) / 2 == i || j / 2 == i) && (j % 2 == 0))
                    System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}