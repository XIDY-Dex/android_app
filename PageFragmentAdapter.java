package com.example.worldskills;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class PageFragmentAdapter extends FragmentStateAdapter {
    public PageFragmentAdapter(FragmentActivity fragmentActivity) {
    super(fragmentActivity);
}

    public PageFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return PageFragment.newInstance(position);
    }

    @Override
    public int getItemCount() { return 3; }

}
