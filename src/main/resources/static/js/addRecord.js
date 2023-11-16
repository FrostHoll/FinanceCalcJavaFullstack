window.addEventListener("DOMContentLoaded", (event) => {
    const typeSelect = document.getElementById("recordType");
    const category = document.getElementById("categorySelector");
    const loan = document.getElementById("loanSelector");
    const goal = document.getElementById("goalSelector");
    const deposit = document.getElementById("depositSelector");

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
            case "DEPOSIT":
                hideAllSelectors();
                deposit.hidden = false;
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
        deposit.hidden = true;
    }
});