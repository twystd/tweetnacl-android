tweetnacl-android: version 0.0

# TweetNaCl - Android NDK port

*tweetnacl-android* is a port of Dan Bernstein's [TweetNaCl][tweetnacl] "crypto library in a 100 tweets" code to 
the Android NDK.

Status
------
Tidying up .....

Version Info
------------
tweetnacl: 20140427

Usage
-----
1. The simplest way to use the tweetnacl-android library is to clone the project into your workspace and
   reference the TweetNaCl project in the native-lib directory as an Android library project.

2. Alternatively, copy the libs and src directories to your own project.

Building (Eclipse/ADT)
----------------------
1. To rebuild the native library:
   - create an entry for ndk.dir in the local.properties file in the native-lib project that points to your
     installation of the Android NDK
   - execute the 'clean' and 'build' targets in the build.xml Ant script

2. Alternatively invoke the Android NDK ndk-build script from the jni directory in the native-lib project

3. To build the 'benchmark' project:
   - update the Android section of the project properties so that the android-support-v7-appcompat
     library project references the Android SDK extras/android/support/v7/appcompat project (or a copy
     thereof)
   - for some (infinitely bizarre) reason you will probably have to update the v4 support library in both
     projects 
   - for the same (infinitely bizarre) reason you will quite probably have to restart Eclipse/ADT after
     updating the v4 support library to get the project to build cleanly

4. The 'test' project manifest references the 'benchmark' project and should build correctly once the 'benchmark'
   project builds cleanly.

Disclaimer
----------
The JNI wrapper has been kept as 'thin' as possible to avoid compromising the careful design
and coding of the original TweetNaCl implementation. However, cryptography being what it is, 
the wrapper may have (entirely inadvertently) introduced non-obvious vulnerabilities. So ....

**USE ENTIRELY AT YOUR OWN RISK !**

Notes
-----
1. There is a barely measureable (5%) but seemingly consistent performance improvement using 
   GetByteArrayElements rather than GetByteArrayRegion.

References
----------

1. [TweetNaCl][tweetnacl]
2. [TweetNaCl: A crypto library in 100 tweets] [tweetnacl-pdf]
3. [Cryptography in NaCl] [nacl-pdf]
4. [TweetNaCl: How cr.yp.to’s developers got carried away by the carry bit][carrybitbug]
5. [NaCl: Cryptography for the Internet][slides]
6. [On NaCl: Undefined Behaviour][ciawof]
7. [Safe, Efficient, and Portable Rotate in C/C++][regehr]
8. [StackOveflow:Is there a replacement for /dev/random on Android JNI][stackoverflow]
9. [Issue 42265:Android empties the entropy pool, resulting in blocking, user perceived lag/poor performance][issue]	
10.[Android Developer's Blog:Some SecureRandom Thoughts][android]


[tweetnacl]:     http://tweetnacl.cr.yp.to
[tweetnacl-pdf]: http://tweetnacl.cr.yp.to/tweetnacl-20131229.pdf
[nacl-pdf]:      http://cr.yp.to/highspeed/naclcrypto-20090310.pdf
[carrybitbug]:   http://blog.skylable.com/2014/05/tweetnacl-carrybit-bug
[slides]:        http://cryptojedi.org/peter/data/tenerife-20130121.pdf
[ciawof]:        http://coderinaworldofcode.blogspot.com/2014/03/on-nacl.html
[regehr]:        http://blog.regehr.org/archives/1063
[stackoverflow]: https://stackoverflow.com/questions/13055491/is-there-a-replacement-for-dev-random-on-android-jni
[issue]:         https://code.google.com/p/android/issues/detail?id=42265
[android]:       http://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html
