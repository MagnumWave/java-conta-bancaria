import java.math.BigDecimal;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        BigDecimal x = new BigDecimal("0.003");
        BigDecimal y = new BigDecimal("0.001");

        System.out.println(
            x.subtract(y).toString()
        );

        System.out.println("x = " + x.toString());
        System.out.println("y = " + y.toString());
    }
}
