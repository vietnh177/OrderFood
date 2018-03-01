package sec.com.hong.viet.orderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sec.com.hong.viet.orderfood.database.CreateDatabaseOrderFood;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CreateDatabaseOrderFood createDatabase = new CreateDatabaseOrderFood(this);
        createDatabase.open();
    }
}
