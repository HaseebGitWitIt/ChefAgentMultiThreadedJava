/**
 * The chef checks the table for their missing ingredients required to make and
 * eat their sandwich.
 * 
 * @author Haseeb
 *
 */
public class Chef extends Thread {

	private String name;
	private Table table;
	private Start.Ingredients specialIngredient;
	private final long SLEEP_TIME = 500;
	int personalCount;

	Chef(String name, Table table, Start.Ingredients specialIngredient) {
		this.name = name;
		this.table = table;
		this.specialIngredient = specialIngredient;
		personalCount = 0;
	}

	@Override
	public void run() {

		long startTime, endTime, duration;

		startTime = System.nanoTime();

		// Continuously run
		while (table.getCount() < 20) {

			// Get the ingredients (if allowed)
			if (table.getItem(specialIngredient) != null) {
				System.out.println(name + " created the sandwich and ate it.");
				table.incrementCount();
				System.out.println("Eat count: " + table.getCount() + "\n");
				personalCount++;
			}

		}

		try {
			Thread.sleep(SLEEP_TIME);
			startTime += SLEEP_TIME;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println(String.format(
				"The chef '%s' was ran for %d nanoseconds, made/ate %d sandwiches, with an average time of %d nanoseconds per sandwich.",
				this.name, duration, personalCount, duration / personalCount));

	}

}
