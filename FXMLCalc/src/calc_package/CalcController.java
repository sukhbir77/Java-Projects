package calc_package;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CalcController {
	@FXML private TextField txtV1, txtV2;
	@FXML private Label lblRes;
	@FXML private RadioButton rbAdd, rbSub, rbMul, rbDiv;
	@FXML private Button cmdCalc;
	@FXML private MenuItem mnuActionAdd, mnuActionSub, mnuActionMul, mnuActionDiv, mnuApplicationExit;
	private int action = 0; // 0 - add, 1 - sub, 2 - mul, 3 - div

	@FXML public void doExit(ActionEvent e) {
		Alert a = new Alert(AlertType.CONFIRMATION, "Exit?");
		Optional<ButtonType> res = a.showAndWait();
		if(res.get() == ButtonType.OK) System.exit(0);
	}
	@FXML public void doAction(ActionEvent e) {
		if(e.getSource() == rbAdd || e.getSource() == mnuActionAdd) { action = 0; rbAdd.setSelected(true); }
		else if(e.getSource() == rbSub || e.getSource() == mnuActionSub) { action = 1; rbSub.setSelected(true); }
		else if(e.getSource() == rbMul || e.getSource() == mnuActionMul) { action = 2; rbMul.setSelected(true); }
		else { action = 3; rbDiv.setSelected(true); }
	}
	@FXML public void doCalc(ActionEvent e) {
		Double v1 = Double.parseDouble(txtV1.getText());
		Double v2 = Double.parseDouble(txtV2.getText());
		double res = 0;
		switch(action) {
		case 0:
			res = v1 + v2; break;
		case 1:
			res = v1 - v2; break;
		case 2: 
			res = v1 * v2; break;
		case 3: 
			res = v1 / v2; break;
		}
		lblRes.setText(String.format("Result: %.2f", res));
	}
}












