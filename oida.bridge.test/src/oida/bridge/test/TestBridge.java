package oida.bridge.test;



import org.junit.Test;

public class TestBridge {

	@Test
	public void test() {
		ElementObserver observ1 = new ElementObserver();
        observ1.doStuff();
        TotalObserver observ2 = new TotalObserver();
        observ2.doStuff();
	}

}
