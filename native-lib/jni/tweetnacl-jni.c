#include <stdlib.h>
#include <jni.h>
#include <android/log.h>
#include "tweetnacl.h"

typedef unsigned char       u8;
typedef unsigned long       u32;
typedef unsigned long long  u64;
typedef long long           i64;
typedef i64                 gf[16];

#define YES 1
#define M   0
#define C   1
#define H   1
#define S   1

// __android_log_print(ANDROID_LOG_INFO,"TweetNaCl","ATTRIBUTE: %s::%s",name,value);

/** Utility function to release a JNI byte array reference.
 *
 */
void release(JNIEnv *env,jbyteArray jbytes,u8 *bytes,u64 N,int discard,jboolean copied) {
	if (bytes) {
		(*env)->ReleaseByteArrayElements(env,jbytes,bytes,discard ? JNI_ABORT : 0);
	}

	if (copied) {
		memset(bytes,0,N);
	}
}

/** jniRandomBytes
 *
 */
void Java_za_co_twyst_tweetnacl_TweetNaCl_jniRandomBytes(JNIEnv *env,jobject object,jbyteArray bytes) {
     int  N = (*env)->GetArrayLength(env,bytes);
 	 u64  l = (u64) N;
 	 u8  *b = (u8 *) malloc(N);
 	 int  i;

	 randombytes(b,l);

     (*env)->SetByteArrayRegion(env,bytes,0,N,b);

     memset(b,0,N);
     free  (b);
}

/** jniCryptoBoxKeyPair
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxKeyPair(JNIEnv *env,jobject object,jbyteArray publicKey,jbyteArray secretKey) {
	u8 pk[crypto_box_PUBLICKEYBYTES];
	u8 sk[crypto_box_SECRETKEYBYTES];

	int rc = crypto_box_keypair(pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,publicKey,0,crypto_box_PUBLICKEYBYTES,pk);
		(*env)->SetByteArrayRegion(env,secretKey,0,crypto_box_SECRETKEYBYTES,sk);
	}

    memset(pk,0,crypto_box_PUBLICKEYBYTES);
    memset(sk,0,crypto_box_SECRETKEYBYTES);

    return (jint) rc;
}

/** jniCryptoBox
 *
 *  JNI wrapper function for crypto_box.
 *
 *  The code is structured around the assumption that GetByteArrayElements will
 *  succeed a lot more often than it will fail.
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBox(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray publicKey,jbyteArray secretKey) {
	jboolean copied[2];
	int      N = (*env)->GetArrayLength(env,message);
	u8      *c = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8      *m = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8       n [crypto_box_NONCEBYTES];
	u8       pk[crypto_box_PUBLICKEYBYTES];
	u8       sk[crypto_box_SECRETKEYBYTES];
	int      rc     = -2;

	if (c && m) {
	    (*env)->GetByteArrayRegion(env,nonce,     0,crypto_box_NONCEBYTES,n);
	    (*env)->GetByteArrayRegion(env,publicKey, 0,crypto_box_PUBLICKEYBYTES,pk);
	    (*env)->GetByteArrayRegion(env,secretKey, 0,crypto_box_SECRETKEYBYTES,sk);

		rc = crypto_box(c,m,N,n,pk,sk);
	}

	release(env,ciphertext,c,N,rc, copied[C]);
	release(env,message,   m,N,YES,copied[M]);

	memset(n, 0,crypto_box_NONCEBYTES);
	memset(pk,0,crypto_box_PUBLICKEYBYTES);
	memset(sk,0,crypto_box_SECRETKEYBYTES);

    return (jint) rc;
}

/** jniCryptoBoxOpen
 *
 *  JNI wrapper function for crypto_box_open.
 *
 *  The code is structured around the assumption that GetByteArrayElements will succeed
 *  a lot more often than it will fail.
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxOpen(JNIEnv *env,jobject object,jbyteArray message,jbyteArray ciphertext,jbyteArray nonce,jbyteArray publicKey,jbyteArray secretKey) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,ciphertext);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8       n [crypto_box_NONCEBYTES];
	u8       pk[crypto_box_PUBLICKEYBYTES];
	u8       sk[crypto_box_SECRETKEYBYTES];

	if (c && m) {
	    (*env)->GetByteArrayRegion(env,nonce,     0,crypto_box_NONCEBYTES,n);
	    (*env)->GetByteArrayRegion(env,publicKey, 0,crypto_box_PUBLICKEYBYTES,pk);
	    (*env)->GetByteArrayRegion(env,secretKey, 0,crypto_box_SECRETKEYBYTES,sk);

		rc = crypto_box_open(m,c,N,n,pk,sk);
	}

	release(env,message,   m,N,rc, copied[M]);
	release(env,ciphertext,c,N,YES,copied[C]);

	memset(n, 0,crypto_box_NONCEBYTES);
	memset(pk,0,crypto_box_PUBLICKEYBYTES);
	memset(sk,0,crypto_box_SECRETKEYBYTES);

    return (jint) rc;
}

/** jniCryptoBoxBeforeNM
 *
 *  JNI wrapper function for crypto_box_beforenm.
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxBeforeNM(JNIEnv *env,jobject object,jbyteArray key,jbyteArray publicKey,jbyteArray secretKey) {
	u8 k [crypto_box_BEFORENMBYTES];
	u8 pk[crypto_box_PUBLICKEYBYTES];
	u8 sk[crypto_box_SECRETKEYBYTES];

    (*env)->GetByteArrayRegion(env,publicKey,0,crypto_box_PUBLICKEYBYTES,pk);
    (*env)->GetByteArrayRegion(env,secretKey,0,crypto_box_SECRETKEYBYTES,sk);

	int rc = crypto_box_beforenm(k,pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,key,0,crypto_box_BEFORENMBYTES,k);
	}

	memset(k, 0,crypto_box_BEFORENMBYTES);
	memset(pk,0,crypto_box_PUBLICKEYBYTES);
	memset(sk,0,crypto_box_SECRETKEYBYTES);

    return (jint) rc;
}

/** jniCryptoBoxAfterNM
 *
 *  JNI wrapper function for crypto_box_afternm.
 *
 *  The code is structured around the assumption that GetByteArrayElements will succeed
 *  a lot more often than it will fail.
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxAfterNM(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray key) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,message);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8       n[crypto_box_NONCEBYTES];
	u8       k[crypto_box_BEFORENMBYTES];

	if (m && c) {
		(*env)->GetByteArrayRegion(env,nonce,0,crypto_box_NONCEBYTES,   n);
		(*env)->GetByteArrayRegion(env,key,  0,crypto_box_BEFORENMBYTES,k);

		rc = crypto_box_afternm(c,m,N,n,k);
	}

	release(env,ciphertext,c,N,rc, copied[C]);
	release(env,message,   m,N,YES,copied[M]);

	memset(n,0,crypto_box_NONCEBYTES);
	memset(k,0,crypto_box_BEFORENMBYTES);

    return (jint) rc;
}

/** jniCryptoBoxOpenAfterNM
 *
 *  JNI wrapper function for crypto_box_open_afternm.
 *
 *  The code is structured around the assumption that GetByteArrayElements will succeed
 *  a lot more often than it will fail.
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoBoxOpenAfterNM(JNIEnv *env,jobject object,jbyteArray message,jbyteArray ciphertext,jbyteArray nonce,jbyteArray key) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,ciphertext);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8       n[crypto_box_NONCEBYTES];
	u8       k[crypto_box_BEFORENMBYTES];

	if (m && c) {
		(*env)->GetByteArrayRegion(env,nonce,0,crypto_box_NONCEBYTES,   n);
		(*env)->GetByteArrayRegion(env,key,  0,crypto_box_BEFORENMBYTES,k);

		rc = crypto_box_open_afternm(m,c,N,n,k);
	}

	release(env,message,   m,N,rc, copied[M]);
	release(env,ciphertext,c,N,YES,copied[C]);

	memset(n,0,crypto_box_NONCEBYTES);
	memset(k,0,crypto_box_BEFORENMBYTES);

    return (jint) rc;
}

/** jniCryptoCoreHSalsa20
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoCoreHSalsa20(JNIEnv *env,jobject object,jbyteArray out,jbyteArray in,jbyteArray key,jbyteArray constant) {
	u8 o[crypto_core_hsalsa20_OUTPUTBYTES];
	u8 i[crypto_core_hsalsa20_INPUTBYTES];
	u8 k[crypto_core_hsalsa20_KEYBYTES];
	u8 c[crypto_core_hsalsa20_CONSTBYTES];

    (*env)->GetByteArrayRegion(env,in,      0,crypto_core_hsalsa20_INPUTBYTES,i);
    (*env)->GetByteArrayRegion(env,key,     0,crypto_core_hsalsa20_KEYBYTES,  k);
    (*env)->GetByteArrayRegion(env,constant,0,crypto_core_hsalsa20_CONSTBYTES,c);

	int rc = crypto_core_hsalsa20(o,i,k,c);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,out,0,crypto_core_hsalsa20_OUTPUTBYTES,o);
	}

	memset(o,0,crypto_core_hsalsa20_OUTPUTBYTES);
	memset(i,0,crypto_core_hsalsa20_INPUTBYTES);
	memset(k,0,crypto_core_hsalsa20_KEYBYTES);
	memset(c,0,crypto_core_hsalsa20_CONSTBYTES);

    return (jint) rc;
}

/** jniCryptoCoreSalsa20
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoCoreSalsa20(JNIEnv *env,jobject object,jbyteArray out,jbyteArray in,jbyteArray key,jbyteArray constant) {
	u8 o[crypto_core_salsa20_OUTPUTBYTES];
	u8 i[crypto_core_salsa20_INPUTBYTES];
	u8 k[crypto_core_salsa20_KEYBYTES];
	u8 c[crypto_core_salsa20_CONSTBYTES];

    (*env)->GetByteArrayRegion(env,in,      0,crypto_core_salsa20_INPUTBYTES,i);
    (*env)->GetByteArrayRegion(env,key,     0,crypto_core_salsa20_KEYBYTES,  k);
    (*env)->GetByteArrayRegion(env,constant,0,crypto_core_salsa20_CONSTBYTES,c);

	int rc = crypto_core_salsa20(o,i,k,c);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,out,0,crypto_core_salsa20_OUTPUTBYTES,o);
	}

	memset(o,0,crypto_core_salsa20_OUTPUTBYTES);
	memset(i,0,crypto_core_salsa20_INPUTBYTES);
	memset(k,0,crypto_core_salsa20_KEYBYTES);
	memset(c,0,crypto_core_salsa20_CONSTBYTES);

    return (jint) rc;
}

/** jniCryptoHash
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoHash(JNIEnv *env,jobject object,jbyteArray hash,jbyteArray message) {
	jboolean copied;
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,message);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,&copied);
	u8       h[crypto_hash_BYTES];

	if (m) {
		if ((rc = crypto_hash(h,m,(u64) N)) == 0) {
			(*env)->SetByteArrayRegion(env,hash,0,crypto_hash_BYTES,h);
		}
	}

	release(env,message,m,N,YES,copied);
	memset (h,0,crypto_hash_BYTES);

    return (jint) rc;
}

/** jniCryptoHashBlocks
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoHashBlocks(JNIEnv *env,jobject object,jbyteArray hash,jbyteArray message) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,message);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,&copied[M]);
	u8      *h  = (u8 *) (*env)->GetByteArrayElements(env,hash,   &copied[H]);

	if (m && h) {
	    rc = crypto_hashblocks(h,m,N);
	}

	release(env,message,m,N,YES,copied[M]);
	release(env,hash,   h,N,rc, copied[H]);

    return (jint) rc;
}

/** jniCryptoOneTimeAuth
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoOneTimeAuth(JNIEnv *env,jobject object,jbyteArray auth,jbyteArray message,jbyteArray key) {
	jboolean copied;
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,message);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,&copied);
	u8       k[crypto_onetimeauth_KEYBYTES];
	u8       a[crypto_onetimeauth_BYTES];

	if (m) {
		(*env)->GetByteArrayRegion(env,key,0,crypto_onetimeauth_KEYBYTES,k);

		if ((rc = crypto_onetimeauth(a,m,N,k)) == 0) {
			(*env)->SetByteArrayRegion(env,auth,0,crypto_onetimeauth_BYTES,a);
		}
	}

	release(env,message,m,N,YES,copied);

    memset(k,0,crypto_onetimeauth_KEYBYTES);
    memset(a,0,crypto_onetimeauth_BYTES);

    return (jint) rc;
}

/** jniCryptoOneTimeAuthVerify
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoOneTimeAuthVerify(JNIEnv *env,jobject object,jbyteArray auth,jbyteArray message,jbyteArray key) {
	jboolean copied;
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,message);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,&copied);
	u8       k[crypto_onetimeauth_KEYBYTES];
	u8       a[crypto_onetimeauth_BYTES];

	if (m) {
		(*env)->GetByteArrayRegion(env,auth,0,crypto_onetimeauth_BYTES,   a);
		(*env)->GetByteArrayRegion(env,key, 0,crypto_onetimeauth_KEYBYTES,k);

	    rc = crypto_onetimeauth_verify(a,m,N,k);
	}

	release(env,message,m,N,YES,copied);

	memset(k,0,crypto_onetimeauth_KEYBYTES);
	memset(a,0,crypto_onetimeauth_BYTES);

	return (jint) rc;
}

/** jniCryptoScalarMultBase
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoScalarMultBase(JNIEnv *env,jobject object,jbyteArray Q,jbyteArray N) {
	u8 n[crypto_scalarmult_SCALARBYTES];
	u8 q[crypto_scalarmult_BYTES];

    (*env)->GetByteArrayRegion(env,N,0,crypto_scalarmult_SCALARBYTES,n);

	int rc = crypto_scalarmult_base(q,n);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,Q,0,crypto_scalarmult_BYTES,q);
	}

	memset(n,0,crypto_scalarmult_SCALARBYTES);
	memset(q,0,crypto_scalarmult_BYTES);

    return (jint) rc;
}

/** jniCryptoScalarMult
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoScalarMult(JNIEnv *env,jobject object,jbyteArray Q,jbyteArray N,jbyteArray P) {
	u8 n[crypto_scalarmult_SCALARBYTES];
	u8 p[crypto_scalarmult_BYTES];
	u8 q[crypto_scalarmult_BYTES];

    (*env)->GetByteArrayRegion(env,N,0,crypto_scalarmult_SCALARBYTES,n);
    (*env)->GetByteArrayRegion(env,P,0,crypto_scalarmult_BYTES,      p);

	int rc = crypto_scalarmult(q,n,p);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,Q,0,crypto_scalarmult_BYTES,q);
	}

	memset(n,0,crypto_scalarmult_SCALARBYTES);
	memset(p,0,crypto_scalarmult_BYTES);
	memset(q,0,crypto_scalarmult_BYTES);

    return (jint) rc;
}

/** jniCryptoSecretBox
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSecretBox(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray key) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,ciphertext);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8       n[crypto_secretbox_NONCEBYTES];
	u8       k[crypto_secretbox_KEYBYTES];

	if (m && c) {
		(*env)->GetByteArrayRegion(env,nonce,0,crypto_secretbox_NONCEBYTES,n);
		(*env)->GetByteArrayRegion(env,key,  0,crypto_secretbox_KEYBYTES,  k);

		rc = crypto_secretbox(c,m,N,n,k);
	}

	release(env,message,   m,N,YES,copied[M]);
	release(env,ciphertext,c,N,rc, copied[C]);

	memset(n,0,crypto_secretbox_NONCEBYTES);
	memset(k,0,crypto_secretbox_KEYBYTES);

    return (jint) rc;
}

/** jniCryptoSecretBoxOpen
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSecretBoxOpen(JNIEnv *env,jobject object,jbyteArray message,jbyteArray ciphertext,jbyteArray nonce,jbyteArray key) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,ciphertext);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8       n[crypto_secretbox_NONCEBYTES];
	u8       k[crypto_secretbox_KEYBYTES];

	if (m && c) {
		(*env)->GetByteArrayRegion(env,nonce,0,crypto_secretbox_NONCEBYTES,n);
		(*env)->GetByteArrayRegion(env,key,  0,crypto_secretbox_KEYBYTES,  k);

	    rc = crypto_secretbox_open(m,c,N,n,k);
	}

	release(env,message,   m,N,rc, copied[M]);
	release(env,ciphertext,c,N,YES,copied[C]);

	memset(n,0,crypto_secretbox_NONCEBYTES);
	memset(k,0,crypto_secretbox_KEYBYTES);

    return (jint) rc;
}

/** jniCryptoStream
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStream(JNIEnv *env,jobject object,jbyteArray stream,jbyteArray nonce,jbyteArray key) {
	jboolean copied;
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,stream);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,stream,&copied);
	u8       n[crypto_stream_NONCEBYTES];
	u8       k[crypto_stream_KEYBYTES];

	if (c) {
		(*env)->GetByteArrayRegion(env,nonce,0,crypto_stream_NONCEBYTES,n);
		(*env)->GetByteArrayRegion(env,key,  0,crypto_stream_KEYBYTES,  k);

		rc = crypto_stream(c,N,n,k);
	}

	release(env,stream,c,N,rc,copied);

	memset(n,0,crypto_stream_NONCEBYTES);
	memset(k,0,crypto_stream_KEYBYTES);

    return (jint) rc;
}

/** jniCryptoStreamXor
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStreamXor(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray key) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,message);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8       n[crypto_stream_NONCEBYTES];
	u8       k[crypto_stream_KEYBYTES];

	if (m && c) {
		(*env)->GetByteArrayRegion(env,nonce,0,crypto_stream_NONCEBYTES,n);
		(*env)->GetByteArrayRegion(env,key,  0,crypto_stream_KEYBYTES,  k);

		rc = crypto_stream_xor(c,m,N,n,k);
	}

	release(env,ciphertext,c,N,rc, copied[C]);
	release(env,message,   m,N,YES,copied[M]);

	memset(n,0,crypto_stream_NONCEBYTES);
	memset(k,0,crypto_stream_KEYBYTES);

    return (jint) rc;
}

/** jniCryptoStreamSalsa20
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStreamSalsa20(JNIEnv *env,jobject object,jbyteArray stream,jbyteArray nonce,jbyteArray key) {
	jboolean copied;
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,stream);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,stream,&copied);
	u8       n[crypto_stream_salsa20_NONCEBYTES];
	u8       k[crypto_stream_salsa20_KEYBYTES];

	if (c) {
		(*env)->GetByteArrayRegion(env,nonce,0,crypto_stream_salsa20_NONCEBYTES,n);
		(*env)->GetByteArrayRegion(env,key,  0,crypto_stream_salsa20_KEYBYTES,  k);

		rc = crypto_stream_salsa20(c,N,n,k);
	}

	release(env,stream,c,N,rc,copied);

	memset(n,0,crypto_stream_salsa20_NONCEBYTES);
	memset(k,0,crypto_stream_salsa20_KEYBYTES);

    return (jint) rc;
}

/** jniCryptoStreamSalsa20Xor
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoStreamSalsa20Xor(JNIEnv *env,jobject object,jbyteArray ciphertext,jbyteArray message,jbyteArray nonce,jbyteArray key) {
	jboolean copied[2];
	int      rc = -2;
	int      N  = (*env)->GetArrayLength(env,message);
	u8      *c  = (u8 *) (*env)->GetByteArrayElements(env,ciphertext,&copied[C]);
	u8      *m  = (u8 *) (*env)->GetByteArrayElements(env,message,   &copied[M]);
	u8       n[crypto_stream_salsa20_NONCEBYTES];
	u8       k[crypto_stream_salsa20_KEYBYTES];

	if (m && c) {
	    (*env)->GetByteArrayRegion(env,nonce,0,crypto_stream_salsa20_NONCEBYTES,n);
	    (*env)->GetByteArrayRegion(env,key,  0,crypto_stream_salsa20_KEYBYTES,  k);

		rc = crypto_stream_salsa20_xor(c,m,N,n,k);
	}

	release(env,ciphertext,c,N,rc, copied[C]);
	release(env,message,   m,N,YES,copied[M]);

	memset(n,0,crypto_stream_salsa20_NONCEBYTES);
	memset(k,0,crypto_stream_salsa20_KEYBYTES);

    return (jint) rc;
}

/** jniCryptoSignKeyPair
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSignKeyPair(JNIEnv *env,jobject object,jbyteArray publicKey,jbyteArray secretKey) {
	u8 pk[crypto_sign_PUBLICKEYBYTES];
	u8 sk[crypto_sign_SECRETKEYBYTES];

	int rc = crypto_sign_keypair(pk,sk);

	if (rc == 0) {
		(*env)->SetByteArrayRegion(env,publicKey,0,crypto_sign_PUBLICKEYBYTES,pk);
		(*env)->SetByteArrayRegion(env,secretKey,0,crypto_sign_SECRETKEYBYTES,sk);
	}

	memset(pk,0,crypto_sign_PUBLICKEYBYTES);
	memset(sk,0,crypto_sign_SECRETKEYBYTES);

    return (jint) rc;
}

/** jniCryptoSign
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSign(JNIEnv *env,jobject object,jbyteArray signedm,jbyteArray message,jbyteArray secretKey) {
	jboolean copied[2];
	int      rc    = -2;
	int      N     = (*env)->GetArrayLength(env,message);
	u64      smlen = (*env)->GetArrayLength(env,signedm);
	u8      *m     = (u8 *) (*env)->GetByteArrayElements(env,message,&copied[M]);
	u8      *sm    = (u8 *) (*env)->GetByteArrayElements(env,signedm,&copied[S]);
	u8       sk[crypto_sign_SECRETKEYBYTES];

	if (m && sm) {
		(*env)->GetByteArrayRegion(env,secretKey,0,crypto_sign_SECRETKEYBYTES,sk);

		rc = crypto_sign(sm,&smlen,m,N,sk);
	}

	release(env,signedm,sm,smlen,rc, copied[S]);
	release(env,message,m, N,    YES,copied[M]);

    memset(sk,0,crypto_sign_SECRETKEYBYTES);

    return (jint) rc;
}

/** jniCryptoSignOpen
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoSignOpen(JNIEnv *env,jobject object,jbyteArray message,jbyteArray signedm,jbyteArray key) {
	jboolean copied[2];
	int      rc    = -2;
	int      N     = (*env)->GetArrayLength(env,signedm);
	u64      mlen  = (*env)->GetArrayLength(env,message) - crypto_sign_BYTES;
	u8      *sm    = (u8 *) (*env)->GetByteArrayElements(env,signedm,&copied[S]);
	u8      *m     = (u8 *) (*env)->GetByteArrayElements(env,message,&copied[M]);
	u8       pk[crypto_sign_PUBLICKEYBYTES];

	if (sm && m) {
		(*env)->GetByteArrayRegion(env,key,0,crypto_sign_PUBLICKEYBYTES,pk);

		rc = crypto_sign_open(m,&mlen,sm,N,pk);
	}

	release(env,message,m, mlen,rc, copied[M]);
	release(env,signedm,sm,N,   YES,copied[S]);

	memset(pk,0,crypto_sign_PUBLICKEYBYTES);

    return (jint) rc;
}

/** jniCryptoVerify16
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoVerify16(JNIEnv *env,jobject object,jbyteArray X,jbyteArray Y) {
	u8 x[crypto_verify_16_BYTES];
	u8 y[crypto_verify_16_BYTES];

    (*env)->GetByteArrayRegion(env,X,0,crypto_verify_16_BYTES,x);
    (*env)->GetByteArrayRegion(env,Y,0,crypto_verify_16_BYTES,y);

	int rc = crypto_verify_16(x,y);

	memset(x,0,crypto_verify_16_BYTES);
	memset(y,0,crypto_verify_16_BYTES);

    return (jint) rc;
}

/** jniCryptoVerify32
 *
 */
jint Java_za_co_twyst_tweetnacl_TweetNaCl_jniCryptoVerify32(JNIEnv *env,jobject object,jbyteArray X,jbyteArray Y) {
	u8 x[crypto_verify_32_BYTES];
	u8 y[crypto_verify_32_BYTES];

    (*env)->GetByteArrayRegion(env,X,0,crypto_verify_32_BYTES,x);
    (*env)->GetByteArrayRegion(env,Y,0,crypto_verify_32_BYTES,y);

	int rc = crypto_verify_32(x,y);

	memset(x,0,crypto_verify_32_BYTES);
	memset(y,0,crypto_verify_32_BYTES);

    return (jint) rc;
}
