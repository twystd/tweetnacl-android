#include <stdlib.h>
#include <jni.h>
#include <android/log.h>
#include "tweetnacl.h"

typedef unsigned char u8;
typedef unsigned long u32;
typedef unsigned long long u64;
typedef long long i64;
typedef i64 gf[16];

// __android_log_print(ANDROID_LOG_INFO,"TweetNaCl","ATTRIBUTE: %s::%s",name,value);

const char HEX[16] = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };

char *tohex(const u8 *b,u64 n,char *string) {
	int ix = 0;
	int i;

	memset(string,0,n*2 + 1);

	for (i=0; i<n; i++) {
        string[ix++] = HEX[(b[i] >> 4) & 0x000f];
        string[ix++] = HEX[(b[i] >> 0) & 0x000f];
	}

	return string;
}

/** jniRandomBytes
 *
 */
void Java_za_co_twyst_tweetnacl_TweetNaCl_jniRandomBytes(JNIEnv *env,jobject object,jbyteArray bytes) {
     int                N = (*env)->GetArrayLength(env,bytes);
 	 unsigned long long l = (unsigned long long) N;
 	 unsigned char      b[N];
 	 int                i;

	 randombytes(b,l);

     (*env)->SetByteArrayRegion(env,bytes,0,N,b);
}

/** jniCryptoBoxKeyPair
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxKeyPair(JNIEnv *env,jobject object,jbyteArray publicKey,jbyteArray secretKey) {
	unsigned char pk[crypto_box_PUBLICKEYBYTES];
	unsigned char sk[crypto_box_SECRETKEYBYTES];

	int rc = crypto_box_keypair(pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,publicKey,0,crypto_box_PUBLICKEYBYTES,pk);
		(*env)->SetByteArrayRegion(env,secretKey,0,crypto_box_SECRETKEYBYTES,sk);
	}

    return (jint) rc;
}

/** jniCryptoBox
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBox(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray publicKey,jbyteArray secretKey) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char c[N];
	unsigned char m[N];
	unsigned char n[crypto_box_NONCEBYTES];
	unsigned char pk[crypto_box_PUBLICKEYBYTES];
	unsigned char sk[crypto_box_SECRETKEYBYTES];

    (*env)->GetByteArrayRegion(env,message,  0,N, m);
    (*env)->GetByteArrayRegion(env,nonce,    0,crypto_box_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,publicKey,0,crypto_box_PUBLICKEYBYTES,pk);
    (*env)->GetByteArrayRegion(env,secretKey,0,crypto_box_SECRETKEYBYTES,sk);

	int rc = crypto_box(c,m,N,n,pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,ciphertext,0,N,c);
	}

    return (jint) rc;
}

/** jniCryptoBoxOpen
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxOpen(JNIEnv *env,jobject object,jbyteArray message,jbyteArray ciphertext,jbyteArray nonce,jbyteArray publicKey,jbyteArray secretKey) {
	int      N  = (*env)->GetArrayLength(env,ciphertext);
	unsigned char c[N];
	unsigned char m[N];
	unsigned char n[crypto_box_NONCEBYTES];
	unsigned char pk[crypto_box_PUBLICKEYBYTES];
	unsigned char sk[crypto_box_SECRETKEYBYTES];

    (*env)->GetByteArrayRegion(env,ciphertext,0, N,c);
    (*env)->GetByteArrayRegion(env,nonce,     0,crypto_box_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,publicKey, 0,crypto_box_PUBLICKEYBYTES,pk);
    (*env)->GetByteArrayRegion(env,secretKey, 0,crypto_box_SECRETKEYBYTES,sk);

	int rc = crypto_box_open(m,c,N,n,pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,message,0,N,m);
	}

    return (jint) rc;
}

/** jniCryptoBoxBeforeNM
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxBeforeNM(JNIEnv *env,jobject object,jbyteArray key,jbyteArray publicKey,jbyteArray secretKey) {
	unsigned char k [crypto_box_BEFORENMBYTES];
	unsigned char pk[crypto_box_PUBLICKEYBYTES];
	unsigned char sk[crypto_box_SECRETKEYBYTES];

    (*env)->GetByteArrayRegion(env,publicKey,0,crypto_box_PUBLICKEYBYTES,pk);
    (*env)->GetByteArrayRegion(env,secretKey,0,crypto_box_SECRETKEYBYTES,sk);

	int rc = crypto_box_beforenm(k,pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,key,0,crypto_box_BEFORENMBYTES,k);
	}

    return (jint) rc;
}

/** jniCryptoBoxAfterNM
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxAfterNM(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char c[N];
	unsigned char m[N];
	unsigned char n[crypto_box_NONCEBYTES];
	unsigned char k[crypto_box_BEFORENMBYTES];

    (*env)->GetByteArrayRegion(env,message,0,N,                       m);
    (*env)->GetByteArrayRegion(env,nonce,  0,crypto_box_NONCEBYTES,   n);
    (*env)->GetByteArrayRegion(env,key,    0,crypto_box_BEFORENMBYTES,k);

	int rc = crypto_box_afternm(c,m,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,ciphertext,0,N,c);
	}

    return (jint) rc;
}

/** jniCryptoBoxOpenAfterNM
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxOpenAfterNM(JNIEnv *env,jobject object,jbyteArray message,jbyteArray ciphertext,jbyteArray nonce,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,ciphertext);
	unsigned char c[N];
	unsigned char m[N];
	unsigned char n[crypto_box_NONCEBYTES];
	unsigned char k[crypto_box_BEFORENMBYTES];

    (*env)->GetByteArrayRegion(env,ciphertext,0, N,c);
    (*env)->GetByteArrayRegion(env,nonce,     0,crypto_box_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,key,       0,crypto_box_BEFORENMBYTES,k);

	int rc = crypto_box_open_afternm(m,c,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,message,0,N,m);
	}

    return (jint) rc;
}

/** jniCryptoCoreHSalsa20
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoCoreHSalsa20(JNIEnv *env,jobject object,jbyteArray out,jbyteArray in,jbyteArray key,jbyteArray constant) {
	unsigned char o[crypto_core_hsalsa20_OUTPUTBYTES];
	unsigned char i[crypto_core_hsalsa20_INPUTBYTES];
	unsigned char k[crypto_core_hsalsa20_KEYBYTES];
	unsigned char c[crypto_core_hsalsa20_CONSTBYTES];

    (*env)->GetByteArrayRegion(env,in,      0,crypto_core_hsalsa20_INPUTBYTES,i);
    (*env)->GetByteArrayRegion(env,key,     0,crypto_core_hsalsa20_KEYBYTES,  k);
    (*env)->GetByteArrayRegion(env,constant,0,crypto_core_hsalsa20_CONSTBYTES,c);

	int rc = crypto_core_hsalsa20(o,i,k,c);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,out,0,crypto_core_hsalsa20_OUTPUTBYTES,o);
	}

    return (jint) rc;
}

/** jniCryptoCoreSSalsa20
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoCoreSalsa20(JNIEnv *env,jobject object,jbyteArray out,jbyteArray in,jbyteArray key,jbyteArray constant) {
	unsigned char o[crypto_core_salsa20_OUTPUTBYTES];
	unsigned char i[crypto_core_salsa20_INPUTBYTES];
	unsigned char k[crypto_core_salsa20_KEYBYTES];
	unsigned char c[crypto_core_salsa20_CONSTBYTES];

    (*env)->GetByteArrayRegion(env,in,      0,crypto_core_salsa20_INPUTBYTES,i);
    (*env)->GetByteArrayRegion(env,key,     0,crypto_core_salsa20_KEYBYTES,  k);
    (*env)->GetByteArrayRegion(env,constant,0,crypto_core_salsa20_CONSTBYTES,c);

	int rc = crypto_core_salsa20(o,i,k,c);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,out,0,crypto_core_salsa20_OUTPUTBYTES,o);
	}

    return (jint) rc;
}

/** jniCryptoHash
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoHash(JNIEnv *env,jobject object,jbyteArray hash,jbyteArray message) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char m[N];
	unsigned char h[crypto_hash_BYTES];

    (*env)->GetByteArrayRegion(env,message,0,N,m);

	int rc = crypto_hash(h,m,(u64) N);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,hash,0,crypto_hash_BYTES,h);
	}

    return (jint) rc;
}

/** jniCryptoHashBlocks
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoHashBlocks(JNIEnv *env,jobject object,jbyteArray hash,jbyteArray message) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char m[N];
	unsigned char h[crypto_hashblocks_STATEBYTES];

    (*env)->GetByteArrayRegion(env,message,0,N,m);
    (*env)->GetByteArrayRegion(env,hash,   0,crypto_hashblocks_STATEBYTES,h);

	int rc = crypto_hashblocks(h,m,N);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,hash,0,crypto_hashblocks_STATEBYTES,h);
	}

    return (jint) rc;
}

/** jniCryptoOneTimeAuth
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoOneTimeAuth(JNIEnv *env,jobject object,jbyteArray auth,jbyteArray message,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char m[N];
	unsigned char k[crypto_onetimeauth_KEYBYTES];
	unsigned char a[crypto_onetimeauth_BYTES];

    (*env)->GetByteArrayRegion(env,message,0,N,m);
    (*env)->GetByteArrayRegion(env,key,    0,crypto_onetimeauth_KEYBYTES,k);

	int rc = crypto_onetimeauth(a,m,N,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,auth,0,crypto_onetimeauth_BYTES,a);
	}

    return (jint) rc;
}

/** jniCryptoOneTimeAuthVerify
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoOneTimeAuthVerify(JNIEnv *env,jobject object,jbyteArray auth,jbyteArray message,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char m[N];
	unsigned char k[crypto_onetimeauth_KEYBYTES];
	unsigned char a[crypto_onetimeauth_BYTES];

    (*env)->GetByteArrayRegion(env,auth,   0,crypto_onetimeauth_BYTES,a);
    (*env)->GetByteArrayRegion(env,message,0,N,m);
    (*env)->GetByteArrayRegion(env,key,    0,crypto_onetimeauth_KEYBYTES,k);

	return (jint) crypto_onetimeauth_verify(a,m,N,k);
}

/** jniCryptoScalarMultBase
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoScalarMultBase(JNIEnv *env,jobject object,jbyteArray Q,jbyteArray N) {
	unsigned char n[crypto_scalarmult_BYTES];
	unsigned char q[crypto_scalarmult_SCALARBYTES];

    (*env)->GetByteArrayRegion(env,N,0,crypto_scalarmult_BYTES,n);

	int rc = crypto_scalarmult_base(q,n);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,Q,0,crypto_scalarmult_SCALARBYTES,q);
	}

    return (jint) rc;
}

/** jniCryptoScalarMult
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoScalarMult(JNIEnv *env,jobject object,jbyteArray Q,jbyteArray N,jbyteArray P) {
	unsigned char n[crypto_scalarmult_BYTES];
	unsigned char p[crypto_scalarmult_BYTES];
	unsigned char q[crypto_scalarmult_SCALARBYTES];

    (*env)->GetByteArrayRegion(env,N,0,crypto_scalarmult_BYTES,n);
    (*env)->GetByteArrayRegion(env,P,0,crypto_scalarmult_BYTES,p);

	int rc = crypto_scalarmult(q,n,p);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,Q,0,crypto_scalarmult_SCALARBYTES,q);
	}

    return (jint) rc;
}

/** jniCryptoSecretBox
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSecretBox(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char c[N];
	unsigned char m[N];
	unsigned char n[crypto_secretbox_NONCEBYTES];
	unsigned char k[crypto_secretbox_KEYBYTES];

    (*env)->GetByteArrayRegion(env,message,0,N,m);
    (*env)->GetByteArrayRegion(env,nonce,  0,crypto_secretbox_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,key,    0,crypto_secretbox_KEYBYTES,  k);

	int rc = crypto_secretbox(c,m,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,ciphertext,0,N,c);
	}

    return (jint) rc;
}

/** jniCryptoSecretBoxOpen
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSecretBoxOpen(JNIEnv *env,jobject object,jbyteArray plaintext,jbyteArray ciphertext,jbyteArray nonce,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,ciphertext);
	unsigned char c[N];
	unsigned char m[N];
	unsigned char n[crypto_secretbox_NONCEBYTES];
	unsigned char k[crypto_secretbox_KEYBYTES];

    (*env)->GetByteArrayRegion(env,ciphertext,0,N,c);
    (*env)->GetByteArrayRegion(env,nonce,     0,crypto_secretbox_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,key,       0,crypto_secretbox_KEYBYTES,  k);

	int rc = crypto_secretbox_open(m,c,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,plaintext,0,N,m);
	}

    return (jint) rc;
}

/** jniCryptoStream
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStream(JNIEnv *env,jobject object,jbyteArray stream,jbyteArray nonce,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,stream);
	unsigned char c[N];
	unsigned char n[crypto_stream_NONCEBYTES];
	unsigned char k[crypto_stream_KEYBYTES];

    (*env)->GetByteArrayRegion(env,nonce,0,crypto_stream_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,key,  0,crypto_stream_KEYBYTES,  k);

	int rc = crypto_stream(c,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,stream,0,N,c);
	}

    return (jint) rc;
}

/** jniCryptoStreamXor
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStreamXor(JNIEnv *env,jobject object,jbyteArray crypttext,jbyteArray plaintext,jbyteArray nonce,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,plaintext);
	unsigned char m[N];
	unsigned char c[N];
	unsigned char n[crypto_stream_NONCEBYTES];
	unsigned char k[crypto_stream_KEYBYTES];

    (*env)->GetByteArrayRegion(env,plaintext,0,N,m);
    (*env)->GetByteArrayRegion(env,nonce,    0,crypto_stream_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,key,      0,crypto_stream_KEYBYTES,  k);

	int rc = crypto_stream_xor(c,m,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,crypttext,0,N,c);
	}

    return (jint) rc;
}


/** jniCryptoStreamSalsa20
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStreamSalsa20(JNIEnv *env,jobject object,jbyteArray stream,jbyteArray nonce,jbyteArray key) {
	int            N = (*env)->GetArrayLength(env,stream);
	unsigned char *c = (unsigned char *) malloc(N);
	unsigned char n[crypto_stream_salsa20_NONCEBYTES];
	unsigned char k[crypto_stream_salsa20_KEYBYTES];

    (*env)->GetByteArrayRegion(env,nonce,0,crypto_stream_salsa20_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,key,  0,crypto_stream_salsa20_KEYBYTES,  k);

	int rc = crypto_stream_salsa20(c,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,stream,0,N,c);
	}

    free(c);

    return (jint) rc;
}


/** jniCryptoStreamSalsa20Xor
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStreamSalsa20Xor(JNIEnv *env,jobject object,jbyteArray crypttext,jbyteArray plaintext,jbyteArray nonce,jbyteArray key) {
	int      N  = (*env)->GetArrayLength(env,plaintext);
	unsigned char m[N];
	unsigned char c[N];
	unsigned char n[crypto_stream_salsa20_NONCEBYTES];
	unsigned char k[crypto_stream_salsa20_KEYBYTES];

    (*env)->GetByteArrayRegion(env,plaintext,0,N,m);
    (*env)->GetByteArrayRegion(env,nonce,    0,crypto_stream_salsa20_NONCEBYTES,n);
    (*env)->GetByteArrayRegion(env,key,      0,crypto_stream_salsa20_KEYBYTES,  k);

	int rc = crypto_stream_salsa20_xor(c,m,N,n,k);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,crypttext,0,N,c);
	}

    return (jint) rc;
}

/** jniCryptoSignKeyPair
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSignKeyPair(JNIEnv *env,jobject object,jbyteArray publicKey,jbyteArray secretKey) {
	unsigned char pk[crypto_sign_PUBLICKEYBYTES];
	unsigned char sk[crypto_sign_SECRETKEYBYTES];

	int rc = crypto_sign_keypair(pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,publicKey,0,crypto_sign_PUBLICKEYBYTES,pk);
		(*env)->SetByteArrayRegion(env,secretKey,0,crypto_sign_SECRETKEYBYTES,sk);
	}

    return (jint) rc;
}

/** jniCryptoSign
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSign(JNIEnv *env,jobject object,jbyteArray signature,jbyteArray message,jbyteArray secretKey) {
	int            N     = (*env)->GetArrayLength(env,message);
	u64            smlen = (*env)->GetArrayLength(env,signature);
	unsigned char *m     = (unsigned char *) malloc(N);
	unsigned char *sm    = (unsigned char *) malloc(smlen);
	unsigned char sk[crypto_sign_SECRETKEYBYTES];

    (*env)->GetByteArrayRegion(env,message,  0,N, m);
    (*env)->GetByteArrayRegion(env,secretKey,0,crypto_sign_SECRETKEYBYTES,sk);

	int rc = crypto_sign(sm,&smlen,m,N,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,signature,0,smlen,sm);
	}

    free(sm);
    free(m);

    return (jint) rc;
}

/** jniCryptoSignOpen
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSignOpen(JNIEnv *env,jobject object,jbyteArray message,jbyteArray signature,jbyteArray publicKey) {
	int            N     = (*env)->GetArrayLength(env,signature);
	u64            mlen  = (*env)->GetArrayLength(env,message);
	unsigned char *sm    = (unsigned char *) malloc(N);
	unsigned char *m     = (unsigned char *) malloc(N);
	unsigned char  pk[crypto_sign_PUBLICKEYBYTES];

    (*env)->GetByteArrayRegion(env,signature,0,N,sm);
    (*env)->GetByteArrayRegion(env,publicKey,0,crypto_sign_PUBLICKEYBYTES,pk);

	int rc = crypto_sign_open(m,&mlen,sm,N,pk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,message,0,mlen,m);
	}

    free(sm);
    free(m);

    return rc == 0 ? (jint) mlen : (jint) rc;
}
