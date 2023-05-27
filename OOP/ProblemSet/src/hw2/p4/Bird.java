package hw2.p4;

public class Bird {
    public void gulgate(Bird b) {
        System.out.println("BiGulBi");
    }
    public static int add(int a,int b){ return a+b;}
}

class Falcon extends Bird {
    public void gulgate(Falcon f) {
        System.out.println("FaGulFa");
    }

    public static void main(String[] args) {
        extracted();
    }

    private static void extracted() {
        Bird bird = new Falcon();
        Falcon falcon = (Falcon) bird;
        bird.gulgate(falcon);
        falcon.gulgate(falcon);
    }
}
// 运行结果为
// BiGulBi
// FaGulFa
// Overload is not override! 方法选择会遵循“就近原则”
