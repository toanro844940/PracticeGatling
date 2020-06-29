var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "10",
        "ok": "0",
        "ko": "10"
    },
    "minResponseTime": {
        "total": "1104",
        "ok": "-",
        "ko": "1104"
    },
    "maxResponseTime": {
        "total": "1480",
        "ok": "-",
        "ko": "1480"
    },
    "meanResponseTime": {
        "total": "1208",
        "ok": "-",
        "ko": "1208"
    },
    "standardDeviation": {
        "total": "129",
        "ok": "-",
        "ko": "129"
    },
    "percentiles1": {
        "total": "1158",
        "ok": "-",
        "ko": "1158"
    },
    "percentiles2": {
        "total": "1234",
        "ok": "-",
        "ko": "1234"
    },
    "percentiles3": {
        "total": "1449",
        "ok": "-",
        "ko": "1449"
    },
    "percentiles4": {
        "total": "1474",
        "ok": "-",
        "ko": "1474"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 10,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5",
        "ok": "-",
        "ko": "5"
    }
},
contents: {
"req_getlistuser-fcad2": {
        type: "REQUEST",
        name: "getListUser",
path: "getListUser",
pathFormatted: "req_getlistuser-fcad2",
stats: {
    "name": "getListUser",
    "numberOfRequests": {
        "total": "10",
        "ok": "0",
        "ko": "10"
    },
    "minResponseTime": {
        "total": "1104",
        "ok": "-",
        "ko": "1104"
    },
    "maxResponseTime": {
        "total": "1480",
        "ok": "-",
        "ko": "1480"
    },
    "meanResponseTime": {
        "total": "1208",
        "ok": "-",
        "ko": "1208"
    },
    "standardDeviation": {
        "total": "129",
        "ok": "-",
        "ko": "129"
    },
    "percentiles1": {
        "total": "1158",
        "ok": "-",
        "ko": "1158"
    },
    "percentiles2": {
        "total": "1234",
        "ok": "-",
        "ko": "1234"
    },
    "percentiles3": {
        "total": "1449",
        "ok": "-",
        "ko": "1449"
    },
    "percentiles4": {
        "total": "1474",
        "ok": "-",
        "ko": "1474"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 10,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5",
        "ok": "-",
        "ko": "5"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
