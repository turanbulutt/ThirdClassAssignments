import random

from player import Player


class Agate(Player):
    # Initializing condition for Special Attack
    special_cond = 0

    # Calling constructor of abstract class Player

    def __init__(self):
        Player.__init__(self, 300, 30, 35, 50)

    # Function that helps the program to store the information about type of attack by storing in attackLog list

    def log_attack(self, attack):
        self.attackLog.append(attack)

    # Function that randomly defines whether the attack type is punch or kick. During each attack, increments the
    # attack condition by 1

    def attack(self):
        attack = random.randint(0, 1)  # 0 is for punch and 1 is for kick
        self.special_cond += 1
        if attack == 0:
            self.log_attack("punch")
            return self.punch_attack
        self.log_attack("kick")
        return self.kick_attack

    # Resetting the stamina for start of the round or game

    def reset_stamina(self):
        self.current_stamina = self.stamina_value

    """
    After each 4 attacks appends attackLog list with special attack and returns the amount of damage done by special attack
    """

    def special_attack(self):
        if self.special_cond == 4:
            self.special_cond = 0
            self.log_attack("special")
            return self.special_value
        return 0
