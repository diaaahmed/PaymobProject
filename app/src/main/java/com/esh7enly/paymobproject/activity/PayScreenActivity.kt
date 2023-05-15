package com.esh7enly.paymobproject.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.esh7enly.paymobproject.Constant
import com.esh7enly.paymobproject.R
import com.esh7enly.paymobproject.databinding.ActivityNewBinding
import com.paymob.acceptsdk.*

@Suppress("DEPRECATION")
class PayScreenActivity : AppCompatActivity()
{
    private val ui by lazy{
        ActivityNewBinding.inflate(layoutInflater)
    }

    private val ACCEPT_PAYMENT_REQUEST = 10

    // Replace this with your actual payment key
    val paymentKey = Constant.finalToken

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        ui.Button1.setOnClickListener { startPayActivityNoToken(true) }
        ui.Button2.setOnClickListener { startPayActivityNoToken(false) }
        ui.Button3.setOnClickListener { startPayActivityToken() }
    }

    private fun startPayActivityNoToken(showSavedCard:Boolean)
    {
        val pay_intent = Intent(this, PayActivity::class.java)
        putNormalExtras(pay_intent)
        pay_intent.putExtra(PayActivityIntentKeys.SAVE_CARD_DEFAULT, false)
        pay_intent.putExtra(PayActivityIntentKeys.SHOW_SAVE_CARD,showSavedCard)
        pay_intent.putExtra(
            PayActivityIntentKeys.THEME_COLOR,
            ContextCompat.getColor(this, R.color.ThemeColor)
        )

        pay_intent.putExtra("ActionBar", true)
        pay_intent.putExtra("language", "ar")
        startActivityForResult(pay_intent,
            ACCEPT_PAYMENT_REQUEST)
        val secure_intent = Intent(this, ThreeDSecureWebViewActivty::class.java)
        secure_intent.putExtra("ActionBar", true)
    }

    private fun startPayActivityToken()
    {
        val pay_intent = Intent(this, PayActivity::class.java)

        putNormalExtras(pay_intent)
        // replace this with your actual card token
        // replace this with your actual card token
        pay_intent.putExtra("language", "ar")
        pay_intent.putExtra(PayActivityIntentKeys.TOKEN, "6088c38c19705a495f1727561d4f4814b2ed7e45e9cd80c72f233253")
        pay_intent.putExtra(PayActivityIntentKeys.MASKED_PAN_NUMBER, "xxxx-xxxx-xxxx-1234")
        pay_intent.putExtra(PayActivityIntentKeys.SAVE_CARD_DEFAULT, false)
        pay_intent.putExtra(PayActivityIntentKeys.SHOW_SAVE_CARD, false)
        pay_intent.putExtra("ActionBar", true)
        pay_intent.putExtra(
            PayActivityIntentKeys.THEME_COLOR,
            resources.getColor(R.color.purple_500)
        )

        pay_intent.putExtra(PayActivityIntentKeys.FIRST_NAME, "Cliffo")
        pay_intent.putExtra(PayActivityIntentKeys.LAST_NAME, "Nicol")
        pay_intent.putExtra(PayActivityIntentKeys.BUILDING, "8028")
        pay_intent.putExtra(PayActivityIntentKeys.FLOOR, "42")
        pay_intent.putExtra(PayActivityIntentKeys.APARTMENT, "803")
        pay_intent.putExtra(PayActivityIntentKeys.CITY, "Jask")
        pay_intent.putExtra(PayActivityIntentKeys.STATE, "Uta")
        pay_intent.putExtra(PayActivityIntentKeys.COUNTRY, "CR")
        pay_intent.putExtra(PayActivityIntentKeys.EMAIL, "claudette09@exa.com")
        pay_intent.putExtra(PayActivityIntentKeys.PHONE_NUMBER, "+86(8)9135210487")
        pay_intent.putExtra(PayActivityIntentKeys.POSTAL_CODE, "01898")

        startActivityForResult(
            pay_intent, ACCEPT_PAYMENT_REQUEST
        )
    }

    private fun putNormalExtras(intent: Intent) {
        intent.putExtra(PayActivityIntentKeys.PAYMENT_KEY, paymentKey)
        intent.putExtra(PayActivityIntentKeys.THREE_D_SECURE_ACTIVITY_TITLE, "Verification")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val extras = data?.extras
        if (requestCode == ACCEPT_PAYMENT_REQUEST) {
            if (resultCode == IntentConstants.USER_CANCELED) {
                // User canceled and did no payment request was fired
                ToastMaker.displayShortToast(this, "User canceled!!")
            } else if (resultCode == IntentConstants.MISSING_ARGUMENT) {
                // You forgot to pass an important key-value pair in the intent's extras
                ToastMaker.displayShortToast(
                    this,
                    "Missing Argument == " + extras!!.getString(IntentConstants.MISSING_ARGUMENT_VALUE)
                )
            } else if (resultCode == IntentConstants.TRANSACTION_ERROR) {
                // An error occurred while handling an API's response
                ToastMaker.displayShortToast(
                    this,
                    "Reason == " + extras!!.getString(IntentConstants.TRANSACTION_ERROR_REASON)
                )
            } else if (resultCode == IntentConstants.TRANSACTION_REJECTED) {
                // User attempted to pay but their transaction was rejected
                Toast.makeText(this, "REJECTED", Toast.LENGTH_SHORT).show()
                // Use the static keys declared in PayResponseKeys to extract the fields you want
                ToastMaker.displayShortToast(this, extras!!.getString(PayResponseKeys.DATA_MESSAGE))
            } else if (resultCode == IntentConstants.TRANSACTION_REJECTED_PARSING_ISSUE) {
                // User attempted to pay but their transaction was rejected. An error occured while reading the returned JSON
                ToastMaker.displayShortToast(
                    this,
                    extras!!.getString(IntentConstants.RAW_PAY_RESPONSE)
                )
            } else if (resultCode == IntentConstants.TRANSACTION_SUCCESSFUL) {
                // User finished their payment successfully
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()

                // Use the static keys declared in PayResponseKeys to extract the fields you want
                ToastMaker.displayShortToast(this, extras!!.getString(PayResponseKeys.DATA_MESSAGE))
            } else if (resultCode == IntentConstants.TRANSACTION_SUCCESSFUL_PARSING_ISSUE) {
                // User finished their payment successfully. An error occured while reading the returned JSON.
                ToastMaker.displayShortToast(this, "TRANSACTION_SUCCESSFUL - Parsing Issue")

                // ToastMaker.displayShortToast(this, extras.getString(IntentConstants.RAW_PAY_RESPONSE));
            } else if (resultCode == IntentConstants.TRANSACTION_SUCCESSFUL_CARD_SAVED) {
                // User finished their payment successfully and card was saved.
                Toast.makeText(this, "SUCCESS CARD SAVE", Toast.LENGTH_SHORT).show()

                // Use the static keys declared in PayResponseKeys to extract the fields you want
                // Use the static keys declared in SaveCardResponseKeys to extract the fields you want
                ToastMaker.displayShortToast(
                    this,
                    "Token == " + extras!!.getString(SaveCardResponseKeys.TOKEN)
                )
            } else if (resultCode == IntentConstants.USER_CANCELED_3D_SECURE_VERIFICATION) {
                ToastMaker.displayShortToast(this, "User canceled 3-d scure verification!!")

                // Note that a payment process was attempted. You can extract the original returned values
                // Use the static keys declared in PayResponseKeys to extract the fields you want
                ToastMaker.displayShortToast(this, extras!!.getString(PayResponseKeys.PENDING))
            } else if (resultCode == IntentConstants.USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE) {
                ToastMaker.displayShortToast(
                    this,
                    "User canceled 3-d scure verification - Parsing Issue!!"
                )

                // Note that a payment process was attempted.
                // User finished their payment successfully. An error occured while reading the returned JSON.
                ToastMaker.displayShortToast(
                    this,
                    extras!!.getString(IntentConstants.RAW_PAY_RESPONSE)
                )
            }
        }

    }
}