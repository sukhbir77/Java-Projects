I'm hoping/assuming you're using a Linux/Unix-based system (including Mac OS X)
and only bothered to implement basic build and run commands as simple .sh 
scripts rather than using Gradle or Maven.  I would obviously use a real build
and/or dependency management system soon enough into the lifetime of any code
written (and I happen to think Gradle is pretty cool so would push for that
when and where possible).  

I didn't set up Gradle here since the pain of using .sh files right now for 
one developer and a small audience (whomever is reading and running this)
is non-existent.  As a result, the junit and hamcrest JARs are stored in the
git repository, which is weird, but acceptable for right now.  (If this project
was going to be used in the long run, that would maybe not be a great idea
since those JARs would be stored in the git history forever, even after we 
deleted them.)   

1. Compile the code with ./compile.sh.

2. Run the code with ./run.sh.  This runs a game of war with default 
   parameters.  (4 suits, 13 ranks, 2 players)  Feel free to run with
   other params like './run.sh 5 10 3' which would do 5 suits, 10 
   rank values, and 3 players.  

   Each round's output starts with a line starting with the round number and 
   then a list of each player's number of cards in their stash.  

   Then each flip within that round starts with an asterix and either 
   'current sub-round' with a list of all cards thrown down, or 
   'face down sub-round' for a sub-round of cards dealt face down before
   the next tie-breaker sub-round.  

3. Run the tests with ./runTests.sh, which runs a handful of functionality
   tests with JUnit 4.  No mocking library was used and not 100% code
   coverage is acheived, but the unit tests written were significant enough
   to give me a decent level of confidence to make non-trivial changes, which
   means the tests were 'good enough' for my purposes for this project.  
