import java.util.ArrayList;

/**
 * Table is the common area where agents place ingredients and chefs take
 * ingredients
 * 
 * @author Haseeb
 *
 */
public class Table {

	private boolean empty = true;
	private ArrayList<Start.Ingredients> contents = null;
	private int count;

	Table() {
		count = 0;
	}

	/**
	 * Place a list of ingredients on the table
	 * 
	 * @param obj - List being placed on the table
	 */
	public synchronized void addItem(ArrayList<Start.Ingredients> obj) {
		// Only add if the table is empty
		while (!empty && count < 20) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (count >= 20) {
			return;
		}

		// Set the contents and notifyAll()
		contents = obj;
		empty = false;
		notifyAll();

	}

	/**
	 * Get a list of ingredients from the table IFF they are the chefs missing
	 * ingredients
	 * 
	 * @param specialIngredient - Ingredient the chef has unlimited of
	 * @return - List of ingredients on the table
	 */
	public synchronized ArrayList<Start.Ingredients> getItem(Start.Ingredients specialIngredient) {
		// Only get if something on table and special ingredient is not on table
		while ((empty || contents.contains(specialIngredient)) && count < 20) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (count >= 20) {
			return null;
		}

		// Get the contents, update the eat count and notifyAll()
		ArrayList<Start.Ingredients> item = contents;
		contents = null;
		empty = true;
		notifyAll();
		return item;
	}

	/**
	 * Get the number of sandwiches created and eaten
	 * 
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Set the number of sandwiches created and eaten
	 * 
	 */
	public synchronized void incrementCount() {
		count++;
		notifyAll();
	}
}
