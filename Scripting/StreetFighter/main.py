from game import Game

"""
Welcome screen
"""
def create_game():
    print("Choose your characters!\n")
    print(" @ Name\tStamina\tPunch\tKick\tSpecial\t Condition:\n")
    print("1. Agate\t300\t 30\t\t35\t\t50\t\tAfter each four attacks\n")
    print("2. Jasper\t280\t 40\t\t40\t\t60\t\tAfter each two consecutive kick attacks\n")
    print("3. Beryl\t320\t 30\t\t25\t\t40\t\tWhen three of the last five attacks are punch attacks\n")

    character1 = int(input("Select 1st character(enter the number 1-3): "))
    character2 = int(input("Select 2st character(enter the number 1-3): "))
    """
    Calling Game class with constructor
    """
    game = Game(character1, character2)
    game_cond = 0
    """
    Game maximum can last 3 rounds. game_finished function checks whether any character wins 2 rounds and returns 1 or 2
    dependent on which character won (0 returned in case no one has won yet). 
    """
    while game.current_round <= 3:
        game_cond = game.game_finished()
        if game_cond == 0:
            game.play_round()
        else:
            break
    """
    function for displaying winner
    """
    game.show_game_winner(game_cond)


# main menu of the game
while True:
    print("Welcome to Street Fighter Game\nMenu:\n1. Create game\n2. Exit game\n")
    """
    Gets input for option from user
    """
    op = int(input("Option: "))
    if op == 1:
        create_game()
    elif op == 2:
        break
    else:
        print("Error. Please choose between 1 or 2!")
