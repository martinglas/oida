package oida.bridge.test;



import de.symo.model.symo.ComponentRepository;
import de.symo.model.symo.SymoFactory;
import de.symo.model.symo.SystemElement;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class ElementObserver {
        private ComponentRepository componentRepository;

        public ElementObserver() {
                SymoFactory factory = SymoFactory.eINSTANCE;
                componentRepository = factory.createComponentRepository();

                Adapter adapter = new AdapterImpl() {
                        public void notifyChanged(Notification notification) {
                                System.out
                                                .println("Notfication received from the data model. Data model has changed!!!");
                        }
                };
                componentRepository.eAdapters().add(adapter);
        }

        public void doStuff() {
                SymoFactory factory = SymoFactory.eINSTANCE;
                SystemElement systemElement = factory.createSystemElement();
                systemElement.setName("Lars");
                System.out.println("I'm adding a person.");
                componentRepository.getComponents().add(systemElement);
                System.out.println("I'm changing a entry");
                componentRepository.getComponents().get(0).setName("Lars2");
        }
}