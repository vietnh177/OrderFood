package sec.com.hong.viet.orderfood.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import sec.com.hong.viet.orderfood.DAO.NhanVietDAO;
import sec.com.hong.viet.orderfood.DTO.NhanVienDTO;
import sec.com.hong.viet.orderfood.R;
import sec.com.hong.viet.orderfood.view.fragments.DatePickerPopupFragment;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edt_ten_dang_nhap, edt_mat_khau, edt_ngay_sinh, edt_cmnd;
    Button btn_dong_y, btn_thoat;
    RadioGroup rg_gioi_tinh;

    NhanVietDAO nhanVietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_ten_dang_nhap = findViewById(R.id.edt_ten_dang_nhap);
        edt_mat_khau = findViewById(R.id.edt_mat_khau);
        edt_ngay_sinh = findViewById(R.id.edt_ngay_sinh);
        edt_cmnd = findViewById(R.id.edt_cmnd);

        btn_dong_y = findViewById(R.id.btn_dong_y);
        btn_thoat = findViewById(R.id.btn_thoat);

        btn_dong_y.setOnClickListener(this);
        btn_thoat.setOnClickListener(this);

        rg_gioi_tinh = findViewById(R.id.rg_gioi_tinh);
        edt_ngay_sinh.setOnFocusChangeListener(this);

        nhanVietDAO = new NhanVietDAO(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dong_y:
                themNhanVien();
                break;
            case R.id.btn_thoat:
                finish();
                break;
        }
    }

    private void themNhanVien() {
        NhanVienDTO nv = new NhanVienDTO();
        String sTenNv = edt_ten_dang_nhap.getText().toString();
        String sMatKhau = edt_mat_khau.getText().toString();
        String sGioiTinh = null;
        if(sTenNv.equals("")|| sMatKhau.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.err_input), Toast.LENGTH_SHORT).show();
        }else{
            nv.setTenNV(sTenNv);
            nv.setMatKhau(sMatKhau);
            switch (rg_gioi_tinh.getCheckedRadioButtonId()){
                case R.id.rb_nam:
                    sGioiTinh = "Nam";
                    break;
                case R.id.rd_nu:
                    sGioiTinh = "Nu";
                    break;
            }
            nv.setGioiTinh(sGioiTinh);
            long kiemtra = nhanVietDAO.themMoiNhanVien(nv);
            if(kiemtra != 0)
                Toast.makeText(this, getResources().getString(R.string.nhap_thanh_cong) + kiemtra, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, getResources().getString(R.string.nhap_that_bai), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.edt_ngay_sinh:
                if(b){
                    DatePickerPopupFragment datePickerPopupFragment = new DatePickerPopupFragment();
                    datePickerPopupFragment.show(getFragmentManager(), "Ngay Sinh");
                }

                break;
        }

    }
}
