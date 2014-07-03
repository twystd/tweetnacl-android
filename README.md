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
1. crypto_box_keypair
2. crypto_box
3. crypto_box_open
4. crypto_box_beforenm
5. crypto_box_afternm
6. crypto_box_open_afternm
7. crypto_core_hsalsa20
8. crypto_core_salsa20

Pending
-------
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

References
----------

1. [TweetNaCl][tweetnacl]

[tweetnacl]: http://tweetnacl.cr.yp.to
