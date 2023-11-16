# Synchronization Router

This Java program simulates a simple network environment with a router that manages connections for various devices using synchronization mechanisms such as Semaphores. The program ensures that a limited number of devices can connect to the router simultaneously, and it tracks their activities in a synchronized manner.

## Table of Contents

- [Overview](#overview)
- [Installation](#installation)
- [Usage](#usage)
- [Sample Output](#sample-output)
- [Contributing](#contributing)
- [License](#license)

## Overview

The program consists of the following key classes:

- **Network Class:** The main class where the user inputs the number of Wi-Fi connections and devices. It creates instances of the `Router` and `Device` classes.

- **Semaphore Class:** Represents a semaphore used for synchronization. It includes methods for decrementing and incrementing the semaphore value in a synchronized manner.

- **Device Class:** Represents a device that connects to the router. It extends the Thread class and includes methods for connecting, performing online activities, and disconnecting. Devices are synchronized using the `Router` and `Semaphore`.

- **Router Class:** Represents the router that manages connections. It includes methods for occupying and releasing connections, as well as a semaphore to control the number of simultaneous connections.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/synchronization_router.git
   ```

2. Navigate to the project directory:

   ```bash
   cd synchronization_router
   ```

3. Compile the Java files:

   ```bash
   javac Classes/*.java
   ```

## Usage

1. Run the program:

   ```bash
   java Classes.Network
   ```

2. Follow the prompts to enter the number of Wi-Fi connections and the number of devices.

3. Enter the details for each device, including its name and type.

4. View the simulated network activity based on the provided synchronization logic.

## Sample Output

An example of the program's output is as follows:

```
(C1) (mobile) arrived
(C4) (pc) arrived
(C3) (pc) arrived and waiting
(C2) (tablet) arrived and waiting

Connection 1: C1 Occupied
Connection 2: C4 Occupied
Connection 1: C1 login
Connection 2: C4 login
Connection 1: C1 Performs online activity
Connection 2: C4 Performs online activity
Connection 1: C1 Logged out
Connection 2: C4 Logged out

Connection 1: C3 Occupied
Connection 1: C3 login
Connection 1: C3 Performs online activity
Connection 1: C3 Logged out

Connection 2: C2 Occupied
Connection 2: C2 login
Connection 2: C2 Performs online activity
Connection 2: C2 Logged out
```
