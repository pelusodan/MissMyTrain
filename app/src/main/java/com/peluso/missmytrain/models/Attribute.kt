package com.peluso.missmytrain.models

data class Attribute(
    val arrival_time: String,
    val direction_id: Int
){}


/*
*
* "data": [
    {
      "attributes": {
        "arrival_time": "2019-12-23T20:12:22-05:00",
        "departure_time": "2019-12-23T20:13:27-05:00",
        "direction_id": 0,
        "schedule_relationship": null,
        "status": null,
        "stop_sequence": 110
      },
      "id": "prediction-43019020-70155-110",
      "relationships": {
        "route": {
          "data": {
            "id": "Green-D",
            "type": "route"
          }
        },
        "stop": {
          "data": {
            "id": "70155",
            "type": "stop"
          }
        },
        "trip": {
          "data": {
            "id": "43019020",
            "type": "trip"
          }
        },
        "vehicle": {
          "data": {
            "id": "G-10007",
            "type": "vehicle"
          }
        }
      },
      "type": "prediction"
    },
* */