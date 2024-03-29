package com.example.calculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {
   
   private EditText textInput;
   private TextView textOutput;
   private int numberOfParenthesisCLick = 0;
   private int numberOfPlusMinusClick = 0;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      textInput = findViewById(R.id.textInput);
      textOutput = findViewById(R.id.textOutput);
      textInput.setShowSoftInputOnFocus(false);
     
   }
   public void zeroPressed(View view){
      updateOutput(getResources().getString(R.string.zero));
   }
   public void onePressed(View view){
      updateOutput(getResources().getString(R.string.one));
   }
   public void twoPressed(View view){
      updateOutput(getResources().getString(R.string.two));
   }
   public void threePressed(View view){
      updateOutput(getResources().getString(R.string.three));
   }
   public void fourPressed(View view){
      updateOutput(getResources().getString(R.string.four));
   }
   public void fivePressed(View view){
      updateOutput(getResources().getString(R.string.five));
   }
public void sixPressed(View view){
   updateOutput(getResources().getString(R.string.six));
}
   public void sevenPressed(View view){
      updateOutput(getResources().getString(R.string.seven));
   }
   public void eightPressed(View view){
      updateOutput(getResources().getString(R.string.eight));
   }
   public void ninePressed(View view){
      updateOutput(getResources().getString(R.string.nine));
   }
//  Arithmetic  Operations buttons
   public void plusPressed(View view){
      updateOutput(getResources().getString(R.string.plus));
   }
   public void minusPressed(View view){
      updateOutput(getResources().getString(R.string.minus));
   }
   public void multiplyPressed(View view){
      updateOutput(getResources().getString(R.string.multiply));
   }
   public void dividePressed(View view){
      updateOutput(getResources().getString(R.string.divide));
   }
//   Other operations buttons
   public void plusMinusPressed(View view){
//      textInput.setSelection(0);
      SpannableStringBuilder value = (SpannableStringBuilder) textInput.getText();
      
      String signal = String.valueOf(value.charAt(0));
      String newStr;
      if(signal.equals("-")){
         value.replace(0,1,"");
         textInput.setText(value);
         textInput.setSelection(value.length());
      }
      if(!signal.equals("-")){
         textInput.setText(String.format("%s%s", "-",value));
         textInput.setSelection(value.length());
      }
      
      //      updateOutput(getResources().getString(R.string.plusMinus));
   }
   public void parenthesesPressed(View view){
      if(numberOfParenthesisCLick == 0){
         updateOutput("(");
         numberOfParenthesisCLick++;
      }else{
         updateOutput( ")");
         numberOfParenthesisCLick = 0;
      }
   }
   public void backspacePressed(View view){
      if(!textInput.toString().isEmpty()){
         int cursorPos = textInput.getSelectionStart();
         
         if(cursorPos-1 >= 0){
            String leftStr = textInput.getText().toString().substring(0,
                    cursorPos-1);
            String rightStr =
                    textInput.getText().toString().substring(cursorPos);
            textInput.setText(String.format("%s%s", leftStr,rightStr));
            textInput.setSelection(leftStr.length());
         }
      }
   }
   public void clearPressed(View view){
      textInput.setText("");
      textOutput.setText("");
      numberOfParenthesisCLick = 0;
   }
 
   public void equalPressed(View view){
      Expression expression = new Expression(textInput.getText().toString());
      textOutput.setText(textInput.getText().toString());
      
      String result = String.valueOf(expression.calculate());
      
      textInput.setText(result);
      textInput.setSelection(result.length());
   }
   public void dotPressed(View view){
      updateOutput(getResources().getString(R.string.dot));
   }
   
   private void updateOutput(String number) {
      String previousInput = textInput.getText().toString();
      int cursorPos = textInput.getSelectionStart();
      String leftStr = previousInput.substring(0,cursorPos);
      String rightStr = previousInput.substring(cursorPos);
      String currentText = String.format("%s%s%s", leftStr, number, rightStr);
      textInput.setText(currentText);
      textInput.setSelection(number.length()+cursorPos);
      
   }
   
}