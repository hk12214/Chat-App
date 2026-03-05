# Java Socket Chat Application

## Overview

This project is a simple desktop chat application built using **Java Sockets** and **Swing GUI**.
It demonstrates how two programs (a server and a client) can communicate over a network using **TCP socket programming**.

The application allows real-time messaging between a server and a client on the same machine or across a network.

This project was built to practice **network programming, multithreading, and GUI development in Java**.

---

## Features

* Real-time messaging between server and client
* Graphical user interface built with **Java Swing**
* Uses **TCP sockets** for communication
* Multi-threaded message listening to keep the UI responsive
* Simple and easy-to-understand architecture

---

## Technologies Used

* Java
* Java Swing (GUI)
* Java Socket Programming
* Multithreading

---

## Project Structure

```
ChatappGUI
│
├── ChatServer.java
└── ChatClient.java
```

**ChatServer.java**
Creates a server that listens for incoming client connections and allows the server user to send and receive messages.

**ChatClient.java**
Connects to the server and allows the client user to exchange messages with the server.

---

## How It Works

1. The server starts and waits for a client connection on **port 5000**.
2. The client connects to the server using **localhost:5000**.
3. Once connected, both sides can send and receive messages in real time.
4. Separate threads listen for incoming messages so the interface remains responsive.

---

## How to Run the Project

### 1. Compile the files

```
javac ChatServer.java
javac ChatClient.java
```

### 2. Start the Server

```
java ChatServer
```

The server window will open and wait for a client connection.

### 3. Start the Client

```
java ChatClient
```

The client will connect to the server and the chat session will begin.

---

## Possible Improvements

* Support multiple clients
* Add usernames
* Improve UI design
* Add message timestamps
* Enable communication over different machines in a network

---

## Learning Outcomes

Through this project, I practiced:

* Java socket programming
* Client-server architecture
* Multithreading in Java
* Building desktop GUIs using Swing
* Network communication concepts

