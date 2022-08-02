const url = "http://localhost:3000";

document.getElementById("getEmployeeButton").onclick = getEmployees;

async function getEmployees(){

    let response = await fetch(url + "/employees")
    let user = await fetch(url + "/users");
    let 
    
    console.log(response)

    if(response.status == 200){

        let data = await reponse.json;

        for(let employee of data){

            let row = document.createElement("tr");
            let cell = document.createElement("td");        
            cell.innerHTML = employee.employee_id;
            row.appendChild(cell);
            cell.innerHTML = employee.first_name;
            row.appendChild(cell);
            cell.innerHTML = employee.last_name;
            row.appendChild(cell);

            document.getElementById("employeeBody").appendChild("row");
        }
    } else {
        alert("Something went wrong! Make sure your Java is running!");
    }
}