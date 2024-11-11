package com.example.purchaselist.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.purchaselist.R;
import com.example.purchaselist.models.MyList;
import com.example.purchaselist.utils.AppConstant;
import com.example.purchaselist.utils.TestUtils;

public class AddListDialog extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_add_list_title)
                .setView(R.layout.add_lists_dialog)
                .setPositiveButton(R.string.dialog_ok, (dialog, which) -> confirmAddition())
                .setNegativeButton(R.string.dialog_no, null)
                .create();
    }

    private void confirmAddition() {
        Dialog d = getDialog();
        if (d == null) {
            return;
        }

        // создаём объект MyList
        EditText nameEditText = d.findViewById(R.id.list_name_edit_text);
        EditText descriptionEditText = d.findViewById(R.id.list_description_edit_text);
        MyList myList = new MyList(
                0,
                nameEditText.getText().toString().trim(),
                TestUtils.getCurrentDateBySecond(),
                descriptionEditText.getText().toString().trim()
        );

        // используем setFragmentResult для передачи данных
        Bundle result = new Bundle();
        result.putParcelable(AppConstant.MY_LIST_ADD_DIALOG_KEY, myList);
        getParentFragmentManager().setFragmentResult(AppConstant.MY_LIST_ADD_DIALOG_REQUEST_KEY, result);

        dismiss();
    }
}
