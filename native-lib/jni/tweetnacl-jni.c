#include <jni.h>
#include <android/log.h>
#include "tweetnacl.h"

typedef unsigned char u8;
typedef unsigned long u32;
typedef unsigned long long u64;
typedef long long i64;
typedef i64 gf[16];

extern void randombytes(u8 *,u64);

/** randombytes
 *
 *  Fills a byte array using SecureRandom.
 */
void randombytes(u8 *buffer,u64 N) {
	u64 i;

	__android_log_print(ANDROID_LOG_INFO,"TweetNaCl","randomBytes");


	for (i=0; i<N; i++) {
		buffer[i] = i % 256;
	}
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
