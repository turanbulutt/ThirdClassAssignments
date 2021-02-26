from tkinter import *
from tkinter import messagebox
import socket
import datetime


class CafeScreen(Frame):
    def __init__(self):  # constructor for LoginScreen class
        Frame.__init__(self)  # calling the constructor of base class
        self.pack()  # packing frame

        self.master.title("Hello World Cafe!")  # naming the title of the window

        self.frame1 = Frame(self)  # creating frame object for customer no
        self.frame1.pack(padx=5, pady=5)  # giving the padding for the frame

        self.customerNoLabel = Label(self.frame1, text="Customer No: ")  # creating label for customer no
        self.customerNoLabel.pack(side=LEFT, padx=10, pady=5)  # giving the padding and alignment

        self.customerNo = Entry(self.frame1, name="customerNo")  # creating a text box for customer no
        self.customerNo.pack(side=LEFT, padx=5, pady=5)  # creating label for customer no

        self.frame2 = Frame(self)
        self.frame2.pack(padx=5, pady=5)

        self.frame2.label = Label(self.frame2, text="Meals:")  # creating Coffee label
        self.frame2.label.pack(side=TOP, padx=5, pady=5)  # giving the padding and alignment

        self.mealTypes = ["Burger(25TL)", "Pizza(30TL)"]
        self.meal = StringVar()  # string representation of selected report
        self.meal.set(self.mealTypes[0])  # setting the initial value of the report type

        # iterates through all report types in the array
        for mealType in self.mealTypes:
            self.frame2.mealSelection = Radiobutton(self.frame2, text=mealType, value=mealType,
                                                    # creating a button for each meal type
                                                    variable=self.meal)
            self.frame2.mealSelection.pack(side=LEFT, padx=5, pady=5)  # packing the button

        self.frame3 = Frame(self)
        self.frame3.pack(padx=5, pady=5)

        self.chocolate = BooleanVar()
        self.strawberry = BooleanVar()

        self.chocolateCheck = Checkbutton(self.frame3, text="Chocolate Cake(20TL)", variable=self.chocolate)
        self.chocolateCheck.pack(side=LEFT, padx=5, pady=5)

        self.strawberryCheck = Checkbutton(self.frame3, text="Strawberry Cake(18TL)", variable=self.strawberry)
        self.strawberryCheck.pack(side=LEFT, padx=5, pady=5)

        self.buttons = Frame()  # creating a frame for a button
        self.buttons.pack(side=TOP, padx=5, pady=5)  # packing the button
        self.buttons.item = Button(self.buttons, text="Order",
                                   command=self.ButtonPressed)  # putting the button as a first element to the frame
        self.buttons.item.pack(side=LEFT, padx=5, pady=5)  # packing the first element of the frame (button)

        self.buttons.item = Button(self.buttons, text="Close",
                                   command=self.CloseButton)  # putting the second button to the frame
        self.buttons.item.pack(side=LEFT, padx=5, pady=5)  # pack the second button

    def ButtonPressed(self):  # actions done when button is pressed
        data = "Customer: " + self.customerNo.get() + "; Meal: " + self.meal.get() + ";Cakes: "
        if self.chocolate.get():
            data = data + "Chocolate Cake"
            if self.strawberry.get():
                data = data + ",Strawberry Cake"
        elif self.strawberry.get():
            data = data + "Strawberry Cake"
        print(data)
        client.send(data.encode())
        answer = client.recv(1024).decode()  # receiving the msg from the server
        messagebox.showinfo("Confirmation",
                            "Order is received!\nPrice: "+answer)  # showing the msg received from the server

    def CloseButton(self):  # fucntion called when close button is called
        client.send("bye".encode())  # send the "bye msg to the server, when close button is pressed
        self.quit()  # closes itself


SERVER = "127.0.0.1"  # localhost
PORT = 5000  # port

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # TCP
client.connect((SERVER, PORT))  # binding server to the address and port provided above
while True:
    in_data = client.recv(1024).decode()
    print("From server: {}".format(in_data))
    if in_data:
        print("Connection established!\n")
    window = CafeScreen()
    window.mainloop()
    # client.send(out_data.encode())
    if in_data == "bye":
        break

client.close()
