#include <jni.h>
#include <android/log.h>
#include "tweetnacl.h"

typedef unsigned char u8;
typedef unsigned long u32;
typedef unsigned long long u64;
typedef long long i64;
typedef i64 gf[16];

extern void randombytes(u8 *,u64);

//crypto_box_beforenm
//crypto_box_afternm
//crypto_box_open_afternm
//crypto_core_salsa20
//crypto_core_hsalsa20
//crypto_hashblocks = crypto_hashblocks_sha512
//crypto_hash = crypto_hash_sha512
//crypto_onetimeauth = crypto_onetimeauth_poly1305
//crypto_onetimeauth_verify
//crypto_scalarmult = crypto_scalarmult_curve25519
//crypto_scalarmult_base
//crypto_secretbox = crypto_secretbox_xsalsa20poly1305
//crypto_secretbox_open
//crypto_sign = crypto_sign_ed25519
//crypto_sign_open
//crypto_sign_keypair
//crypto_stream = crypto_stream_xsalsa20
//crypto_stream_xor
//crypto_stream_salsa20
//crypto_stream_salsa20_xor
//crypto_verify_16
//crypto_verify_32

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
	unsigned char n[24];
	unsigned char pk[32];
	unsigned char sk[32];

    (*env)->GetByteArrayRegion(env,message,  0,N, m);
    (*env)->GetByteArrayRegion(env,nonce,    0,24,n);
    (*env)->GetByteArrayRegion(env,publicKey,0,32,pk);
    (*env)->GetByteArrayRegion(env,secretKey,0,32,sk);

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
	unsigned char n[24];
	unsigned char pk[32];
	unsigned char sk[32];

    (*env)->GetByteArrayRegion(env,ciphertext,0, N,c);
    (*env)->GetByteArrayRegion(env,nonce,     0,24,n);
    (*env)->GetByteArrayRegion(env,publicKey, 0,32,pk);
    (*env)->GetByteArrayRegion(env,secretKey, 0,32,sk);

	int rc = crypto_box_open(m,c,N,n,pk,sk);

    (*env)->SetByteArrayRegion(env,message,0,N,m);

    return (jint) rc;
}
