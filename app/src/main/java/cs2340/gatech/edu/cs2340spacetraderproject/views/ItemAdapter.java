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
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.R;

/**
 * Adapts the list of tradegood items in the model to be a list of graphical elements in view
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    /** a copy of the list of items in the model */
    private Market market = Market.Market();

    private List<TradeGood> itemList = new ArrayList<>(); //will be list of keys (tradegood names)


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
                .inflate(R.layout.tradegood_item, parent, false);

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
        TradeGood item = itemList.get(position);
        Log.d("Null?", item.getName());

        Log.d("APP", "Binding: " + position + " " + itemList.get(position));

        //replace the contents of the view with that element
        holder.name.setText(item.getName());
        holder.price.setText("" + (item.getBasePrice() +
                (item.getIPL() * (market.getSS().getTech() - item.getMTLP()))));
        //omitted var for now, will go back to change
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List<TradeGood> items) {
        itemList = items;
        notifyDataSetChanged();

    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView quantity;
        private TextView price;
        //price + (basePrice * var), actually ignoring the basePrice * var for now

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            //quantity = itemView.findViewById(R.id.item_quantity);
            price = itemView.findViewById(R.id.item_price);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(itemList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(TradeGood item);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
