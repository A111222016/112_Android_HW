import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private double theValue = 0.0; // 使用 double 类型来存储值
    private double operand1 = 0.0, operand2 = 0.0; // 使用 double 类型来存储操作数
    private OP op = OP.None;
    private State state = State.FirstNumberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 省略其他方法...

    public void processKeyInput(View view) {
        Button b = (Button) view;
        String bstr = b.getText().toString();
        double bdouble; // 使用 double 类型存储按钮上的数字

        EditText edt = findViewById(R.id.display);

        switch (bstr) {
            case ".":
                if (!String.valueOf(theValue).contains(".")) { // 检查当前值是否已经包含小数点
                    bdouble = Double.parseDouble("0." + theValue); // 将当前值转换为 double，并在前面添加小数点
                    theValue = bdouble;
                    state = State.NumberInput;
                    edt.setText(String.valueOf(theValue));
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                switch (state) {
                    case FirstNumberInput:
                        operand1 = theValue;
                        operand2 = theValue;
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        state = State.OperatorInputed;
                        break;
                    case OperatorInputed:
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        operand2 = theValue;
                        break;
                    case NumberInput:
                        operand2 = theValue;
                        switch (op) {
                            case Add:
                                theValue = operand1 + operand2;
                                break;
                            case Sub:
                                theValue = operand1 - operand2;
                                break;
                            case Mul:
                                theValue = operand1 * operand2;
                                break;
                            case Div:
                                if (operand2 != 0) {
                                    theValue = operand1 / operand2;
                                } else {
                                    // 处理除零错误
                                    theValue = Double.NaN;
                                }
                                break;
                        }
                        operand1 = theValue;
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        state = State.OperatorInputed;
                        edt.setText(String.valueOf(theValue));
                        break;
                }
                break;
            case "=":
                if (state == State.OperatorInputed) {
                    operand2 = theValue;
                    switch (op) {
                        case Add:
                            theValue = operand1 + operand2;
                            break;
                        case Sub:
                            theValue = operand1 - operand2;
                            break;
                        case Mul:
                            theValue = operand1 * operand2;
                            break;
                        case Div:
                            if (operand2 != 0) {
                                theValue = operand1 / operand2;
                            } else {
                                // 处理除零错误
                                theValue = Double.NaN;
                            }
                            break;
                    }
                    operand1 = theValue;
                } else if (state == State.NumberInput) {
                    operand2 = theValue;
                    switch (op) {
                        case Add:
                            theValue = operand1 + operand2;
                            break;
                        case Sub:
                            theValue = operand1 - operand2;
                            break;
                        case Mul:
                            theValue = operand1 * operand2;
                            break;
                        case Div:
                            if (operand2 != 0) {
                                theValue = operand1 / operand2;
                            } else {
                                // 处理除零错误
                                theValue = Double.NaN;
                            }
                            break;
                    }
                    operand1 = theValue;
                    state = State.OperatorInputed;
                }
                edt.setText(String.valueOf(theValue));
                break;
            default:
                if (bstr.matches("\\d")) {
                    int bint = Integer.parseInt(bstr);
                    switch (state) {
                        case FirstNumberInput:
                            theValue = theValue * 10 + bint;
                            break;
                        case OperatorInputed:
                            theValue = bint;
                            operand2 = bint;
                            state = State.NumberInput;
                            break;
                        case NumberInput:
                            theValue = theValue * 10 + bint;
                            break;
                    }
                    edt.setText(String.valueOf(theValue));
                }
                break;
        }
    }
}

