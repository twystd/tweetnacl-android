package za.co.twyst.tweetnacl.benchmark.ui.main;

import za.co.twyst.tweetnacl.benchmark.R;

public enum MENUITEM { 
    SUMMARY              (R.string.menuitem_summary),
    CRYPTO_BOX           (R.string.menuitem_crypto_box),
    CRYPTO_CORE          (R.string.menuitem_crypto_core),
    CRYPTO_HASH          (R.string.menuitem_crypto_hash),
    CRYPTO_ONETIMEAUTH   (R.string.menuitem_crypto_onetimeauth),
    CRYPTO_SCALARMULT    (R.string.menuitem_crypto_scalarmult),
    CRYPTO_SECRETBOX     (R.string.menuitem_crypto_secretbox),
    CRYPTO_STREAM        (R.string.menuitem_crypto_stream);

    final int label;
                        
    private MENUITEM(int label) {
        this.label = label;
    }
}