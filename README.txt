All code is in the /src directory

1. Compile from the src directory
javac -cp JavaPlot.jar algorithms/*.java server/*.java Client.java

2. Run from the src directory
java -cp JavaPlot.jar:. Client n a -f
n - integer greater than 1
a - integer greater than 0
f - type of id assignment. 'r' for random, 'a' for clockwise ascending, 'd' for counter clockwise ascending.
Program reads only one letter for f.
