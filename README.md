# OOP-Game-S16 
Objected Oriented Programming Spring 2016, Iteration 3.

**Team Kanye Kats**
- [Sergio Puleri](https://github.com/spuleri)
- [Bradley Treuherz](https://github.com/bmtreuherz)
- [Aidan Kelliher](https://github.com/dnkllhr)
- [John Kaufmann](https://github.com/kaufmann42)
- [Josh Nassar](https://github.com/JoshNassar94)
- [Rokas Leskevicius](https://github.com/Leskevicius)


# How to run

## IDE ([IntelliJ](https://www.jetbrains.com/idea/) is preferred)
-  Clone the repository if you do not have the source code, with:
```bash
git clone https://github.com/KanyeKats/Iteration3.git
```
- In [IntelliJ](https://www.jetbrains.com/idea/) `File > New Project From Exisiting Sources...`
- Or at splash screen select `New Project From Exisiting Sources...`
- Select root directory


## Source
- Clone the repository if you do not have the source code, with:
```bash
git clone https://github.com/KanyeKats/Iteration3.git
```
- Run the following command at the root directory to compile and run the game:   
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin && javac -d bin @sources.txt && cp -R src bin/ && cp -R res bin/ && cp -R aiden_res bin/ && cd bin && java RunGame && cd .. && rm -rf bin
```
- To just compile the game run:
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin && javac -d bin @sources.txt && cp -R src bin/ && cp -R res bin/ && cp -R aiden_res bin/
```
- Then, to run the game, run:
```bash
cd bin && java RunGame && cd ..
```
