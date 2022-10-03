package AA.a;

public class test {
    public static void main(String[] args) {
        teacher w = new teacher("w", 1);
        teacher c= new teacher("w", 1);
        teacher d = new teacher("w", 2);
        System.out.println(w.equals(c));
        System.out.println(w.equals(d));
    }
}
