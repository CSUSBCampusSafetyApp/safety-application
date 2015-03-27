package utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SimpleDialog {
    public static void show(String header, String content, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(header)
                .setMessage(content)
                .setNeutralButton("OK",null)
                .show();
    }

    public static void show(DialogInterface.OnClickListener on_ok, String header, String content, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(header)
                .setMessage(content)
                .setNeutralButton("OK",on_ok)
                .show();
    }
}
