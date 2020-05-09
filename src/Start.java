import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Start the program
 * 
 * @author Haseeb
 *
 */
public class Start {

	private int sampleTime = 10000;
	private ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
	private RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	private OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();
	private Map<Long, Long> threadInitialCPU = new HashMap<Long, Long>();
	private Map<Long, Float> threadCPUUsage = new HashMap<Long, Float>();
	private Map<Long, Long> threadCPUTime = new HashMap<Long, Long>();
	private long initialUptime = runtimeMxBean.getUptime();

	enum Ingredients {
		BREAD, JAM, PEANUT_BUTTER
	}

	public static void main(String[] args) {
		/**
		 * Create the:
		 * 
		 * <pre>
		 *  - Table: Common area where the agent places ingredients and the chef takes the ingredients
		 * 	- 3 x Chef: Creates and eats the sandwich (each chef has unlimited amount of 1 ingredient)
		 *  - Agent: Places 2 random ingredients on the table (has unlimited amount of 3 ingredients)
		 * </pre>
		 */
		Table table = new Table();
		Agent agent = new Agent(table);
		Chef breadChef = new Chef("Lets Get This Dough Chef", table, Ingredients.BREAD);
		Chef peanutbutterChef = new Chef("Peanut Man", table, Ingredients.PEANUT_BUTTER);
		Chef jamChef = new Chef("Jammin Chef", table, Ingredients.JAM);

		Thread agentThread = new Thread(agent, "Agent Thread");
		Thread breadChefThread = new Thread(breadChef, "Bread Chef Thread");
		Thread peanutButterChefThread = new Thread(peanutbutterChef, "Peanutbutter Chef Thread");
		Thread jamChefThread = new Thread(jamChef, "Jam Chef Thread");

		// Start the agent and chef threads
		agentThread.start();
		breadChefThread.start();
		peanutButterChefThread.start();
		jamChefThread.start();

//		new Start().measure();
	}

	/**
	 * This code is commented out because the instructions said not to include it.
	 */
//	private void measure() {
//
//		ThreadInfo[] threadInfos = threadMxBean.dumpAllThreads(false, false);
//		for (ThreadInfo info : threadInfos) {
//			threadInitialCPU.put(info.getThreadId(), threadMxBean.getThreadCpuTime(info.getThreadId()));
//		}
//
//		try {
//			Thread.sleep(sampleTime);
//		} catch (InterruptedException e) {
//		}
//
//		long upTime = runtimeMxBean.getUptime();
//
//		Map<Long, Long> threadCurrentCPU = new HashMap<Long, Long>();
//		threadInfos = threadMxBean.dumpAllThreads(false, false);
//		for (ThreadInfo info : threadInfos) {
//			threadCurrentCPU.put(info.getThreadId(), threadMxBean.getThreadCpuTime(info.getThreadId()));
//		}
//
//		// CPU over all processes
//		int nrCPUs = osMxBean.getAvailableProcessors();
//		// total CPU: CPU % can be more than 100% (devided over multiple cpus)
//		// long nrCPUs = 1;
//		// elapsedTime is in ms.
//		long elapsedTime = (upTime - initialUptime);
//		for (ThreadInfo info : threadInfos) {
//			// elapsedCpu is in ns
//			Long initialCPU = threadInitialCPU.get(info.getThreadId());
//			if (initialCPU != null) {
//				long elapsedCpu = threadCurrentCPU.get(info.getThreadId()) - initialCPU;
//				float cpuUsage = elapsedCpu * 100 / (elapsedTime * 1000000F * nrCPUs);
//				threadCPUUsage.put(info.getThreadId(), cpuUsage);
//				threadCPUTime.put(info.getThreadId(), elapsedCpu);
//			}
//		}
//
//		// threadCPUUsage contains cpu % per thread
//		System.out.println(threadCPUTime);
//		// You can use osMxBean.getThreadInfo(theadId) to get information on
//		// every thread reported in threadCPUUsage and analyze the most CPU
//		// intentive threads
//
//		for (ThreadInfo info : threadInfos) {
//			System.out.println(
//					String.format("%s, %s", info.getThreadName(), threadMxBean.getThreadInfo(info.getThreadId())));
//		}
//	}

}
