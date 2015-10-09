package org.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class AbstractTest {

	@Deployment(name = "test")
	public static WebArchive createBasicDeployment() {

		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(AbstractTest.class.getPackage())
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("jboss-deployment-structure.xml", "jboss-deployment-structure.xml");

	}

	@OperateOnDeployment("test")
	@Test
	public void testMethodA() {

	}

}
