from agate import Agate
from beryl import Beryl
from jasper import Jasper
import random


class Game:
    """
    Constructor for Game class containing of 2 players
    """

    def __init__(self, player1, player2):
        if player1 == 1:
            self.character1 = Agate()
        elif player1 == 2:
            self.character1 = Jasper()
        else:
            self.character1 = Beryl()

        if player2 == 1:
            self.character2 = Agate()
        elif player2 == 2:
            self.character2 = Jasper()
        else:
            self.character2 = Beryl()

        self.current_round = 0
        self.round_winners = []

    def play_round(self):

        # Function for running 1 round. At the start of every round, resets staminas of characters

        self.character1.reset_stamina()
        self.character2.reset_stamina()

        # Clears the attack log of each player at the begging of each round

        self.character1.attackLog = []
        self.character2.attackLog = []
        # For Game class increments the number of rounds.
        self.current_round += 1
        """
        Characters are exchanging staminas : Attack amount of 1st character is subtracted of 2nd character's stamina and
        the similar process is done towards 1st character
        """

        while self.character1.current_stamina > 0 and self.character2.current_stamina > 0:
            if random.randint(0, 1):
                self.character2.current_stamina -= self.character1.attack()
                self.character2.current_stamina -= self.character1.special_attack()
                # Checks whether the stamina of 2nd character is less than 0 in order to not run redundant lines of code
                if self.character2.current_stamina <= 0:
                    break
                self.character1.current_stamina -= self.character2.attack()
                self.character1.current_stamina -= self.character2.special_attack()
            else:
                self.character1.current_stamina -= self.character2.attack()
                self.character1.current_stamina -= self.character2.special_attack()
                # Checks whether the stamina of 1st character is less than 0 in order to not run redundant lines of code
                if self.character1.current_stamina <= 0:
                    break
                self.character2.current_stamina -= self.character1.attack()
                self.character2.current_stamina -= self.character1.special_attack()
        # Based on whose stamina has diminished first, game decides on winner
        if self.character1.current_stamina <= 0:
            winner = 2
            self.round_winners.append("Character 2")
        else:
            winner = 1
            self.round_winners.append("Character 1")

        # Statistics

        print("\n" + self.round_winners[-1] + " has won the ROUND " + str(self.current_round) + " and stats are: ")

        # Based on winner variable displays information about the winner character

        if winner == 2:
            print("Remaining stamina of the winner: " + str(self.character2.current_stamina))
        else:
            print("Remaining stamina of the winner: " + str(self.character1.current_stamina))
        print("\t\t\t\tCharacter 1\tCharacter 2\t")
        print("Punch Attacks\t" + str(self.character1.attackLog.count("punch")) + "\t\t\t" + str(
            self.character2.attackLog.count("punch")))
        print("Kick Attacks\t" + str(self.character1.attackLog.count("kick")) + "\t\t\t" + str(
            self.character2.attackLog.count("kick")))
        print("Special Attacks\t" + str(self.character1.attackLog.count("special")) + "\t\t\t" + str(
            self.character2.attackLog.count("special")))

    # When the game is finished resets char1 and char2 for next game

    def game_finished(self):
        # When the game is finished resets char1 and char2 for next game
        char1 = 0
        char2 = 0

        # Counts number of times the characters has won and stores appropriate statistics

        for i in self.round_winners:
            if i == "Character 2":
                char2 += 1
            else:
                char1 += 1
        if char2 >= 2:
            return 2
        elif char1 >= 2:
            return 1
        return 0

    # Based on cond variable retrieved from return statement of game_finished function, displays the winner of the game

    def show_game_winner(self, cond):
        if cond == 2:
            print("\nCharacter 2 has won the game!")
        else:
            print("\nCharacter 1 has won the game!")
