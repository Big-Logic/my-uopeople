# Chat Application

This project implements a simple multi-client chat application in Java.
It consists of a server, a client, and a client handler class.

## Project Structure

- `ChatServer.java`
  - Starts a TCP server on port `5000`.
  - Accepts incoming client connections.
  - Creates a `ClientHandler` for each connected client.
  - Broadcasts messages to all connected clients.
  - Tracks connected clients using a thread-safe `ConcurrentHashMap`-backed set.

- `ClientHandler.java`
  - Handles communication for a single connected client.
  - Reads messages from that client and broadcasts them to others.
  - Sends server broadcasts to the client.
  - Handles client join and leave events.

- `ChatClient.java`
  - Connects to the chat server at `localhost:5000`.
  - Sends keyboard input to the server.
  - Receives and prints broadcast messages from the server.
  - Exits when the user types `exit`.

## How it works

1. Run `ChatServer` first.
   - The server listens on port `5000`.
   - Each new connection is assigned a unique `User-N` identifier.
   - When a client sends a message, the server broadcasts it to all active clients.

2. Run one or more instances of `ChatClient`.
   - Each client connects to the server and starts a listener thread.
   - The client prints any messages received from the server.
   - User input is sent immediately to the server.

3. The `ClientHandler` handles each client session.
   - It broadcasts a join message when the client connects.
   - It broadcasts `User-N: message` for every message received.
   - It broadcasts a leave message when the client disconnects.

## Requirements

- Java Development Kit (JDK) installed.
- `javac` and `java` available on the command line.

## Compile

From the project directory:

```bash
javac *.java
```

If you have a JDK installed at a custom location, use the full path:

```bash
/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home/bin/javac *.java
```

## Run

Start the server:

```bash
java ChatServer
```

Then start one or more clients from the same directory:

```bash
java ChatClient
```

If needed, use the full Java path:

```bash
/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home/bin/java ChatServer
/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home/bin/java ChatClient
```

## Notes

- The server uses port `5000`; make sure it is free.
- Type `exit` in the client to disconnect.
- In some sandboxed editor terminals, local networking may need permission to bind/connect.
