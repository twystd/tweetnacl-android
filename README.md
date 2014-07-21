tweetnacl-android: version 0.0

# TweetNaCl - Android NDK port

*tweetnacl-android* is a port of Dan Bernstein's [TweetNaCl][tweetnacl] "crypto library in a 100 tweets" code to 
the Android NDK.

Status
------
Tidying up .....

Implemented
-----------
0.  randombytes
1.  crypto_box
2.  crypto_box_open
2.  crypto_box_keypair
4.  crypto_box_beforenm
5.  crypto_box_afternm
6.  crypto_box_open_afternm
7.  crypto_core_salsa20
8.  crypto_core_hsalsa20
9.  crypto_hashblocks
10. crypto_hash
11. crypto_onetimeauth
12. crypto_onetimeauth_verify
13. crypto_scalarmult
14. crypto_scalarmult_base
15. crypto_secretbox
16. crypto_secretbox_open
17. crypto_sign
18. crypto_sign_open
19. crypto_sign_keypair
20. crypto_stream
21. crypto_stream_xor
22. crypto_stream_salsa20
23. crypto_stream_salsa20_xor
24. crypto_verify_16
25. crypto_verify_32

Disclaimer
----------
The JNI wrapper has been kept as 'thin' as possible to avoid compromising the careful design
and coding of the original TweetNaCl implementation. However, cryptography being what it is, 
the wrapper may have (entirely inadvertently) introduced non-obvious vulnerabilities. So ....

**USE ENTIRELY AT YOUR OWN RISK !**

References
----------

1. [TweetNaCl (website)][tweetnacl]
2. [TweetNaCl: A crypto library in 100 tweets] [tweetnacl-pdf]
3. [Cryptography in NaCl] [nacl-pdf]
4. [TweetNaCl: How cr.yp.to’s developers got carried away by the carry bit][carrybitbug]
5. [NaCl: Cryptography for the Internet][slides]

[tweetnacl]:     http://tweetnacl.cr.yp.to
[tweetnacl-pdf]: http://tweetnacl.cr.yp.to/tweetnacl-20131229.pdf
[nacl-pdf]:      http://cr.yp.to/highspeed/naclcrypto-20090310.pdf
[carrybitbug]:   http://blog.skylable.com/2014/05/tweetnacl-carrybit-bug
[slides]:        http://cryptojedi.org/peter/data/tenerife-20130121.pdf
