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

3. To build the 'TweetNaCl - Measure' project:
   - update the Android section of the project properties so that the android-support-v7-appcompat
     library project references the Android SDK extras/android/support/v7/appcompat project (or a copy
     thereof)
   - for some (infinitely bizarre) reason you will probably have to update the v4 support library in both
     projects 
   - for the same (infinitely bizarre) reason you will quite probably have to restart Eclipse/ADT after
     updating the v4 support library to get the project to build cleanly

4. The 'TweetNaCl - Test' project manifest references the 'TweetNacl - Measure' project and should build 
   correctly once the 'TweetNaCl - Measure' project builds cleanly.

Disclaimer
----------
The JNI wrapper has been kept as 'thin' as possible to avoid compromising the careful design
and coding of the original TweetNaCl implementation. However, cryptography being what it is, 
the wrapper may have (entirely inadvertently) introduced non-obvious vulnerabilities. So ....

**USE ENTIRELY AT YOUR OWN RISK !**

Notes
-----

References
----------

1. [TweetNaCl (website)][tweetnacl]
2. [TweetNaCl: A crypto library in 100 tweets] [tweetnacl-pdf]
3. [Cryptography in NaCl] [nacl-pdf]
4. [TweetNaCl: How cr.yp.to’s developers got carried away by the carry bit][carrybitbug]
5. [NaCl: Cryptography for the Internet][slides]
6. [On NaCl: Undefined Behaviour][ciawof]
7. [Safe, Efficient, and Portable Rotate in C/C++][regehr]

[tweetnacl]:     http://tweetnacl.cr.yp.to
[tweetnacl-pdf]: http://tweetnacl.cr.yp.to/tweetnacl-20131229.pdf
[nacl-pdf]:      http://cr.yp.to/highspeed/naclcrypto-20090310.pdf
[carrybitbug]:   http://blog.skylable.com/2014/05/tweetnacl-carrybit-bug
[slides]:        http://cryptojedi.org/peter/data/tenerife-20130121.pdf
[ciawof]:        http://coderinaworldofcode.blogspot.com/2014/03/on-nacl.html
[regehr]:        http://blog.regehr.org/archives/1063
