package utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class ConfirmDialog {
    public static void show(String content, DialogInterface.OnClickListener on_continue, Context context) {
        AlertDialog.Builder user_alert_tosend = new AlertDialog.Builder(context);
        user_alert_tosend.setMessage(content)
                .setPositiveButton("Continue", on_continue)
                .setNegativeButton("Stop", null)
                .setCancelable(false).show(); // User can't cancel by touching off dialog box
    }
}
