package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;

/**
 * Created by Andrey on 05.02.2016.
 */
public class ContactsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_contacts, null, false);
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));
        ((MainActivity)getActivity()).checkMenu(R.id.drawer_contacts);
        return convertView;
    }
}
