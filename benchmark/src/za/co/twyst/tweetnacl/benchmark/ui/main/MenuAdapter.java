package za.co.twyst.tweetnacl.benchmark.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import za.co.twyst.tweetnacl.benchmark.R;

public class MenuAdapter extends BaseAdapter {
    // CONSTANTS
    
    @SuppressWarnings("unused")
    private static final String TAG = MenuAdapter.class.getSimpleName();
    
    // INSTANCE VARIABLES
    
    private Context        context;
    private LayoutInflater inflater;
    
    // CONSTRUCTOR
    
    public MenuAdapter(Fragment fragment) {
        this.context  = fragment.getActivity();
        this.inflater = fragment.getActivity().getLayoutInflater();
    }
    
    // *** BaseAdapter ***

    @Override
    public int getCount() {
        return MENUITEM.values().length;
    }

    @Override
    public Object getItem(int position) {
        return MENUITEM.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MENUITEM item = MENUITEM.values()[position];
        View     view = convertView;
        Holder   holder;
        
        if (view == null) {
            view   = inflater.inflate(R.layout.listitem_menu,parent,false);
            holder = new Holder(view);
            
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        
        holder.initialise(item);
        
        return view;
    }

    // INNER CLASSES
    
    private class Holder { 
        private final TextView label;
            
        private Holder(View view) {
            this.label = (TextView) view.findViewById(R.id.label);
        }
        
        private void initialise(MENUITEM item) {
            this.label.setText(context.getResources().getString(item.label));
        }
    }
}
