package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator;
import es.iessaladillo.pedrojoya.pr01.utils.SoftInputUtils;

public class MainActivity extends AppCompatActivity {

    private EditText txtWeight;
    private EditText txtHeight;
    private Button btnReset;
    private Button btnCalculate;
    private ImageView imgBmi;
    private TextView lblResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initsView();
    }

    private void initsView() {
        txtWeight = ActivityCompat.requireViewById(this, R.id.txtWeight);
        txtHeight = ActivityCompat.requireViewById(this, R.id.txtHeight);
        lblResult = ActivityCompat.requireViewById(this, R.id.lblResult);
        btnReset = ActivityCompat.requireViewById(this, R.id.btnReset);
        btnCalculate = ActivityCompat.requireViewById(this, R.id.btnCalculate);
        imgBmi = ActivityCompat.requireViewById(this, R.id.imgBmi);


        btnReset.setOnClickListener(v -> reset());
        btnCalculate.setOnClickListener(v -> calculateBmiMain());
    }

    private void calculateBmiMain() {
        BmiCalculator bmiCalculator = new BmiCalculator();
        float height = txtHeight.getText().toString().isEmpty() ? 0 : Float.parseFloat(txtHeight.getText().toString());
        float weight = txtWeight.getText().toString().isEmpty() ? 0 : Float.parseFloat(txtWeight.getText().toString());
        float bmi;
        int bmiClasification = 0;

        if (height != 0) {
            if (weight != 0) {
                bmi = bmiCalculator.calculateBmi(weight, height);
                switch (bmiCalculator.getBmiClasification(bmi)) {
                    case LOW_WEIGHT:
                        imgBmi.setImageResource(R.drawable.underweight);
                        bmiClasification = R.string.main_underweight;
                        break;
                    case NORMAL_WEIGHT:
                        imgBmi.setImageResource(R.drawable.normal_weight);

                        bmiClasification = R.string.main_normalWeight;
                        break;
                    case OVERWWEIGHT:
                        imgBmi.setImageResource(R.drawable.overweight);

                        bmiClasification = R.string.main_overweight;
                        break;
                    case OBESITY_GRADE_1:
                        imgBmi.setImageResource(R.drawable.obesity1);
                        bmiClasification = R.string.main_ObesityGrade1;
                        break;
                    case OBESITY_GRADE_2:
                        imgBmi.setImageResource(R.drawable.obesity2);
                        bmiClasification = R.string.main_ObesityGrade2;
                        break;
                    case OBESITY_GRADE_3:
                        imgBmi.setImageResource(R.drawable.obesity3);
                        bmiClasification = R.string.main_ObesityGrade3;
                        break;
                }
                lblResult.setText(String.format(getString(R.string.main_bmi), bmi, getString(bmiClasification)));
                txtHeight.setError(null);
                txtWeight.setError(null);
            } else {
                txtWeight.setError(getString(R.string.main_invalid_weight));
                txtWeight.requestFocus();
            }
        } else {
            if (weight == 0)
                txtWeight.setError(getString(R.string.main_invalid_weight));

            txtHeight.setError(getString(R.string.main_invalid_height));
            txtHeight.requestFocus();

        }
        SoftInputUtils.hideKeyboard(lblResult);

    }

    public void reset() {
        txtHeight.setText("");
        txtWeight.setText("");
        txtHeight.setError(null);
        txtWeight.setError(null);
        lblResult.setText("");
        imgBmi.setImageResource(R.drawable.bmi);
        SoftInputUtils.hideKeyboard(lblResult);
    }


}
