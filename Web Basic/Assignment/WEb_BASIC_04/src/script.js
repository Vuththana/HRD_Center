// ADD TASK
let form = document.getElementById("addForm");
let showForm = document.getElementById("show-form");
let closeForm = document.getElementById("close-form");
let addTask = document.getElementById("add-task");

// DELETE TASK
let deleteForm = document.getElementById("delete");
let yesButton = document.getElementById("yes-button");
let noButton = document.getElementById("no-button");

// UPDATE TASK
let updateForm = document.getElementById("update-form")

let tbody = document.getElementById("tbody");


let task = document.getElementById("task");
let tasks = [];

function getSelectedStatus() {
  const radios = document.getElementsByName("status");
  for (let radio of radios) {
    if (radio.checked) {
      return radio.value;
    }
  }
  return null;
}

function getSelectedPriority() {
  const radios = document.getElementsByName("priority");
  for (let radio of radios) {
    if (radio.checked) {
      return radio.value;
    }
  }
  return null;
}

function showTaskToTable() {
    tbody.innerHTML = "";

    JSON.parse(localStorage.getItem("tasks") || '[]').forEach((taskValue, index) => {
    let tr = document.createElement("tr");
    tr.className = "w-full h-[80px] bg-white text-[20px] font-[800]";

    let td1 = document.createElement("td");
    td1.innerHTML = taskValue.taskName;
    td1.className = "font-[700] px-12 max-w-[70px] rounded-tl-[15px]";
    tr.appendChild(td1);

    let td2 = document.createElement("td");
    td2.innerHTML =
      taskValue.taskPriority.charAt(0).toUpperCase() +
      taskValue.taskPriority.slice(1);
    td2.className =
      "font-[700] " +
      (taskValue.taskPriority === "high"
        ? "text-red-500"
        : taskValue.taskPriority === "medium"
        ? "text-yellow-500"
        : "text-green-500");
    tr.appendChild(td2);

    let td3 = document.createElement("td");
    td3.className = "font-[700]";
    td3.innerHTML =
      taskValue.taskStatus.charAt(0).toUpperCase() +
      taskValue.taskStatus.slice(1);
    tr.appendChild(td3);

    let td4 = document.createElement("td");
    let editIcon = document.createElement("i");
    let trashIcon = document.createElement("i");
    let div = document.createElement("div");
    td4.className="rounded-br-[15px]"
    editIcon.className = "fas fa-edit text-purple-500 text-[30px]";
    editIcon.addEventListener("click", () => {
        updateTask(index)
    })
    trashIcon.className = "fas fa-trash text-red-500 text-[30px]";
    trashIcon.addEventListener("click", () => {
      areYouSure(index);
    });
    div.appendChild(editIcon);
    div.appendChild(trashIcon);
    div.className = "flex gap-[20px]";
    td4.appendChild(div);
    tr.appendChild(td4);

    tbody.appendChild(tr);
  });
}

function areYouSure(index) {
    deleteForm.className =
    "absolute inset-0 flex flex-col justify-center items-center bg-black/50";
    yesButton.addEventListener("click", () => {
        deleteForm.className = "hidden";
        let tasks = JSON.parse(localStorage.getItem("tasks") || []);
        tasks.splice(index, 1);
        localStorage.setItem("tasks", JSON.stringify(tasks))
        showTaskToTable(tasks);
    });
    noButton.addEventListener("click", () => {
        deleteForm.className = "hidden";
    })
}

function updateTask(index) {


    let taskValue = JSON.parse(localStorage.getItem("tasks") || '[]')[index]
    console.log(taskValue.taskName, taskValue.taskPriority, taskValue.taskStatus)
    
}

function addTaskToList(taskValue, priority, status) {
  tasks.push({
    taskName: taskValue,
    taskPriority: priority,
    taskStatus: status,
  });
  localStorage.setItem("tasks", JSON.stringify(tasks));
  showTaskToTable();
}

showForm.addEventListener("click", () => {
  form.className =
    "absolute inset-0 flex flex-col justify-center items-center bg-black/50";
});

closeForm.addEventListener("click", () => {
  form.className = "hidden";
});

addTask.addEventListener("submit", (e) => {
  e.preventDefault();
  if (
    task.value == "" ||
    getSelectedPriority() == null ||
    getSelectedStatus() == null
  ) {
    alert("All fields must be filled.");
    return;
  }  
  addTaskToList(task.value, getSelectedPriority(), getSelectedStatus());
  form.className = "hidden";
});
showTaskToTable();