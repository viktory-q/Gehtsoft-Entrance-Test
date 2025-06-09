
# Gehtsoft Training Program Entrance Test Solution 

## Project Description
This Java console application is designed to fulfill the core functionalities required for the **Gehtsoft Training Program** admission. It provides solutions for two main challenges:

1. **Caesar Cipher**: A classic encryption algorithm with support for both English and Russian alphabets.
2. **Arithmetic Expression Evaluator**: A tool to evaluate arithmetic expressions, supporting basic operations and parentheses.

---

## Features 

### Caesar Cipher 
- **Supports both English (26 letters) and Russian (33 letters) alphabets** 🇬🇧🇷🇺
- **Preserves letter case and non-alphabetic characters** (e.g., spaces, punctuation)
- **Handles positive/negative shift values** with proper wrap-around (encryption and decryption)
- **File input/output functionality** for reading/writing encrypted/decrypted text
- **Encryption & Decryption** support with variable shift values

### Arithmetic Expression Evaluator 
- **Supports basic operations**: `+`, `-`, `*`, `/`
- **Handles nested parentheses** for complex expressions
- **Follows PEMDAS/BODMAS** order of operations (Parentheses, Exponents, Multiplication, Division, Addition, Subtraction)
- **Supports decimal and negative numbers**
- **Input validation and error handling** to ensure proper calculation

---

## Requirements 
- **Java 8** or higher

---

## Installation & Execution 

### 1. Clone the repository:
```bash
git clone https://github.com/viktory-q/Gehtsoft-Entrance-Test
```

### 2. Compile the source files:
```bash
javac *.java
```

### 3. Run the application:
```bash
java Main
```

---

## Usage Examples 

### Caesar Cipher Encryption 
```text
Enter your choice: 1
Enter text to encrypt: Hello World
Enter shift value: 3
Result: Khoor Zruog
```

### Arithmetic Expression Evaluation 
```text
Enter your choice: 3
Enter arithmetic expression: (10 + 5) / 3
Result: 5.0
```

---

## Project Structure 
- `Main.java`: Entry point with menu system
- `CaesarCipher.java`: Implements the Caesar cipher encryption and decryption logic
- `ArithmeticEvaluator.java`: Handles the parsing and evaluation of arithmetic expressions
- `FileUtils.java`: Manages file input/output operations for text encryption/decryption


