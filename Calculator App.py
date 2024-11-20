import tkinter as tk
from tkinter import messagebox

# Function to update expression in the entry field
def press(key):
    entry_text.set(entry_text.get() + str(key))

# Function to evaluate the final expression
def calculate():
    try:
        result = eval(entry_text.get())
        entry_text.set(result)
    except Exception as e:
        messagebox.showerror("Error", "Invalid Expression")
        entry_text.set("")

# Function to clear the entry field
def clear():
    entry_text.set("")

# Initialize main application window
root = tk.Tk()
root.title("Calculator")
root.geometry("300x400")
root.resizable(False, False)

# Entry field to display the expression
entry_text = tk.StringVar()
entry = tk.Entry(root, textvariable=entry_text, font=("Arial", 20), bd=10, relief=tk.RIDGE, justify="right")
entry.grid(row=0, column=0, columnspan=4, ipadx=8, ipady=8)

# Button layout
buttons = [
    ('7', 1, 0), ('8', 1, 1), ('9', 1, 2), ('/', 1, 3),
    ('4', 2, 0), ('5', 2, 1), ('6', 2, 2), ('*', 2, 3),
    ('1', 3, 0), ('2', 3, 1), ('3', 3, 2), ('-', 3, 3),
    ('C', 4, 0), ('0', 4, 1), ('=', 4, 2), ('+', 4, 3)
]

# Create buttons dynamically
for (text, row, col) in buttons:
    if text == "=":
        btn = tk.Button(root, text=text, font=("Arial", 18), bg="#4caf50", fg="white", command=calculate)
    elif text == "C":
        btn = tk.Button(root, text=text, font=("Arial", 18), bg="#f44336", fg="white", command=clear)
    else:
        btn = tk.Button(root, text=text, font=("Arial", 18), command=lambda t=text: press(t))
    btn.grid(row=row, column=col, ipadx=10, ipady=10, padx=5, pady=5, sticky="nsew")

# Run the application
root.mainloop()
