package sec.com.hong.viet.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import sec.com.hong.viet.orderfood.DTO.NhanVienDTO;
import sec.com.hong.viet.orderfood.database.CreateDatabaseOrderFood;

/**
 * Created by MyPC on 3/6/2018.
 */

public class NhanVietDAO {
    SQLiteDatabase database;

    public NhanVietDAO(Context context) {
        CreateDatabaseOrderFood createDatabase = new CreateDatabaseOrderFood(context);
        database = createDatabase.open();
    }

    public long themMoiNhanVien(NhanVienDTO nv){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabaseOrderFood.TB_NHANVIEN_TENNV, nv.getTenNV());
        contentValues.put(CreateDatabaseOrderFood.TB_NHANVIEN_MATKHAU, nv.getMatKhau());
        contentValues.put(CreateDatabaseOrderFood.TB_NHANVIEN_GIOITINH, nv.getGioiTinh());
        contentValues.put(CreateDatabaseOrderFood.TB_NHANVIEN_NGAYSINH, nv.getNgaySinh());
        contentValues.put(CreateDatabaseOrderFood.TB_NHANVIEN_CMND, nv.getSoCMND());

        long kiemtra = database.insert(CreateDatabaseOrderFood.TB_NHANVIEN, null, contentValues);
        return  kiemtra;
    }
}
