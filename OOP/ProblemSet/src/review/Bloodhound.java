package review;

class Dog{
    void makeNoise(){
        System.out.println("bark ");
    }
    static void play(){
        System.out.println("catching ");
    }
}

public class Bloodhound extends Dog{
    void makeNoise() {
        System.out.println("howl ");
    }

    public static void main(String[] args) {
        new Bloodhound().go();
    }
    void go(){
        ((Dog)this).makeNoise();
        super.play();
        makeNoise();
        super.makeNoise();
    }
}
