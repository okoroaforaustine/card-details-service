# card-details-service

swagger-documentation:https://card-details-service.herokuapp.com/swagger-ui.html#/

To generate APIKEY :  https://card-details-service.herokuapp.com/User/generateAPIKey

request {"appID": "3line"}

response{
    "success": "true",
    "payload": {
        "id": 1,
        "appKey": "APIKEY_17644261",
        "appID": "3line"
    }
}

app key:"APIKEY_17644261"
Authorization header:"appID+hash"
timeStamp:********
