package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    private final StringBuilder sb = new StringBuilder();
    @FXML
    public TextField txtResult;
    @FXML
    public Button btnRun;

    public void onBtnClick(ActionEvent actionEvent) {
        Button btn = (Button)actionEvent.getSource();
        sb.append(txtResult.getText()).append(btn.getText());
        txtResult.setText(sb.toString());
        sb.setLength(0);
        btnRun.setDisable(!canCalculate(txtResult.getText()));

    }

    public void onBtnCalcClick(ActionEvent actionEvent) {
        try {
            float result = calculate(txtResult.getText());
            sb.append(txtResult.getText()).append(" = " + result);
        } catch (Exception ex) {
            sb.append(txtResult.getText()).append(" = " + ex.getMessage());
        } finally {
            txtResult.setText(sb.toString());
            sb.setLength(0);
        }
    }

    private float calculate(String input) throws Exception {
        StringBuilder sbFirstOperand = new StringBuilder();
        StringBuilder sbSecondOperand = new StringBuilder();
        char operation = '_';
        boolean isFirstOperand = true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (operation != '_') {
                    throw new Exception("Не используйте более одной операции");
                }
                operation = c;
                isFirstOperand = false;
                continue;
            }
            if (isFirstOperand) {
                sbFirstOperand.append(c);
            } else {
                sbSecondOperand.append(c);
            }
        }
        //long operand1 = Long.parseLong(sbFirstOperand.toString());
        Long operand1 = Long.valueOf(sbFirstOperand.toString());
        Long operand2 = Long.valueOf(sbSecondOperand.toString());
        float result = 0;

        switch (operation) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    throw new Exception("На 0 делить нельзя!");
                }
                result = operand1.floatValue() / operand2;
                break;
            default:
                throw new Exception("Неизвестная операция");
        }
        return result;
    }

    public void onBtnClearClick(ActionEvent actionEvent) {
        txtResult.clear();
        btnRun.setDisable(true);
    }

    private boolean canCalculate(String str) {
        if (str == null || str.length() < 3) {
            return false;
        }
        char[] operations = {'+', '-', '/', '*'};
        char lastChar = str.charAt(str.length() - 1);
        for (char op:
                operations) {
            if (lastChar == op) {
                return false;
            }
        }
        return true;
    }
}
