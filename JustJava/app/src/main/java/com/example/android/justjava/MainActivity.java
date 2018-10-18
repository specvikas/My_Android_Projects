/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    int quantity = 2;
    int price = quantity * 5;

    public void increaseOrder(View view)
    {
        if(quantity==100) {
            display(quantity);
        }
        else {
            quantity = quantity + 1;
            display(quantity);
        }
    }


    public void decreaseOrder(View view)
    {
        if(quantity==1)
        {
            display(quantity);
            return;
        }
        else {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    public void submitOrder(View view) {
        boolean whipped_cream = false;
        boolean chocolate = false;
        price = quantity * 5;
        CheckBox w_cream_topping = (CheckBox)findViewById(R.id.whipped_cream);
        CheckBox chocolate_topping = (CheckBox)findViewById(R.id.chocolate);
        EditText editText = (EditText)findViewById(R.id.name);
        String name = editText.getText().toString();

        if(w_cream_topping.isChecked()) {
            whipped_cream = true;
            price += quantity;
        }
        if(chocolate_topping.isChecked()) {
            chocolate = true;
            price += quantity*2;
        }
        String Message = "Name: " + name;
        Message += "\nAdd Whipped Cream ? " + whipped_cream ;
        Message += "\nAdd Chocolate ? " + chocolate ;
        Message += "\nQuantity: " + quantity;
        Message += "\nTotal = $" + price;
        Message += "\nThank You!!";


        String subject = "JustJava Order Summary for " + name;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, Message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(number));
    }

}