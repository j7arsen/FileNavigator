package com.j7arsen.filenavigator.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.j7arsen.filenavigator.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import moxy.MvpAppCompatDialogFragment;

public abstract class BaseDialogFragment extends MvpAppCompatDialogFragment {

    private Unbinder unbinder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(getLayout(), null);
        unbinder = ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Theme_Dialog);
        builder.setView(view);

        return builder.create();
    }

    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
