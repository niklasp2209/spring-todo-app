const API_URL = "http://localhost:8080/todos";
let todos = [];

async function fetchTodos() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        todos = await response.json();
        displayTodos(todos);
    } catch (error) {
        console.error("Error fetching TODOs:", error);
    }
}

function displayTodos(todosToDisplay) {
    const todoListElement = document.getElementById("todo-list");
    todoListElement.innerHTML = "";

    if (todosToDisplay.length === 0) {
        const li = document.createElement("li");
        li.textContent = "No TODOs available.";
        todoListElement.appendChild(li);
    } else {
        todosToDisplay.forEach((todo, index) => {
            const li = document.createElement("li");
            li.textContent = `${todo.description} - Due: ${todo.dueDate}`;
            li.onclick = () => deleteTodo(index);  // Fix: deleteTodo-Aufruf
            todoListElement.appendChild(li);
        });
    }
}

// Funktion zum Löschen eines TODOs
async function deleteTodo(index) {
    try {
        const response = await fetch(`${API_URL}/${index}`, {
            method: "DELETE"
        });

        if (response.ok) {
            fetchTodos(); // TODO-Liste nach Löschen aktualisieren
        } else {
            console.error("Failed to delete TODO:", response.statusText);
        }
    } catch (error) {
        console.error("Error deleting TODO:", error);
    }
}

function filterTodos() {
    const searchQuery = document.getElementById("search-input").value.toLowerCase();
    const filteredTodos = todos.filter(todo =>
        todo.description.toLowerCase().includes(searchQuery) || todo.dueDate.toLowerCase().includes(searchQuery)
    );
    displayTodos(filteredTodos);
}

document.getElementById("search-input").addEventListener("input", filterTodos);

async function addTodo(event) {
    event.preventDefault();

    const description = document.getElementById("description").value;
    const localDate = document.getElementById("dueDate").value;

    if (!description || !localDate) {
        return;
    }

    const newTodo = {
        description,
        dueDate: localDate
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newTodo),
        });

        if (response.ok) {
            fetchTodos(); // TODO-Liste aktualisieren
            document.getElementById("todo-form").reset();
        }
    } catch (error) {
        console.error("Error adding todo:", error);
    }
}

window.onload = fetchTodos;
document.getElementById("todo-form").addEventListener("submit", addTodo);
