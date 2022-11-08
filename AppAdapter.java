

package edu.uncc.inclass05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.uncc.inclass05.models.DataServices;

public class AppAdapter extends ArrayAdapter<DataServices.App> {
    public AppAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.App> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.applist, parent, false);
        }

        DataServices.App app = getItem(position);

        TextView textviewName = convertView.findViewById(R.id.appNameTextview);
        TextView textviewArtist = convertView.findViewById(R.id.artistNameTextview);
        TextView textviewDate = convertView.findViewById(R.id.releaseDateTextview);

        textviewName.setText(app.name);
        textviewArtist.setText(app.artistName);
        textviewDate.setText(app.releaseDate);

        return convertView;

    }
}
