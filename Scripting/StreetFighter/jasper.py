import random

from player import Player


class Jasper(Player):
    # Initializing condition for Special Attack
    special_cond = 0

    # Calling constructor of abstract class Player

    def __init__(self):
        Player.__init__(self, 280, 40, 40, 60)

    # Function that helps the program to store the information about type of attack by storing in attackLog list

    def log_attack(self, attack):
        self.attackLog.append(attack)

    # Function that randomly defines whether the attack type is punch or kick. In case if attack is kick, then
    # increments the special condition value.

    def attack(self):
        attack = random.randint(0, 1)  # 0 is for punch and 1 is for kick
        if attack == 0:
            self.log_attack("punch")
            self.special_cond = 0
            return self.punch_attack
        self.log_attack("kick")
        self.special_cond += 1
        return self.kick_attack

    # Resetting the stamina for start of the round or game

    def reset_stamina(self):
        self.current_stamina = self.stamina_value

    """
    Checks whether the special condition is 2, if so then appends the attackLog with special attack and returns
    amount of damage dealt by special attack
    """

    def special_attack(self):
        if self.special_cond == 2:
            self.special_cond = 0
            self.log_attack("special")
            return self.special_value
        return 0
