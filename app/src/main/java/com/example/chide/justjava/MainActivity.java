package com.example.chide.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream =  findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();

        EditText name = findViewById(R.id.name);
        Editable nameEditable = name.getText();
        String inputName = nameEditable.toString();

        int price = calculatePrice(hasChocolate, hasWhippedCream);
        displayMessage(createOrderSummary(inputName, price, hasWhippedCream, hasChocolate));
    }

    private int calculatePrice (boolean hasChocolate, boolean hasWhippedCream){
        int initPrice = 5;

        if (hasChocolate){
            initPrice += 1;
        }
        if (hasWhippedCream){
            initPrice += 2;
        }
        return (initPrice) * quantity;
    }
    private String createOrderSummary (String inputName, int price, boolean hasWhippedCream, boolean hasChocolate){
        String priceMessage =  getString(R.string.summary_name, inputName);
        priceMessage += "\n" + getString(R.string.whipped_cream, hasWhippedCream);
        priceMessage += "\n" + getString(R.string.summary_chocolate, hasChocolate);
        priceMessage += "\n" + getString(R.string.summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.summary_price, NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n"+ getString(R.string.thank_you);
        return priceMessage;
    }
    /**
     * This method is called when the plus button is clicked.
     */

    public void increment(View view) {
        if (quantity == 100){
            Toast.makeText(this, "You can't have more than 100 cups of coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */

    public void decrement(View view) {
        if (quantity == 1){
            Toast.makeText(this, "You can't have less than 1 cup of coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}