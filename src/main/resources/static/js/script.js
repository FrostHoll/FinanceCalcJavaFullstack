window.addEventListener("DOMContentLoaded", (event) => {
    const typeSelect = document.getElementById("recordType");
    const category = document.getElementById("categorySelector");
    const loan = document.getElementById("loanSelector");
    const goal = document.getElementById("goalSelector");

    typeSelect.addEventListener("change", (e) => {
                switch (e.target.value) {
                    case "CATEGORY":
                        hideAllSelectors();
                        category.hidden = false;
                        break;
                    case "LOAN":
                        hideAllSelectors();
                        loan.hidden = false;
                        break;
                    case "GOAL":
                        hideAllSelectors();
                        goal.hidden = false;
                        break;
                    default:
                        hideAllSelectors();
                        break;
                    }
                });

    function hideAllSelectors() {
        category.hidden = true;
        loan.hidden = true;
        goal.hidden = true;
    }


    /*
    typeSelect.addEventListener("change", (e) => {
        switch (e.target.value) {
            case "CATEGORY":
                category.className = "visible";
                loan.className = "hidden";
                break;
            case "LOAN":
                loan.className = "visible";
                category.className = "hidden";
                break;
            default:
                category.className = "hidden";
                loan.className = "hidden";
                break;
        }
    });
    */




    
});



