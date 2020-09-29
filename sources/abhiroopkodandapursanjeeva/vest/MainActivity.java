package abhiroopkodandapursanjeeva.vest;

import android.content.Intent;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView PasswordBox;
    TextView UsernameBox;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0010R.layout.activity_main);
        this.UsernameBox = (TextView) findViewById(C0010R.C0012id.Username_Box);
        this.PasswordBox = (TextView) findViewById(C0010R.C0012id.Password_Box);
    }

    public void openMain(View view) {
        if (!this.UsernameBox.getText().toString().equals("Admin") || !this.PasswordBox.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Incorrect Login Credentials", 0).show();
        } else {
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }
    }
}
