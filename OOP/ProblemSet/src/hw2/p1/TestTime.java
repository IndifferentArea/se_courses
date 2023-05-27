package hw2.p1;

public class TestTime {
    public static void main(String[] args) {
        // Official Test
        MyTime t1 = new MyTime();
        MyTime t2 = new MyTime(2);
        MyTime t3 = new MyTime(21, 34);
        MyTime t4 = new MyTime(12, 25, 42);
        MyTime t5 = new MyTime(t4);

        System.out.println("Constructed with:");
        System.out.println("t1: all arguments defaulted");
        System.out.printf(" %s\n", t1.toUniversalString());
        System.out.printf(" %s\n", t1.toString());
        System.out.println("t2: hour specified; minute and second defaulted");
        System.out.printf(" %s\n", t2.toUniversalString());
        System.out.printf(" %s\n", t2.toString());
        System.out.println("t3: hour and minute specified; second defaulted");
        System.out.printf(" %s\n", t3.toUniversalString());
        System.out.printf(" %s\n", t3.toString());
        System.out.println("t4: hour, minute and second specified");
        System.out.printf(" %s\n", t4.toUniversalString());
        System.out.printf(" %s\n", t4.toString());
        System.out.println("t5: MyTime object t4 specified");
        System.out.printf(" %s\n", t5.toUniversalString());
        System.out.printf(" %s\n", t5.toString());
        // when initialize t6 with invalid values,please output error information
        MyTime t6 = new MyTime(15, 74, 99);
        System.out.println("t6: invalid values");
        System.out.printf("%s\n", t6.toUniversalString());

        // My Test for new increment methods
        System.out.println("-----NEW TESTS FOR INCREMENT-----");
        MyTime t7 = new MyTime(1,2,3);
        System.out.printf("t7 is initialized as %s\n",t7.toUniversalString());
        t7.incrementHour();
        System.out.printf("  after t7.incrementHour(), t7 is %s\n", t7.toUniversalString());
        t7.incrementMinute();
        System.out.printf("  after t7.incrementMinute(), t7 is %s\n", t7.toUniversalString());
        t7.incrementSecond();
        System.out.printf("  after t7.incrementSecond(), t7 is %s\n", t7.toUniversalString());
        MyTime t8 = new MyTime(23, 59, 59);
        System.out.printf("t8 is initialized as %s\n",t8.toUniversalString());
        t8.incrementSecond();
        System.out.printf("  after t8.incrementSecond(), t8 is %s\n", t8.toUniversalString());
    }
}
