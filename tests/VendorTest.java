import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    @Test
    void addMoney() {
        Vendor v = new Vendor(1, 1);
        v.addMoney(3);
        assertEquals(v.getBalance(), 3);
    }

    @Test void buy(){
        Vendor v = new Vendor(5,5);
        v.addMoney(10);
        v.select("Candy");
        assertEquals(v.getBalance(), 8.75);
    }

    @Test void restock() {
        Vendor v = new Vendor(5, 5);
        v.restock("Candy", 4);
        assertEquals(v.getStock("Candy"), 9);
    }

    @Test void buyAfterRestock(){
        Vendor v = new Vendor(0, 0);
        v.restock("Candy", 1);
        v.addMoney(10);
        v.select("Candy");
        assertEquals(v.getBalance(), 8.75);
    }
}
