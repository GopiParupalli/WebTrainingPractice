package testngprogrammes;

import org.testng.annotations.Test;

public class GroupsTest {
	@Test(groups = "smoke")
	public void smoketest1() {
		System.out.println("GroupsTest class -smoketest1--functional");
	}

	@Test(groups = "smoke")
	public void smoketest2() {
		System.out.println("GroupsTest class - smoketest2--functional");
	}

	@Test(groups = "smoke")
	public void smoketest3() {
		System.out.println("GroupsTest classs - smoketest3--functional");
	}

	@Test(groups = "regression")
	public void regresstest1() {
		System.out.println("GroupsTest classs - regressiontest1--functional");
	}

	@Test(groups = "regression")
	public void regresstest2() {
		System.out.println("GroupsTest classs - regressiontest1--functional");
	}

	@Test(groups = "regression")
	public void regresstest3() {
		System.out.println("GroupsTest classs - regressiontest3--functional");
	}

}