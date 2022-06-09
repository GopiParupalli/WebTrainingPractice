package testngprogrammes;

import org.testng.annotations.Test;

public class GroupsTest2 {
	@Test(groups = "smoke", priority = 1)
	public void smotest1() {
		System.out.println("GroupsTest2 classs - smotest1--functional-GroupTest2");
	}

	@Test(groups = "smoke", priority = 3)
	public void smotest2() {
		System.out.println("GroupsTest2 classs -smotest2--functional-GroupTest2");
	}

	@Test(groups = "smoke", priority = 3)
	public void smotest3() {
		System.out.println("GroupsTest2 classs - smotest3--functional-GroupTest2");
	}

	@Test(groups = "regression")
	public void regtest1() {
		System.out.println("GroupsTest2 classs - regtest1--functional-GroupTest2");
	}

	@Test(groups = "regression")
	public void regtest2() {
		System.out.println("GroupsTest2 classs - regtest2--functional-GroupTest2");
	}

	@Test(groups = "regression")
	public void regtest3() {
		System.out.println("GroupsTest2 classs - regtest3--functional-GroupTest2");
	}

}