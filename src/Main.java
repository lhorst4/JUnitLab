public class Main {
    static Vendor v = new Vendor(1, 2, 1.0);
    static Vendor w = new Vendor(3, 3, 5.0);
    static Vendor x = new Vendor(4, 4, 3.0);
    static Vendor y = new Vendor(5, 6, 2.5);
    static Vendor z = new Vendor(7, 8);
    static String str = "";
    static Vendor[] array = {v,w,x,y,z};
    public static void main() {

        System.out.println("Welcome to Ultra Shop!");

        for(Vendor a: array){
            str = str + a;
        }
        System.out.println(str);
        System.out.println("Maintained by <A human>");

    }
}
