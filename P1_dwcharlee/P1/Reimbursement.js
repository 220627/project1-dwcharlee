const url = "http://localhost:3000";

document.getElementById("getUsersButton").onclick = getEmployees;

async function getUserss(){

    let response = await fetch(url + "/users")
    
    console.log(response)

    if(response.status == 200){

        let data = await reponse.json;

        for(let users of data){

            let row = document.createElement("tr");
            let cell = document.createElement("td");        
            cell.innerHTML = users.ers_users_id;
            row.appendChild(cell);
            cell.innerHTML = users.ers_username;
            row.appendChild(cell);
            cell.innerHTML = users.ers_password;
            row.appendChild(cell);

            document.getElementById("usersBody").appendChild("row");
        }
    } else {
        alert("Something went wrong! Make sure your Java is running!");
    }
}