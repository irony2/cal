package com.wonjun.calculator_v1;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends Activity implements View.OnClickListener{

	private EditText fNumEditText;
	private EditText sNumEditText;
	private Button addButton;
	private Button subButton;
	private Button mulButton;
	private Button divButton;
	private TextView resultTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator_activity);
		fNumEditText = (EditText) findViewById(R.id.fNum);
		sNumEditText = (EditText) findViewById(R.id.sNum);
		addButton = (Button) findViewById(R.id.add);
		subButton = (Button) findViewById(R.id.sub);
		mulButton = (Button) findViewById(R.id.mul);
		divButton = (Button) findViewById(R.id.div);
		resultTextView = (TextView) findViewById(R.id.result);
		resultTextView.setText(R.string.Result);
		addButton.setOnClickListener(this);
		subButton.setOnClickListener(this);
		mulButton.setOnClickListener(this);
		divButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.action_settings) {
		//	return true;
		//}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		String fNum = fNumEditText.getText().toString();
		String sNum = sNumEditText.getText().toString();
		if(!isStringDouble(fNum) || !isStringDouble(sNum)){
			Toast.makeText(this, R.string.NumberException, Toast.LENGTH_SHORT);
			return;
		}
		BigDecimal num1 = new BigDecimal(fNum);
		BigDecimal num2 = new BigDecimal(sNum);
		BigDecimal result = new BigDecimal("0");
		switch (v.getId()) {
		case R.id.add:
			result = addNum(num1, num2);
			break;
		case R.id.sub:
			result = subNum(num1, num2);
			break;
		case R.id.mul:
			result = mulNum(num1, num2);
			break;
		case R.id.div:
			result = divNum(num1, num2);
			break;
		}
		resultTextView.setText(getResources().getString(R.string.Result) + "" + result.toString());
	}
	private boolean isStringDouble(String s){
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	private BigDecimal addNum(BigDecimal num1, BigDecimal num2){
		return num1.add(num2);
	}
	private BigDecimal subNum(BigDecimal num1, BigDecimal num2){
		return num1.subtract(num2);
	}
	private BigDecimal mulNum(BigDecimal num1, BigDecimal num2){
		return num1.multiply(num2);
	}
	private BigDecimal divNum(BigDecimal num1, BigDecimal num2){
		return num1.divide(num2, 10, RoundingMode.HALF_UP);
	}
}
