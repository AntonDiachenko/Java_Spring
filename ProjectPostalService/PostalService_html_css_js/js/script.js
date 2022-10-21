const HOST = "http://localhost:8080"

// Customer functions
// POST
function registerCustomer() {

    const custFistName = document.getElementById('customer-first-name').value;
    const custLastName = document.getElementById('customer-last-name').value;

    $.ajax({

        method: "post",
        url: `${HOST}/customer`,
        data: JSON.stringify({
            "firstName": custFistName,
            "lastName": custLastName
        }),

        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        alert('customer registered')

    }
    ).fail((obj, textStatus) => {
        if (obj && obj.responseJSON && Object.responseJSON.message) {
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText) {
            alert(obj.responseText)
        }
    })
}

// GET
// function showAllCustomers() {

//     $.ajax(
//         {
//             method: "get",
//             url: `${HOST}/customer`
//         }).done((response) => {
//             document.getElementById('all-customers-here').innerHTML = JSON.stringify(response);

//         }).fail((obj, textStatus) => {
//             if (obj && obj.responseJSON && Object.responseJSON.message) {
//                 alert(obj.responseJSON.message)
//             }
//             if (obj && obj.responseText) {
//                 alert(obj.responseText)
//             }
//         })
// }

function showAllCustomers() {

    fetch('http://localhost:8080/customer')
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            appendData(data);
        })
        .catch(function (err) {
            console.log('error: ' + err);
        });
    function appendData(data) {
        var mainContainer = document.getElementById("all-customers-here");
        for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = 'ID: ' + data[i].custID + ' - ' + data[i].firstName + ' ' + data[i].lastName;
            mainContainer.appendChild(div);
        }
    }
}


// GET
function getCustomerByID() {
    const id = parseInt(document.getElementById('cust-id').value);

    $.ajax(
        {
            method: "get",
            url: `${HOST}/customer/id/${id}`
        }).done((response) => {
            document.getElementById('cust-first-name').value = response.firstName; // 'firstName' corresponds to model
            document.getElementById('cust-last-name').value = response.lastName; // 'lastName' corresponds to model

        }).fail((obj, textStatus) => {
            if (obj && obj.responseJSON && Object.responseJSON.message) {
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText) {
                alert(obj.responseText)
            }
        })
}

// GET
function getCustomerByLastName() {
    const sendlastname = document.getElementById('enter-cust-last-name').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/customer/name/${sendlastname}`
        }).done((response) => {
            document.getElementById('show-cust-id').value = response.custID;
            document.getElementById('show-cust-first-name').value = response.firstName;
            document.getElementById('show-cust-last-name').value = response.lastName;

        }).fail((obj, textStatus) => {
            if (obj && obj.responseJSON && Object.responseJSON.message) {
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText) {
                alert(obj.responseText)
            }
        })
}

// PUT
function updateCustomerByID() {
    const id = parseInt(document.getElementById('cust-id').value);
    const custPutFistName = document.getElementById('cust-first-name').value;
    const custPutLastName = document.getElementById('cust-last-name').value;

    $.ajax({

        method: "put",
        url: `${HOST}/customer/id/${id}`,
        data: JSON.stringify({
            "firstName": custPutFistName,
            "lastName": custPutLastName
        }),

        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        alert('customer updated')

    }
    ).fail((obj, textStatus) => {
        if (obj && obj.responseJSON && Object.responseJSON.message) {
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText) {
            alert(obj.responseText)
        }
    })
}

// DELETE
function deleteCustomerByID() {
    const id = parseInt(document.getElementById('cust-id').value);

    $.ajax({
        method: "delete",
        url: `${HOST}/customer/id/${id}`,
    }).done((response) => {
        alert('customer deleted')

    }
    ).fail((obj, textStatus) => {
        if (obj && obj.responseJSON && Object.responseJSON.message) {
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText) {
            alert(obj.responseText)
        }
    })
}


// Employee functions
// POST
function registerEmployee() {

    const empRegBranch = parseInt(document.getElementById('employee-Reg-Branch').value);
    const empFistName = document.getElementById('employee-first-name').value;
    const empLastName = document.getElementById('employee-last-name').value;

    $.ajax({

        method: "post",
        url: `${HOST}/employee`,
        data: JSON.stringify({
            "branchID": empRegBranch,
            "firstName": empFistName,
            "lastName": empLastName
        }),

        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json',
        }
    }).done((response) => {
        alert('new employee registered')

    }
    ).fail((obj, textStatus) => {
        if (obj && obj.responseJSON && Object.responseJSON.message) {
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText) {
            alert(obj.responseText)
        }
    })
}

// GET
// function showEmployeesByBranch() {
//     const branchID = parseInt(document.getElementById('employee-choose-branch').value);

//     $.ajax(
//         {
//             method: "get",
//             url: `${HOST}/employee/branchid/${branchID}`
//         }).done((response) => {
//             document.getElementById('employees-by-branch-here').innerHTML = JSON.stringify(response);

//         }).fail((obj, textStatus) => {
//             if (obj && obj.responseJSON && Object.responseJSON.message) {
//                 alert(obj.responseJSON.message)
//             }
//             if (obj && obj.responseText) {
//                 alert(obj.responseText)
//             }
//         })
// }

function showEmployeesByBranch() {
    const branchID = parseInt(document.getElementById('employee-choose-branch').value);

    fetch('http://localhost:8080/employee/branchid/' + branchID)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            appendData(data);
        })
        .catch(function (err) {
            console.log('error: ' + err);
        });
    function appendData(data) {
        var mainContainer = document.getElementById("employees-by-branch-here");
        for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = 'ID: ' + data[i].empID + ' - ' + data[i].firstName + ' ' + data[i].lastName;
            mainContainer.appendChild(div);
        }
    }

}


// GET
function getEmployeeByID() {
    const empl_id = document.getElementById('empl-id').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/employee/id/${empl_id}`
        }).done((response) => {
            document.getElementById('empl-branch-id').value = response.branchID;
            document.getElementById('empl-first-name').value = response.firstName; // 'firstName' corresponds to model
            document.getElementById('empl-last-name').value = response.lastName; // 'lastName' corresponds to model

        }).fail((obj, textStatus) => {
            if (obj && obj.responseJSON && Object.responseJSON.message) {
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText) {
                alert(obj.responseText)
            }
        })
}

// PUT
function updateEmployeeByID() {
    const updateEmpid = parseInt(document.getElementById('empl-id').value);
    const emplPutBranchID = document.getElementById('empl-branch-id').value;
    const emplPutFirstName = document.getElementById('empl-first-name').value;
    const emplPutLastName = document.getElementById('empl-last-name').value;

    $.ajax({

        method: "put",
        url: `${HOST}/employee/id/${updateEmpid}`,
        data: JSON.stringify({
            "branchID": emplPutBranchID,
            "firstName": emplPutFirstName,
            "lastName": emplPutLastName
        }),

        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        alert('employee updated')

    }
    ).fail((obj, textStatus) => {
        if (obj && obj.responseJSON && Object.responseJSON.message) {
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText) {
            alert(obj.responseText)
        }
    })
}

// DELETE
function deleteEmployeeByID() {
    const delID = document.getElementById('empl-id').value;

    $.ajax({
        method: "delete",
        url: `${HOST}/employee/id/${delID}`,
    }).done((response) => {
        alert('employee deleted')

    }
    ).fail((obj, textStatus) => {
        if (obj && obj.responseJSON && Object.responseJSON.message) {
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText) {
            alert(obj.responseText)
        }
    })
}



// Orders functions
// POST
function registerOrder() {

    const orderBranch = document.getElementById('orderBranch').value;
    const orderCustID = document.getElementById('orderCustID').value;
    const orderEmpID = document.getElementById('orderEmpID').value;
    const orderShipType = document.getElementById('orderShipType').value;
    const orderAddress = document.getElementById('orderAddress').value;


    $.ajax({

        method: "post",
        url: `${HOST}/shiporder`,
        data: JSON.stringify({
            "branchID": orderBranch,
            "custID": orderCustID,
            "empID": orderEmpID,
            "shipTypeID": orderShipType,
            "shipAddress": orderAddress
        }),

        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        alert('Order registered. Thank You!')
    }
    ).fail((obj, textStatus) => {
        if (obj && obj.responseJSON && Object.responseJSON.message) {
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText) {
            alert(obj.responseText)
        }
    })
}

// GET
// function showOrdersByCustomer() {
//     const orderByCustID = parseInt(document.getElementById('orderBycust-id').value);

//     $.ajax(
//         {
//             method: "get",
//             url: `${HOST}/shiporder/customer/${orderByCustID}`
//         }).done((response) => {
//             document.getElementById('orders-by-customer-here').innerHTML = JSON.stringify(response);

//         }).fail((obj, textStatus) => {
//             if (obj && obj.responseJSON && Object.responseJSON.message) {
//                 alert(obj.responseJSON.message)
//             }
//             if (obj && obj.responseText) {
//                 alert(obj.responseText)
//             }
//         })
// }

function showOrdersByCustomer() {
    const orderByCustID = parseInt(document.getElementById('orderBycust-id').value);


    fetch('http://localhost:8080/shiporder/customer/' + orderByCustID)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            appendData(data);
        })
        .catch(function (err) {
            console.log('error: ' + err);
        });
    function appendData(data) {
        var mainContainer = document.getElementById("orders-by-customer-here");
        for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = 'ID: ' + data[i].custID + ' - ' + data[i].firstName + ' ' + data[i].lastName +
                ', Order ID: ' + data[i].shipOrderID +
                ', Branch: ' + data[i].branchName +
                ', Shipping Type: ' + data[i].shipTypeDescrip +
                ', Address: ' + data[i].shipAddress;
            mainContainer.appendChild(div);
        }
    }

}

     /** THIS ONE WAS SHOWED DURING LECTURE */
// function showOrdersByShipType() {
//     const shipTypeID = document.getElementById('order-by-shipping-type').value;

//     $.ajax(
//         {
//             method: "get",
//             url: `${HOST}/shiporder/shippingtype/${shipTypeID}`
//         }).done((response) => {
//             document.getElementById('orders-by-shiptype-here').innerHTML = JSON.stringify(response);

//         }).fail((obj, textStatus) => {
//             if (obj && obj.responseJSON && Object.responseJSON.message) {
//                 alert(obj.responseJSON.message)
//             }
//             if (obj && obj.responseText) {
//                 alert(obj.responseText)
//             }
//         })
// }

// GET



     /** THIS ONE IS ALTERNATIVE WITHOUT TABLE */
/*    
function showOrdersByShipType() {
    const shipTypeID = document.getElementById('order-by-shipping-type').value;

        fetch('http://localhost:8080/shiporder/shippingtype/' + shipTypeID) 
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            appendData(data);
        })
        .catch(function (err) {
            console.log('error: ' + err);
        });
    function appendData(data) {
        var mainContainer = document.getElementById("orders-by-shiptype-here");
        for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = 'Shipping Type: ' + data[i].shipTypeDescrip + 
                            ', Order ID: ' + data[i].shipOrderID + 
                            ', Address: ' + data[i].shipAddress + 
                            ', Name: ' + data[i].firstName + ' ' + data[i].lastName + 
                            ', Branch: ' +  data[i].branchName;
            mainContainer.appendChild(div);
        }
    }


}
*/

function showOrdersByShipType() {
    const shipTypeID = document.getElementById('order-by-shipping-type').value;

    fetch('http://localhost:8080/shiporder/shippingtype/' + shipTypeID)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            appendData(data);
        })
        .catch(function (err) {
            console.log('error: ' + err);
        });
    function appendData(data) {
        var mainContainer = document.getElementById("orders-by-shiptype-here");
        for (var i = 0; i < data.length; i++) {
                var tr = document.createElement("tr");
                mainContainer.appendChild(tr);

                var td1 = document.createElement("td");
                td1.innerHTML = data[i].shipTypeDescrip;
                mainContainer.appendChild(td1);

                var td2 = document.createElement("td");
                td2.innerHTML = data[i].shipOrderID
                mainContainer.appendChild(td2);

                var td3 = document.createElement("td");
                td3.innerHTML = data[i].shipAddress
                mainContainer.appendChild(td3);

                var td4 = document.createElement("td");
                td4.innerHTML = data[i].firstName + ' ' + data[i].lastName
                mainContainer.appendChild(td4);

                var td5 = document.createElement("td");
                td5.innerHTML = data[i].branchName;
                mainContainer.appendChild(td5);
        }
    }
}