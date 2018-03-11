package sec.com.hong.viet.orderfood.view.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sec.com.hong.viet.orderfood.DAO.NhanVietDAO;
import sec.com.hong.viet.orderfood.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_dang_nhap, edt_mk;
    Button btn_dang_ky,btn_dang_nhap;
    NhanVietDAO nhanVietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_dang_nhap = findViewById(R.id.edt_dang_nhap);
        edt_mk = findViewById(R.id.edt_mat_khau);

        btn_dang_ky =findViewById(R.id.btn_dang_ky);
        btn_dang_nhap = findViewById(R.id.btn_dang_nhap);

        btn_dang_nhap.setOnClickListener(this);
        btn_dang_ky.setOnClickListener(this);

        nhanVietDAO = new NhanVietDAO(this);

        kiemTraHienThi();

    }

    private void kiemTraHienThi() {
        if(!nhanVietDAO.kiemTraNV()){
            btn_dang_ky.setVisibility(View.VISIBLE);
            btn_dang_nhap.setVisibility(View.GONE);
        }else {
            btn_dang_ky.setVisibility(View.GONE);
            btn_dang_nhap.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dang_ky:
                doRegister();
                break;
            case R.id.btn_dang_nhap:
                doLogin();
                break;
        }
    }
    private void doRegister() {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        kiemTraHienThi();
    }

    @SuppressLint("ResourceType")
    private void doLogin() {
        String sName = edt_dang_nhap.getText().toString();
        String sPw= edt_mk.getText().toString();
        if(sName.equals("") || sPw.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.str_warning_wrong_input_login), Toast.LENGTH_SHORT).show();
        }else if(nhanVietDAO.checkLogin(sName, sPw)){
            Toast.makeText(this, getResources().getString(R.string.str_login_success), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("tennv", sName);
            startActivity(i);
            clearData();
        }else {
            showDialogRegister();
        }
    }

    private void showDialogRegister() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.str_dialog_register_title));
        builder.setMessage(getResources().getString(R.string.str_dialog_register_body));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doRegister();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void clearData(){
        edt_dang_nhap.setText("");
        edt_mk.setText("");
    }
}
