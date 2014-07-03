#include <jni.h>
#include <android/log.h>
#include "tweetnacl.h"

typedef unsigned char u8;
typedef unsigned long u32;
typedef unsigned long long u64;
typedef long long i64;
typedef i64 gf[16];

// __android_log_print(ANDROID_LOG_INFO,"TBXML","ATTRIBUTE: %s::%s",name,value);

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

    (*env)->SetByteArrayRegion(env,publicKey,0,crypto_box_PUBLICKEYBYTES,pk);
    (*env)->SetByteArrayRegion(env,secretKey,0,crypto_box_SECRETKEYBYTES,sk);

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

    (*env)->SetByteArrayRegion(env,ciphertext,0,N,c);

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

    (*env)->SetByteArrayRegion(env,message,0,N,m);

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

    (*env)->SetByteArrayRegion(env,key,0,crypto_box_BEFORENMBYTES,k);

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

    (*env)->SetByteArrayRegion(env,ciphertext,0,N,c);

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

    (*env)->SetByteArrayRegion(env,message,0,N,m);

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

     (*env)->SetByteArrayRegion(env,out,0,crypto_core_hsalsa20_OUTPUTBYTES,o);

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

     (*env)->SetByteArrayRegion(env,out,0,crypto_core_salsa20_OUTPUTBYTES,o);

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

    (*env)->SetByteArrayRegion(env,hash,0,crypto_hash_BYTES,h);

    return (jint) rc;
}

/** jniCryptoHashSha512
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoHashSha512(JNIEnv *env,jobject object,jbyteArray hash,jbyteArray message) {
	int      N  = (*env)->GetArrayLength(env,message);
	unsigned char m[N];
	unsigned char h[crypto_hash_BYTES];

    (*env)->GetByteArrayRegion(env,message,0,N,m);

	int rc = crypto_hash_sha512(h,m,(u64) N);

    (*env)->SetByteArrayRegion(env,hash,0,crypto_hash_BYTES,h);

    return (jint) rc;
}
