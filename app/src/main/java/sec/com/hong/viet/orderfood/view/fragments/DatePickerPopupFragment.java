package sec.com.hong.viet.orderfood.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import sec.com.hong.viet.orderfood.R;

/**
 * Created by MyPC on 3/8/2018.
 */

public class DatePickerPopupFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int iNam = calendar.get(Calendar.YEAR);
        int iThang = calendar.get(Calendar.MONTH);
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, iDay,iThang, iNam );

        return datePickerDialog;

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        EditText edtNgaySinh = (getActivity().findViewById(R.id.edt_ngay_sinh));
        String sNgaySinh = i2 + "/" + (i1+1) +"/"+ i;
        edtNgaySinh.setText(sNgaySinh);

    }
}
