package za.co.twyst.tweetnacl.benchmark.ui.main;

import za.co.twyst.tweetnacl.benchmark.R;

public enum MENUITEM { 
    SUMMARY   (R.string.summary),
    CRYPTO_BOX(R.string.crypto_box);

    final int label;
                        
    private MENUITEM(int label) {
        this.label = label;
    }
}