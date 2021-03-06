package rs.ac.ni.pmf.marko.helloworld2020;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_HISTORY = 1;

    private final ArrayList<Integer> firstNumberHistory = new ArrayList<>();
    private final ArrayList<Integer> secondNumberHistory = new ArrayList<>();

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
        firstNumberHistory.add(first);
        secondNumberHistory.add(second);
    }

    private void showResult(int result) {
        TextView resultLabel = findViewById(R.id.labelResult);
        resultLabel.setText(String.valueOf(result));
    }


    private int parseNumber(int viewId) {
        EditText edit = findViewById(viewId);
        return Integer.parseInt(edit.getText().toString());
    }

    public void showHistory(View view) {

        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putIntegerArrayListExtra(Constants.KEY_FIRST_HISTORY_ARRAY, firstNumberHistory);
        intent.putIntegerArrayListExtra(Constants.KEY_SECOND_HISTORY_ARRAY, secondNumberHistory);

        //=================================
        // Ovaj kod bas i nema mnogo smisla
//        DataSerializable data = new DataSerializable(1, "message");
        DataParcelable data = new DataParcelable(1, "message");
        intent.putExtra(Constants.KEY_DATA, data);
        //=================================

        startActivityForResult(intent, REQUEST_HISTORY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_HISTORY:
                if (resultCode == RESULT_OK) {
                    int index = data.getIntExtra(Constants.KEY_RESPONSE_INDEX, -1);

                    if (index != -1)
                    {
                        EditText first = findViewById(R.id.editFirstNumber);
                        first.setText(String.valueOf(firstNumberHistory.get(index)));

                        EditText second = findViewById(R.id.editSecondNumber);
                        second.setText(String.valueOf(secondNumberHistory.get(index)));
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    // Canceled
                    Toast.makeText(this, getString(R.string.history_canceled), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void callNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Cannot call", Toast.LENGTH_SHORT).show();
        }
    }
}
