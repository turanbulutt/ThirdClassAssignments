class Player:

    # Constructor for abstract Player class

    def __init__(self, stamina_value=0, punch_attack=0, kick_attack=0, special_value=0):
        self.stamina_value = stamina_value
        self.current_stamina = stamina_value
        self.punch_attack = punch_attack
        self.kick_attack = kick_attack
        self.special_value = special_value
        self.attackLog = []

    # Defining the names of functions, that has to be implemented.

    def attack(self): pass

    def log_attack(self, attack): pass

    def reset_stamina(self): pass
