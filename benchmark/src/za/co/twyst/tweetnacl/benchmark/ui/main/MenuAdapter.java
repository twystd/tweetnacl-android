package za.co.twyst.tweetnacl.benchmark.ui.main;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.ui.widgets.Lozenge;

public class MenuAdapter extends BaseAdapter {
    // CONSTANTS
    
    @SuppressWarnings("unused")
    private static final String TAG = MenuAdapter.class.getSimpleName();
    
    private static final String MAVEN_PRO  = "fonts/MavenProLight-300.otf";
    private static final String MYRIAD_PRO = "fonts/MyriadPro-BlackIt.otf";
    
    // INSTANCE VARIABLES
    
    private final Context        context;
    private final LayoutInflater inflater;
    private final Drawable[]     background;
    private final Typeface       typeface;
    
    // CONSTRUCTOR
    
    public MenuAdapter(Fragment fragment) {
        this.context    = fragment.getActivity();
        this.inflater   = fragment.getActivity().getLayoutInflater();
        this.typeface   = Typeface.createFromAsset(context.getAssets(),MYRIAD_PRO);
        this.background = new Drawable[] { new ShapeDrawable(new Lozenge(false)),
                                           new ShapeDrawable(new Lozenge(true))
                                         };
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
    public int getItemViewType(int position) {
        return position % background.length;
    }

    @Override
    public int getViewTypeCount() {
        return background.length;
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
        
        holder.initialise (item);
        view.setBackground(background[position % background.length]);
        
        return view;
    }

    // INNER CLASSES
    
    private class Holder { 
        private final TextView label;
            
        private Holder(View view) {
            this.label = (TextView) view.findViewById(R.id.label);
            
            this.label.setTypeface(typeface);
        }
        
        private void initialise(MENUITEM item) {
            this.label.setText(context.getResources().getString(item.label));
        }
    }
}
