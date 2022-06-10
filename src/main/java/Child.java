public class Child extends Parent{
    public static void main(String args[])
    {
        Child child1 = new Child();
        System.out.println(child1.property1);
        System.out.println(child1.property2);
        child1.method1();
        child1.method2();
    }
}
