const API_URL = "http://localhost:8080/todos";
let todos = [];

/**
 * Fetches the list of todos from the API and displays them.
 * Handles errors if fetching fails.
 */
async function loadTodos() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error("Failed to fetch todos: " + response.statusText);
        }
        todos = await response.json();
        renderTodos(todos);
    } catch (error) {
        console.error("Error fetching TODOs:", error);
        showError("Could not load todos. Please try again later.");
    }
}

/**
 * Renders the list of todos to the page.
 * If no todos are available, a message will be displayed.
 * @param {Array} todosToDisplay - The list of todos to display
 */
function renderTodos(todosToDisplay) {
    const todoListElement = document.getElementById("todo-list");
    todoListElement.innerHTML = "";

    if (todosToDisplay.length === 0) {
        const li = document.createElement("li");
        li.textContent = "No TODOs available.";
        todoListElement.appendChild(li);
    } else {
        todosToDisplay.forEach((todo) => {
            const li = document.createElement("li");
            li.textContent = `${todo.description} - Due: ${todo.dueDate}`;
            li.onclick = () => deleteTodo(todo.id);
            todoListElement.appendChild(li);
        });
    }
}

/**
 * Deletes a todo item by its ID.
 * @param {number} id - The ID of the todo to delete
 */
async function deleteTodo(index) {
    try {
        const response = await fetch(`${API_URL}/${index}`, {
            method: "DELETE",
        });

        if (response.ok) {
            await loadTodos();
        } else {
            console.error("Failed to delete TODO:", response.statusText);
            showError("Could not delete the todo. Please try again.");
        }
    } catch (error) {
        console.error("Error deleting TODO:", error);
        showError("An error occurred while deleting the todo.");
    }
}

/**
 * Filters the todos based on a search query.
 * @param {Event} event - The input event from the search field
 */
function filterTodos() {
    const searchQuery = document.getElementById("search-input").value.toLowerCase();
    const filteredTodos = todos.filter(todo =>
        todo.description.toLowerCase().includes(searchQuery) ||
        todo.dueDate.toLowerCase().includes(searchQuery)
    );
    renderTodos(filteredTodos);
}

/**
 * Adds a new todo item to the list.
 * @param {Event} event - The submit event from the todo form
 */
async function addTodo(event) {
    event.preventDefault();

    const description = document.getElementById("description").value.trim();
    const dueDate = document.getElementById("dueDate").value;
    const title = document.getElementById("title").value.trim();

    if (!description || !dueDate || !title) {
        showError("Please provide a title, description, and a due date.");
        return;
    }

    const newTodo = {
        title,
        description,
        dueDate,
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
            await loadTodos();
            document.getElementById("todo-form").reset();
        } else {
            console.error("Failed to add todo:", response.statusText);
            showError("Could not add the todo. Please try again.");
        }
    } catch (error) {
        console.error("Error adding todo:", error);
        showError("An error occurred while adding the todo.");
    }
}

/**
 * Displays an error message in the error message container.
 * @param {string} message - The error message to display
 */
function showError(message) {
    const errorElement = document.getElementById("error-message");
    errorElement.textContent = message;
    errorElement.style.display = "block";

    setTimeout(() => {
        errorElement.style.display = "none";
    }, 5000);
}

// Event-Listener
window.onload = loadTodos;
document.getElementById("todo-form").addEventListener("submit", addTodo);
document.getElementById("search-input").addEventListener("input", filterTodos);