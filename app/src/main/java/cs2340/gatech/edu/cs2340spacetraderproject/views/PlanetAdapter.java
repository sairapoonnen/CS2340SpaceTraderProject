package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

/**
 * Adapts the list of Planets in the model to be a list of graphical elements in view
 */
public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ItemViewHolder>{

    private List<SolarSystem> planetList = new ArrayList<>();

    /** a listener for a touch event on the item */
    private OnItemClickListener listener;


    /**
     * create new views (invoked by the layout manager)
     * @param parent
     * @param i viewType
     * @return
     */
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single item in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planet_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        //get element from your dataset at this position
        //bind the student data for one student
        SolarSystem planet = planetList.get(position);
        Log.d("Null?", planet.getName());

        Log.d("APP", "Binding: " + position + " " + planetList.get(position));

        //replace the contents of the view with that element
        holder.name.setText(planet.getName());
        holder.tech.setText(planet.getTechArray()[planet.getTech()]);
        holder.resource.setText(planet.getResourceArray()[planet.getResource()]);
        holder.location.setText("(" + planet.getX() + ", " + planet.getY() + ")");
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public void setItemList(List<SolarSystem> planets) {
        planetList = planets;
        notifyDataSetChanged();

    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView tech;
        private TextView resource;
        private TextView location;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.planet_name);
            //quantity = itemView.findViewById(R.id.item_quantity);
            tech = itemView.findViewById(R.id.planet_tech);
            resource = itemView.findViewById(R.id.planet_resouce);
            location = itemView.findViewById(R.id.planet_location);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(planetList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(SolarSystem planet);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}