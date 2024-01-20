# Online Shopping System

This Java application is a console-based online shopping system developed by Alibek Seitov and Kandyshev Abay from Astana IT University, MCS-2301 group. The system allows users to manage products and users, make purchases, return products, and view order histories.

## Features

- Add, list, and manage products
- Add users and manage their details
- Purchase products
- Return purchased products
- View all products
- View all users and their order history

## Classes

- `Product`: Represents a product with attributes like name, price, quantity, and description.
- `User`: Represents a user with attributes like ID, name, balance, and a list of orders.
- `Order`: Represents an order with attributes like user ID, product name, quantity, and total sum.
- `OnlineShop`: The main class that manages users, products, and order history.

## Setting Up the Project

### Using IntelliJ IDEA

1. **Clone the Repository**: First, clone the repository to your local machine.

    ```
    git clone https://github.com/effuone/online-shop-system-java
    ```

2. **Open the Project**: Open IntelliJ IDEA, and select `File > Open` from the menu. Navigate to the directory where you cloned the repository and open it.

3. **Build the Project**: IntelliJ IDEA should automatically handle dependencies and build configurations. Wait for the IDE to complete the indexing and building process.

4. **Run the Application**: Navigate to the `Main.java` file, right-click on it, and select `Run 'Main.main()'` to start the application.

### Using the Terminal

1. **Clone the Repository**: Clone the repository to your local machine using:

    ```
    git clone https://github.com/effuone/online-shop-system-java
    ```

2. **Navigate to the Source Directory**: Change to the directory containing the source files.

    ```bash
    cd path/to/project/src
    ```

3. **Compile the Java Files**: Compile the Java files using `javac`.

    ```bash
    javac OnlineShop.java User.java Product.java Order.java Main.java
    ```

4. **Run the Application**: After compiling, run the program with:

    ```bash
    java Main
    ```

## Usage

After starting the application, you will be presented with a menu of options. Use the numbers to navigate through the menu and perform actions like adding users, listing products, making purchases, and more. Follow the on-screen prompts for each action.

