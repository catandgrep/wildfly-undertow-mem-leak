package org.test;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.management.MBeanServer;

public class HeapDumper {

	public static void to(String fileName) {
		dumpHeap(fileName);
	}

	/**
	 * Dumps the java heap to the specified file in hprof format. This method
	 * will not overwrite the dump file, so make sure it doesn't already exist.
	 *
	 * @param fileName
	 *            the dump file name which must not already exist.
	 */
	private static void dumpHeap(String fileName) {
		Class clazz;
		try {
			clazz = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: dumpHeap only works on a Sun Java 1.6+ VM containing "
					+ "the class com.sun.management.HotSpotDiagnosticMXBean");
			return;
		}

		// use JMX to find hot spot mbean
		Object hotspotMBean = null;
		try {
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			hotspotMBean = ManagementFactory.newPlatformMXBeanProxy(server, "com.sun.management:type=HotSpotDiagnostic", clazz);
		} catch (Throwable e) {
			System.out.print("ERROR: dumpHeap was unable to obtain the HotSpotDiagnosticMXBean: ");
			e.printStackTrace();
		}

		// invoke the dumpHeap method
		try {
			Method method = hotspotMBean.getClass().getMethod("dumpHeap", String.class, Boolean.TYPE);
			method.invoke(hotspotMBean, fileName, true);
		} catch (InvocationTargetException e) {
			Throwable t = e.getCause() != null ? e.getCause() : e;
			System.out.print("ERROR: dumpHeap threw an exception: ");
			t.printStackTrace();
		} catch (Throwable e) {
			System.out.print("ERROR: dumpHeap threw an exception: ");
			e.printStackTrace();
		}
	}
}
