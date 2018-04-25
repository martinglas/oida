package oida.bridge.ui.e4.toolcontrol;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import oida.bridge.service.IOIDABridge;

public class BridgeStateViewControl {
    private Label statusLabel;

    @PostConstruct
    public void createGui(Composite parent, IOIDABridge oidaBridge) {
	statusLabel = new Label(parent, SWT.NONE);
	statusLabel.setText("OIDA Bridge: " + oidaBridge.getStateString());
    }
}