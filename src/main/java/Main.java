/**
 * Created by rmochetc on 18.01.2017.
 */
public class Main {
    public static void main(String[] args) {
        A a = new B();
        a.testStatic();
        a.nonStatic();
    }
}

class A {
    public static void testStatic(){
        System.out.println("A.test.static");
    }
    public void nonStatic(){
        System.out.println("A.test.nonStatic");
    }
}

class B extends A {
    public static void testStatic(){
        System.out.println("B.test.static");
    }
    public void nonStatic(){
        System.out.println("B.test.nonStatic");
    }
}
