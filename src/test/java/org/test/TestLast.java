package org.test;

import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;

public class TestLast extends AbstractTest {

	@InSequence(Integer.MAX_VALUE)
	@Test
	public void test() {
		final String file = System.getProperty("jboss.home.dir") + "/heap.hprof";
		System.out.println("Writing Heap dump to " + file);
		HeapDumper.to(file);
	}
}
