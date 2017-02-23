package stellar.client.den.stellar.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import stellar.client.den.stellar.R;

public class MainActivity extends AppCompatActivity implements Router {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showDetails() {

    }
}
