package oida.test;


public class ObserverTest {

	
        /**
         * @param args
         */
        public static void main(String[] args) {
                ElementObserver observ1 = new ElementObserver();
                observ1.doStuff();
                TotalObserver observ2 = new TotalObserver();
                observ2.doStuff();
                
                
        }
}
