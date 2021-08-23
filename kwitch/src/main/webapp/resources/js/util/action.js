function update_modal() {
	
    const Modals = document.querySelectorAll("[data-modal]");

    Modals.forEach(function (trigger) {
        console.log("trigger is ", trigger);

        trigger.addEventListener("click", function (event) {
            event.preventDefault();

            const modal = document.getElementById(trigger.dataset.modal);
            modal.classList.add("open");

            const exits = modal.querySelectorAll(".modal-exit");
            exits.forEach(function (exit) {
                exit.addEventListener("click", function (event) {
                    event.preventDefault();
                    modal.classList.remove("open");
                });
            });

        });

    });
}

function dp_menu(){
    let click = document.getElementById("drop-content");
    if(click.style.display === "none"){
        click.style.display = "block";

    }else{
        click.style.display = "none";

    }
}

function inNumber(){
    if(event.keyCode<48 || event.keyCode>57){
       event.returnValue=false;
    }
}

function copyObj(obj) {
    const result = {};

    for (let key in obj) {
        if (typeof obj[key] === 'object') {
            result[key] = copyObj(obj[key]);
        } else {
            result[key] = obj[key];
        }
    }

    return result;
}
