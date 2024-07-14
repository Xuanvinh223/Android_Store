package com.example.store;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

public class NotificationHelper {
    private Context context;
    private FragmentManager fragmentManager;
    private NotificationManager notificationManager;
    private String defaultChannelId = "default_channel_id";

    public NotificationHelper(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    defaultChannelId, "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Method to show Toast
    public void showToast(String message, int duration, int iconResId) {
        // Inflate custom layout for toast
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Set the icon and message
        ImageView icon = layout.findViewById(R.id.toast_icon);
        TextView text = layout.findViewById(R.id.toast_message);
        icon.setImageResource(iconResId);
        text.setText(message);

        // Create and show the custom toast
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    // Method to show Snackbar
    public void showSnackbar(View view, String message, int duration, String actionText, View.OnClickListener action) {
        Snackbar snackbar = Snackbar.make(view, message, duration);
        if (actionText != null && action != null) {
            snackbar.setAction(actionText, action);
        }
        snackbar.show();
    }

    // Method to show AlertDialog
    public void showAlertDialog(String title, String message, String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener,
                                String negativeButtonText, DialogInterface.OnClickListener negativeButtonListener) {
        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(positiveButtonText, positiveButtonListener)
                        .setNegativeButton(negativeButtonText, negativeButtonListener)
                        .show();
            }
        }
    }

    // Method to show Notification
    public void showNotification(String title, String message, int iconResId) {
        Notification notification = new NotificationCompat.Builder(context, defaultChannelId)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(iconResId)
                .build();
        notificationManager.notify(1, notification);
    }

    // Method to show DialogFragment
    public void showDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(fragmentManager, tag);
    }

    // Example DialogFragment
    public static class MyDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Dialog Fragment")
                    .setMessage("This is a dialog fragment message")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Handle positive button click
                        }
                    });
            return builder.create();
        }
    }
}
