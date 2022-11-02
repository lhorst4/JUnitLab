import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    @Test
    void addMoneyPositive() {
        Vendor v = new Vendor(1, 1);
        v.addMoney(3);
        assertEquals(v.getBalance(), 3);
    }
    @Test
    void addMoneyNegative() {
        Vendor v = new Vendor(1, 1);
        v.addMoney(-3);
        assertEquals(v.getBalance(), 0);
    }
    @Test
    void addMoneyZero() {
        Vendor v = new Vendor(1, 1);
        v.addMoney(0);
        assertEquals(v.getBalance(), 0);
    }
    @Test void addMoneyWithInitial(){
        Vendor v = new Vendor(4, 4, 5);
        v.addMoney(5);
        v.addMoney(-4);
        assertEquals(v.getBalance(), 10);
    }
    @Test void buyWithMoney(){
        Vendor v = new Vendor(5,5);
        v.addMoney(10);
        v.select("Candy");
        assertEquals(v.getBalance(), 8.75);
    }
    @Test void buyWithoutMoney(){
        Vendor v = new Vendor(5,5);
        v.select("Candy");
        assertEquals(v.getBalance(), 0);
    }

    @Test void restockPositive() {
        Vendor v = new Vendor(5, 5);
        v.restock("Candy", 4);
        assertEquals(v.getStock("Candy"), 9);
    }
    @Test void restockNegative() {
        Vendor v = new Vendor(5, 5);
        v.restock("Candy", -4);
        assertEquals(v.getStock("Candy"), 5);
    }
    @Test void restockZero() {
        Vendor v = new Vendor(5, 5);
        v.restock("Candy", 0);
        assertEquals(v.getStock("Candy"), 5);
    }

    @Test void buyAfterRestock(){
        Vendor v = new Vendor(0, 0);
        v.restock("Candy", 1);
        v.addMoney(10);
        v.select("Candy");
        assertEquals(v.getBalance(), 8.75);
    }

    @Test void changeItemName(){
        Vendor v = new Vendor(5, 5);
        v.changeItemName("Candy", "candee");
        assertEquals(v.getStock("candee"), 5);
    }

    @Test void emptyItem(){
        Vendor v = new Vendor(5, 5);
        v.emptyStock();
        assertEquals(v.getStock("Candy"), 0);
        assertEquals(v.getStock("Gum"), 0);
    }
}
