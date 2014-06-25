### TODO

1. JNI: zero out arrays and only populate if rc == 0
2. Change to u8 etc instead of unsigned char
3. Remove the pre-padding in ciphertext and message (?)
4. Hmmm - check keypair implemtation: slides say it should be pk = crypto_box_keypair(&sk)
   (Ref. http://cryptojedi.org/peter/data/tenerife-20130121.pdf)
5. Revert license to public domain (github 
6. Add disclaimer for side-channel attacks etc