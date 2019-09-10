#!/bin/bash
echo "=======================AliceBob Protocol============================"
echo
java -jar mungo.jar demos/alice_bob/nothreads/Protocol.java
echo
echo "==========================AliceRunner============================"
echo
java -jar mungo.jar demos/alice_bob/threads/AliceRunner.java
echo
echo "==========================BobRunner============================"
echo
java -jar mungo.jar demos/alice_bob/threads/BobRunner.java
echo
# echo "=========================Broker============================="
# echo
# java -jar mungo.jar demos/alice_bob/threads/Broker.java
# echo
echo "========================BuyerSeller=============================="
echo
java -jar mungo.jar demos/buyer_seller/Main.java
echo
echo "==========================Collection Stack============================"
echo
java -jar mungo.jar demos/collection/Stack.java
echo
echo "=========================file_example============================="
echo
java -jar mungo.jar demos/file_example/File.java
echo
echo "==========================File inheritance============================"
echo
java -jar mungo.jar demos/inheritance/Main.java
echo
echo "==========================StateIterator============================"
echo
java -jar mungo.jar demos/iterator/Main.java
echo
echo "==========================LoopImpl============================"
echo
java -jar mungo.jar demos/loop/ClientTest1.java
echo
echo "==========================LoopImpl============================"
echo
java -jar mungo.jar demos/loop/ClientTest3.java
echo
echo "==========================LoopImpl============================"
echo
java -jar mungo.jar demos/loop/ClientTest4.java
echo
echo "==========================SMTP CMain============================"
echo
java -jar mungo.jar demos/SMTP/CMain.java
echo
echo "============================Traversal Run=========================="
echo
java -jar mungo.jar demos/Traversal/Run.java
