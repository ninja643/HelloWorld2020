package rs.ac.ni.pmf.marko.helloworld2020;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compute(View view) {
        int first;
        int second;

        try {
            first = parseNumber(R.id.editFirstNumber);
            second = parseNumber(R.id.editSecondNumber);
        } catch (final NumberFormatException e) {
            // Hanlde the exception
            Toast.makeText(this, R.string.number_error, Toast.LENGTH_SHORT).show();
            return;
        }

        int id = view.getId();
        int result = 0;

        switch (id) {
            case R.id.buttonPlus:
                result = first + second;
                break;
            case R.id.buttonMinus:
                result = first - second;
                break;
            case R.id.buttonTimes:
                result = first * second;
                break;
            case R.id.buttonDivision:
                if (second == 0) {
                    // Handle the error
                    Toast.makeText(this, R.string.div_by_zero, Toast.LENGTH_SHORT).show();
                    return;
                }
                result = first / second;
        }

        showResult(result);
    }

    private void showResult(int result) {
        TextView resultLabel = findViewById(R.id.labelResult);
        resultLabel.setText(String.valueOf(result));
    }


    private int parseNumber(int viewId) {
        EditText edit = findViewById(viewId);
        return Integer.parseInt(edit.getText().toString());
    }
}
