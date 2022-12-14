import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vendor {
    private static HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;

    Vendor(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;
    }
    Vendor(int numCandy, int numGum, double initialBalance) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = initialBalance;
    }
    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }
    void addItem(String name, int count, double price){
        Stock.put(name, new Item(price, count));
    }

    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt) {
        if(amt > 0) {
            this.balance = this.balance + amt;
        }
    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if(getStock(name) == 0){
                System.out.println("Item out of stock");
                return;
            }
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
            }
            else
                System.out.println("Gimme more money");
        }
        else System.out.println("Sorry, don't know that item");
    }

    void restock(String name, int amount){
        Item item = Stock.get(name);
        if(item != null && amount > 0) {
            item.restock(amount);
        }
    }

    int getStock(String name){
        Item it = Stock.get(name);
        if(it != null) {
            return it.stock;
        }
        return 0;
    }

    void changeItemName(String oldName, String newName){
        Item it = Stock.get(oldName);
        if(it != null) {
            Stock.remove(oldName);
            Stock.put(newName, it);
        }
    }

    void emptyStock() {
        Set set = Stock.keySet();
        for(Object s: set){
            Item it = Stock.get(s);
            it.stock = 0;
        }
    }

    public String toString(){
        String str = "--------------------\n";
        Set set = Stock.keySet();
        for(Object s: set){
            Item it = Stock.get(s);
            str += s.toString() + " : " + getStock(s.toString()) + "\n" + "Price : $" + it.price + "\n";
        }
        str = str + "Balance : " + balance + "\n--------------------\n";
        return str;
    }
}
