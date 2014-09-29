package za.co.twyst.tweetnacl.benchmark.ui.main;

import android.support.v4.app.Fragment;

import za.co.twyst.tweetnacl.benchmark.R;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoBoxFragment;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoCoreFragment;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoHashFragment;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoOneTimeAuthFragment;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoScalarMultFragment;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoSecretBoxFragment;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoSignFragment;
import za.co.twyst.tweetnacl.benchmark.ui.crypto.CryptoStreamFragment;
import za.co.twyst.tweetnacl.benchmark.ui.summary.SummaryFragment;

public enum MENUITEM { 
    SUMMARY              (R.string.menuitem_summary,           SummaryFragment.class),
    CRYPTO_BOX           (R.string.menuitem_crypto_box,        CryptoBoxFragment.class),
    CRYPTO_CORE          (R.string.menuitem_crypto_core,       CryptoCoreFragment.class),
    CRYPTO_HASH          (R.string.menuitem_crypto_hash,       CryptoHashFragment.class),
    CRYPTO_ONETIMEAUTH   (R.string.menuitem_crypto_onetimeauth,CryptoOneTimeAuthFragment.class),
    CRYPTO_SCALARMULT    (R.string.menuitem_crypto_scalarmult, CryptoScalarMultFragment.class),
    CRYPTO_SECRETBOX     (R.string.menuitem_crypto_secretbox,  CryptoSecretBoxFragment.class),
    CRYPTO_STREAM        (R.string.menuitem_crypto_stream,     CryptoStreamFragment.class),
    CRYPTO_SIGN          (R.string.menuitem_crypto_sign,       CryptoSignFragment.class);

    public final int                             label;
    public final Class<? extends Fragment> clazz;
                        
    private MENUITEM(int label,Class<? extends Fragment> clazz) {
        this.label = label;
        this.clazz = clazz;
    }
}