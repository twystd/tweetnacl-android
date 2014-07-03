tweetnacl-android: version 0.0

# TweetNaCl - Android NDK port

*tweetnacl-android* is a port of Dan Bernstein's [TweetNaCl][tweetnacl] "crypto library in a 100 tweets" code to 
the Android NDK.

Status
------
Just started .....

Implemented
-----------
0. randombytes
1.  crypto_box_keypair
2.  crypto_box
3.  crypto_box_open
4.  crypto_box_beforenm
5.  crypto_box_afternm
6.  crypto_box_open_afternm
7.  crypto_core_hsalsa20
8.  crypto_core_salsa20
9.  crypto_hash
10. crypto_hash_sha512

Pending
-------
11. crypto_hashblocks
12. crypto_onetimeauth
13. crypto_onetimeauth_verify
14. crypto_scalarmult
15. crypto_scalarmult_base
16. crypto_secretbox
17. crypto_secretbox_open
18. crypto_sign
19. crypto_sign_open
20. crypto_sign_keypair
21. crypto_stream
22. crypto_stream_xor
23. crypto_stream_salsa20
24. crypto_stream_salsa20_xor
25. crypto_verify_16
26. crypto_verify_32

References
----------

1. [TweetNaCl][tweetnacl]

[tweetnacl]: http://tweetnacl.cr.yp.to
