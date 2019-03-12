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

import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

/**
 * Adapts the list of tradegood items in the model to be a list of graphical elements in view
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    /** a copy of the list of items in the model */
    private List<TradeGood> itemList = new ArrayList<>();
    //not sure if it should be a list of TradeGood or should we export TradeGood info into another format?

    /** a listener for a touch event on the item */
    private OnItemCLickListener listener;

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView quantity;
        private TextView price;

        public ItemViewHolder(TextView itemView) {
            super(itemView);
            //code
        }
    }

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
                .inflate(R.layout.student_item, parent, false);

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
        Student student = studentList.get(position);

        Log.d("APP", "Binding: " + position + " " + studentList.get(position));

        //replace the contents of the view with that element
        holder.name.setText(item.getName());
        holder.quantity.setText(item.);
        holder.price.setText(student.getClassStanding().getYear());

    }



}
