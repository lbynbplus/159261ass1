# 159261ass1
##Snake Game

###Game Functionality
####Main page: includes buttons to start the game, seek help, and exit.
####Help page: provides game rules and instructions.
####Start game: click the "start game" button to begin playing.
####Scoring system: eating apples increases the length of the snake and scores 10 points per 
####apple.
####Game over: the game ends when the snake hits the boundary or touches its own body.
####Space key functionality: after the game is over, pressing the space key returns to the main 
####page while the game is still running.

###Running the Game
####This game uses Java Swing as the GUI library and Java's event handling mechanism to
####implement the game logic. I made some modifications to the GameEngine class to avoid
####concurrency issues. This is because the main page may cause mPanel to be null in the
####createButtons method, which may be caused by concurrency issues. This is because the
####SwingUtilities.invokeLater method is executed asynchronously in the event dispatch thread
####and may not be executed before the setupWindow method is completed in the 
####createButtons method.

###Author
####Boyu Lyu 
###Git
####https://github.com/lbynbplus/159261ass1.git
