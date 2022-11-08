

package edu.uncc.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.uncc.inclass05.databinding.ActivityMainBinding;
import edu.uncc.inclass05.fragments.AppCategoriesFragment;
import edu.uncc.inclass05.fragments.AppDetailsFragment;
import edu.uncc.inclass05.fragments.AppsListFragment;
import edu.uncc.inclass05.models.DataServices;

public class MainActivity extends AppCompatActivity implements AppCategoriesFragment.IListener, AppsListFragment.IListener {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new AppCategoriesFragment())
                .commit();
    }


    @Override
    public void categoryClicked(String categoryChosen) {
        AppsListFragment.appsByCategory(categoryChosen);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new AppsListFragment()).addToBackStack(null).commit();
    }

    @Override
    public void appChosen(DataServices.App appClickedOn) {
        AppDetailsFragment.appClickedOn(appClickedOn);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new AppDetailsFragment()).addToBackStack(null).commit();
    }
}