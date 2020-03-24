package rs.ac.ni.pmf.marko.helloworld2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<Integer> firstNumbers;
    private ArrayList<Integer> secondNumbers;

    private int index = -1;

    private TextView labelFirstNumber;
    private TextView labelSecondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent startIntent = getIntent();

        loadData(startIntent);

        if (!firstNumbers.isEmpty()) {
            index = 0;
        }

        labelFirstNumber = findViewById(R.id.textFirstNumber);
        labelSecondNumber = findViewById(R.id.textSecondNumber);

        showData();
    }

    private void showData() {
        if (index != -1) {
            labelFirstNumber.setText(String.valueOf(firstNumbers.get(index)));
            labelSecondNumber.setText(String.valueOf(secondNumbers.get(index)));
        }
    }


    private void loadData(Intent startIntent) {
        firstNumbers = startIntent.getIntegerArrayListExtra(Constants.KEY_FIRST_HISTORY_ARRAY);
        secondNumbers = startIntent.getIntegerArrayListExtra(Constants.KEY_SECOND_HISTORY_ARRAY);

        if (firstNumbers == null) {
            firstNumbers = new ArrayList<>();
        }

        if (secondNumbers == null) {
            secondNumbers = new ArrayList<>();
        }

//        DataSerializable data = (DataSerializable) startIntent.getSerializableExtra(Constants.KEY_DATA);
        DataParcelable data = startIntent.getParcelableExtra(Constants.KEY_DATA);
        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
    }

    public void previous(View view) {
        if (index > 0) {
            --index;
            showData();
        }
    }

    public void next(View view) {
        if (index < firstNumbers.size() - 1) {
            ++index;
            showData();
        }
    }

    public void selectItem(View view) {
        Intent resultIntent = new Intent();

        if (index != -1) {
            resultIntent.putExtra(Constants.KEY_RESPONSE_INDEX, index);
        }

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
