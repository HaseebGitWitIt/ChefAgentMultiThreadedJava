import java.util.ArrayList;

/**
 * The agent places 2 random ingredients on the table for the chef.
 * 
 * @author Haseeb
 *
 */
public class Agent extends Thread {

	private Table table;
	private ArrayList<Start.Ingredients> items;
	private final long SLEEP_TIME = 500;

	public Agent(Table table) {
		this.table = table;
		items = new ArrayList<Start.Ingredients>();
	}

	@Override
	public void run() {

		long startTime, endTime, duration;

		startTime = System.nanoTime();

		// Only place ingredients on the table 20 times
		while (table.getCount() < 19) {

			try {
				Thread.sleep(SLEEP_TIME);
				startTime += SLEEP_TIME;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Make sure the list is clear and then add 2 different ingredients to the list
			items.clear();
			items.add(0, getAssociatedIngredient(generateRandomNumBetweenOneAndThree()));
			Start.Ingredients secondIngredient = getAssociatedIngredient(generateRandomNumBetweenOneAndThree());
			while (secondIngredient.equals(items.get(0))) {
				secondIngredient = getAssociatedIngredient(generateRandomNumBetweenOneAndThree());
			}
			items.add(1, secondIngredient);
			System.out.println("Agent has put out: " + items.toString());

			// Place the ingredients on the table and then sleep (for timing purposes)
			table.addItem(items);

		}

		try {
			Thread.sleep(SLEEP_TIME);
			startTime += SLEEP_TIME;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println(String.format("The agent was ran for %d nanoseconds.", duration));

	}

	/**
	 * Get associated ingredient with random number (1, 2, 3)
	 * 
	 * @param randomNumber 1,2,3
	 * @return Ingredient
	 */
	private Start.Ingredients getAssociatedIngredient(int randomNumber) {
		if (randomNumber == 1) {
			return Start.Ingredients.BREAD;
		} else if (randomNumber == 2) {
			return Start.Ingredients.JAM;
		} else if (randomNumber == 3) {
			return Start.Ingredients.PEANUT_BUTTER;
		}
		System.out.println("No ingredient associated with that number!");
		System.exit(0);
		return null;

	}

	/**
	 * Generate random number between 0 and 4
	 * 
	 * @return 1, 2 or 3
	 */
	private int generateRandomNumBetweenOneAndThree() {
		return (int) ((Math.random() * 3) + 1);
	}

}
