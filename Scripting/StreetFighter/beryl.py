import random

from player import Player


class Beryl(Player):

    # Initializing condition for Special Attack

    special_cond = 0

    # Calling constructor of abstract class Player

    def __init__(self):
        Player.__init__(self, 320, 30, 25, 40)

    # Function that helps the program to store the information about type of attack by storing in attackLog list

    def log_attack(self, attack):
        self.attackLog.append(attack)

    # Function that randomly defines whether the attack type is punch or kick

    def attack(self):
        attack = random.randint(0, 1)  # 0 is for punch and 1 is for kick
        if attack == 0:
            self.log_attack("punch")
            return self.punch_attack
        self.log_attack("kick")
        return self.kick_attack

    # Resetting the stamina for start of the round or game

    def reset_stamina(self):
        self.current_stamina = self.stamina_value

    """
    Checks the last 5 attacks in attack list whether the attacks were Punch or not and if 3 or more attacks are punches,
    then adds special attack to the attackLog and returns the damage of special attack.
    """

    def special_attack(self):
        if len(self.attackLog) >= 5:
            for i in self.attackLog[-5:]:
                if i == "punch":
                    self.special_cond += 1
            if self.special_cond >= 3:
                self.special_cond = 0
                self.log_attack("special")
                return self.special_value
        return 0
