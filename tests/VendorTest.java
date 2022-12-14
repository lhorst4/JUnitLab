import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;

public class VendorTest {

    Vendor vendor;
    @BeforeEach
    void setup(){
        vendor = new Vendor(1, 2);
        //System.out.println("setup");
    }
    @BeforeAll static void init(){
        //System.out.println("init");
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
    @Test void printInvenory2(){
        //System.out.println(vendor.toString());
        assertEquals(
                "--------------------\n" +
                "Candy : 1\n" +
                "Price : $1.25\n" +
                "Gum : 2\n" +
                "Price : $0.5\n" +
                "Balance : 0.0\n" +
                "--------------------\n", vendor.toString());
    }
    @Test void addItemTest(){
        vendor.addItem("Sucker", 3, 0.75);
        vendor.addMoney(1.0);
        vendor.select("Sucker");
        assertEquals(0.25, 0.25);
    }

    @Test void printInventory(){
        Main main = new Main();
        main.main();
        assertEquals("--------------------\n" +
                "Candy : 7\n" +
                "Price : $1.25\n" +
                "Gum : 8\n" +
                "Price : $0.5\n" +
                "Balance : 1.0\n" +
                "--------------------\n" +
                "--------------------\n" +
                "Candy : 7\n" +
                "Price : $1.25\n" +
                "Gum : 8\n" +
                "Price : $0.5\n" +
                "Balance : 5.0\n" +
                "--------------------\n" +
                "--------------------\n" +
                "Candy : 7\n" +
                "Price : $1.25\n" +
                "Gum : 8\n" +
                "Price : $0.5\n" +
                "Balance : 3.0\n" +
                "--------------------\n" +
                "--------------------\n" +
                "Candy : 7\n" +
                "Price : $1.25\n" +
                "Gum : 8\n" +
                "Price : $0.5\n" +
                "Balance : 2.5\n" +
                "--------------------\n" +
                "--------------------\n" +
                "Candy : 7\n" +
                "Price : $1.25\n" +
                "Gum : 8\n" +
                "Price : $0.5\n" +
                "Balance : 0.0\n" +
                "--------------------\n", main.str);
    }
}
