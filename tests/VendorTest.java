import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;

public class VendorTest {

    Vendor vendor;
    @BeforeEach
    void setup(){
        vendor = new Vendor(1, 2);
        System.out.println("setup");
    }
    @BeforeAll static void init(){
        System.out.println("init");
    }
    @Test
    void addMoneyPositive() {
        vendor.addMoney(2.0);
        assertEquals(2.0, vendor.getBalance());
    }
    @Test
    void addMoneyNegative() {
        vendor.addMoney(-3);
        assertEquals(0, vendor.getBalance());
    }
    @Test
    void addMoneyZero() {
        vendor.addMoney(0);
        assertEquals(0, vendor.getBalance());
    }
    @RepeatedTest(5)
    @DisplayName("Add Random Value (x5)")
    void addRandomValue(){
        Random r = new Random(100);
        double num = r.nextDouble();
        vendor.addMoney(num);
        assertEquals(num, vendor.getBalance());
    }
    @Test void addMoneyWithInitial(){
        Vendor v = new Vendor(4, 4, 5);
        v.addMoney(5);
        v.addMoney(-4);
        assertEquals(10, v.getBalance());
    }
    @Test void buyWithMoney(){
        vendor.addMoney(2);
        vendor.select("Gum");
        assertEquals(1.5, vendor.getBalance());
    }
    @Test void buyWithoutMoney(){
        vendor.select("Candy");
        assertEquals(0, vendor.getBalance());
    }

    @Test void buyWithoutStock(){
        vendor.emptyStock();
        vendor.addMoney(5);
        vendor.select("Candy");
        assertEquals(0, vendor.getStock("Candy"));
    }

    @Test void restockPositive() {
        vendor.restock("Candy", 4);
        assertEquals(5, vendor.getStock("Candy"));
    }
    @Test void restockNegative() {
        vendor.restock("Candy", -4);
        assertEquals(1, vendor.getStock("Candy"));
    }
    @Test void restockZero() {
        vendor.restock("Candy", 0);
        assertEquals(1, vendor.getStock("Candy"));
    }

    @Test void buyAfterRestock(){
        vendor.emptyStock();
        vendor.addMoney(10);
        vendor.restock("Candy", 1);
        vendor.select("Candy");
        assertEquals(8.75, vendor.getBalance());
    }

    @Test void changeItemName(){
        vendor.changeItemName("Candy", "candee");
        assertEquals(1, vendor.getStock("candee"));
    }

    @Test void emptyItem(){
        vendor.emptyStock();
        assertEquals(0, vendor.getStock("Candy"));
        assertEquals(0, vendor.getStock("Gum"));
    }
}
