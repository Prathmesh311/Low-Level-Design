package sample.lld;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class VendingMachine{
	
	private State state;
	
	private HashMap<Integer, Product> productSlots;
	private HashMap<Coin, Integer> inventoryCash;
	private HashMap<Coin, Integer> enteredCash;
	private int selectedProductSlot;
	
	//Singleton Pattern
	private static final VendingMachine instance = new VendingMachine();
	
	public VendingMachine() {
		state  = new idleState();
		
	}
	
	public VendingMachine getInstance() {
		return instance;
	}
	
	
	
	//Managing state (State Pattern)
	public void setState(State newState) {
		state = newState;
	}
	
	public State getState() {
		return state;
	}
	
	
	
	public HashMap<Integer, Product> getProductSlots() {
		return productSlots;
	}

	public void setProductSlots(HashMap<Integer, Product> productSlots) {
		this.productSlots = productSlots;
	}

	public HashMap<Coin, Integer> getInventoryCash() {
		return inventoryCash;
	}

	public void setInventoryCash(HashMap<Coin, Integer> inventoryCash) {
		this.inventoryCash = inventoryCash;
	}

	public HashMap<Coin, Integer> getEnteredCash() {
		return enteredCash;
	}

	public void setEnteredCash(HashMap<Coin, Integer> enteredCash) {
		this.enteredCash = enteredCash;
	}

	public int getSelectedProductSlot() {
		return selectedProductSlot;
	}

	public void setSelectedProductSlot(int selectedProductSlot) {
		this.selectedProductSlot = selectedProductSlot;
	}
	
	

}


//(State Pattern)
interface State{
	public boolean addMoney(VendingMachine vendingMachine,  HashMap<Coin, Integer> insertedMoney);
	public void selectProduct(VendingMachine vendingMachine,int slotNumber);
	public boolean checkCash(VendingMachine vendingMachine);
	public boolean Cancle(VendingMachine vendingMachine);
	public boolean refund(VendingMachine vendingMachine);
	public void dispatchProduct(VendingMachine vendingMachine);
	public void getChange(VendingMachine vendingMachine);
}

class idleState implements State{

	@Override
	public boolean addMoney(VendingMachine vendingMachine, HashMap<Coin, Integer> insertedMoney) {
		vendingMachine.setEnteredCash(insertedMoney);
		return true;
	}

	@Override
	public void selectProduct(VendingMachine vendingMachine, int slotNumber) {
		throw new UnsupportedOperationException("THis method is not supported in current state");
		
	}

	@Override
	public boolean checkCash(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");

	}

	@Override
	public boolean Cancle(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");
	}

	@Override
	public boolean refund(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");
	}

	@Override
	public void dispatchProduct(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");		
	}

	@Override
	public void getChange(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");
	}
}


class hasMoneyState implements State{

	@Override
	public boolean addMoney(VendingMachine vendingMachine, HashMap<Coin, Integer> insertedMoney) {
		
		HashMap<Coin, Integer> availableCoins = vendingMachine.getEnteredCash();
		
		for(Map.Entry<Coin, Integer> entry: insertedMoney.entrySet()) {
			Coin coin = entry.getKey();
			int count = entry.getValue();
			
			if(availableCoins.containsKey(coin)) {
				availableCoins.put(coin, availableCoins.get(coin) + count); 
			}else {
				availableCoins.put(coin, count); 
			}
		}
		
		vendingMachine.setEnteredCash(availableCoins);
		return true;
	}

	@Override
	public void selectProduct(VendingMachine vendingMachine, int slotNumber) {
		vendingMachine.setSelectedProductSlot(slotNumber);
		
		if(!checkCash(vendingMachine)) {
			System.out.println("Please add full amount to buy product");
		}
	}

	@Override
	public boolean checkCash(VendingMachine vendingMachine) {
		int selectedProductSlot =  vendingMachine.getSelectedProductSlot();
		return true;
	}

	@Override
	public boolean Cancle(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");
	}

	@Override
	public boolean refund(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");
	}

	@Override
	public void dispatchProduct(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");		
	}

	@Override
	public void getChange(VendingMachine vendingMachine) {
		throw new UnsupportedOperationException("THis method is not supported in current state");
	}
}




enum Coin{
	Penny(1),
	Nickel(5),
	Dime(10),
	Quarter(25);
	
	public int value;
	
	Coin(int value) {
		this.value = value;
	}
}

class slot{
	private int id;
	private int slotNumber;
	private Product product;
	private int maxSize;
	private int productCount;
	
}

class Product{
	private int id;
	private String name;
	private int price;
}

class Coke extends Product{
	
}

class Juice extends Product{
	
}




public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
