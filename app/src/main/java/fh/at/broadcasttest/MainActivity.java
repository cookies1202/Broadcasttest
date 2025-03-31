package fh.at.broadcasttest;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyReceiver receiver;
    private boolean receiverIsRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch switchReceiver = findViewById(R.id.switchReceiver);
        Button confirmButton = findViewById(R.id.confirmButton);

        receiver = new MyReceiver();

        confirmButton.setOnClickListener(v -> {
            if (switchReceiver.isChecked() && !receiverIsRegistered) {
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_POWER_CONNECTED);
                filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                registerReceiver(receiver, filter);
                receiverIsRegistered = true;
            } else if (!switchReceiver.isChecked() && receiverIsRegistered) {
                unregisterReceiver(receiver);
                receiverIsRegistered = false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (receiverIsRegistered) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
}
