import datetime
import socket
import threading

threadLock = threading.RLock()


class ClientThread(threading.Thread):  # custom class extending the Thread class
    def __init__(self, clientAddr, clientsock):  # constructor for ClientThread
        threading.Thread.__init__(self)  # calling base class constructor Thread class
        self.csocket = clientsock  # setting socket
        self.caddr = clientAddr  # setting address
        print("New connection from {}".format(self.caddr))  # displaying the address of the client in the console

    def run(self):  # running client thread
        msg = "Connection Established".encode()  # message for client
        self.csocket.send(msg)  # send message
        threadLock.acquire()  # defining the borders of critical section
        while True:  # continue listening (receiving packages)
            self.data = self.csocket.recv(1024).decode()  # receiving package from client

            if self.data == 'bye':  # if client terminates the program
                self.csocket.send("bye".encode())
                break  # break the while True loop
            myData = self.data.split(";")
            print(myData)
            customerNo = myData[0].split(" ")[1]
            meal = myData[1].split(" ")[2]
            cakes = myData[2].split(":")[1]

            print(cakes)
            price = 0
            if meal == "Burger(25TL)":
                print("burger")
                price = price + 25
            else:
                print("pizza")
                price = price + 30
            if cakes == " Chocolate Cake":
                print("choco")
                price = price + 20
            elif cakes == " Chocolate Cake,Strawberry Cake":
                print("both")
                price = price + 38
            elif cakes == " Strawberry Cake":
                print("straw")
                price = price + 18
            now = datetime.datetime.now()  # stores the date, when the button was pressed
            file = open("orders.txt", "r")  # open users.txt for reading
            count = 0
            for x in file:  # for each line in users.txt
                xList = x.split(";")  # split users.txt based on the ';' char and stores string in list
                if xList[0].split(" ")[1] == customerNo:
                    count = count + 1
            file.close()  # close the users.txt file
            if count > 10:
                price = price * 90 / 100
            newData = self.data + ";Price: " + str(price) + ";" + str(now.day) + "/" + str(now.month) + "/" + str(
                now.year) + " " + str(now.hour) + ":" + str(now.second)
            file = open("orders.txt", "a")  # open a sales.txt for appending
            file.write(newData+"\n")  # append data from the client to the sales.txt file as a new line
            file.close()  # close the sales.txt
            self.csocket.send(str(price).encode())  # send the report to the client.

        threadLock.release()
        print("Client is disconnected")
        self.csocket.close()  # close the server side socket


HOST = "127.0.0.1"  # localhost
PORT = 5000

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # TCP
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)  # setting server to support multithreading

server.bind((HOST, PORT))  # binding server to the address and port provided above

print("Server is started")
print("Waiting for client requests")

while True:  # Main loop to run the server and accept client packages.
    server.listen()  # listener for client
    clientsock, clientAddr = server.accept()  # accepting packages from client
    newthread = ClientThread(clientAddr, clientsock)  # creating new thread for client
    newthread.start()  # running the newly created thread
