var rooftype = "";
var Roofinclination = "";
var getRoofing = "Tile";
var roof_type_alternate_border = 2;
var getBorderedElement;
var rooftype
localStorage.removeItem("count")
var getLocal = localStorage.getItem("count")
var rooftypearray = []
var roofinclinationarray = []

// var baseUrl = "http://localhost:8081/api/solar-form"
var baseUrl="https://vodasunakquise.ddevops.com/api/solar-form"

function getRoofType(roof_type) {
  rooftype = roof_type

  if (rooftype != null) {


    JSON.parse(getLocal)
    getLocal + 2
    localStorage.setItem('count', JSON.stringify(getLocal))
    document.getElementById('Flachdach').style.border = 'transparent'
    document.getElementById('Grabendach').style.border = 'transparent'
    document.getElementById('Paralleldach').style.border = 'transparent'
    document.getElementById('Pultdach').style.border = 'transparent'
    document.getElementById('Pulterweitert').style.border = 'transparent'
    document.getElementById('Satteldach').style.border = 'transparent'

    var getFlachdach = document.getElementById("Flachdach")
    var getGrabendach = document.getElementById("Grabendach")
    var getParalleldach = document.getElementById("Paralleldach")
    var getPultdach = document.getElementById("Pultdach")
    var getPulterweitert = document.getElementById("Pulterweitert")
    var getSatteldach = document.getElementById("Satteldach")

    getFlachdach.classList.add("otherclass");
    getGrabendach.classList.add("otherclass");
    getParalleldach.classList.add("otherclass");
    getPultdach.classList.add("otherclass");
    getPulterweitert.classList.add("otherclass");
    getSatteldach.classList.add("otherclass");

    var count = 0;
    var applyBorder;
    rooftypearray.push(getFlachdach, getGrabendach, getParalleldach, getPultdach, getPulterweitert, getSatteldach)



    for (let arr of rooftypearray) {
      if (arr.id == roof_type) {
        applyBorder = arr.id
        document.getElementById(applyBorder).style.border = "1px solid blue";
        document.getElementById(applyBorder).style.borderRadius = "4px";
        getBorderedElement = document.getElementById(applyBorder);
        getBorderedElement.classList.remove("otherclass");
        count = 0
      }
    }
  }
}

function getRoofInclination(Roof_inclination) {

  Roofinclination = Roof_inclination;

  document.getElementById('0-grad').style.border = 'transparent'
  document.getElementById('15-grad').style.border = 'transparent'
  document.getElementById('30-grad').style.border = 'transparent'


  var getZero_gradient = document.getElementById("0-grad")
  var getFifteen_gradient = document.getElementById("15-grad")
  var getThirty_gradient = document.getElementById("30-grad")

  getZero_gradient.classList.add("otherclass");
  getFifteen_gradient.classList.add("otherclass");
  getThirty_gradient.classList.add("otherclass");


  var get_roof_inclination_borderedElement;
  roofinclinationarray.push(getZero_gradient, getFifteen_gradient, getThirty_gradient)

  for (let arr of roofinclinationarray) {
    if (arr.id == Roofinclination) {
      let applyBorder = arr.id
      document.getElementById(applyBorder).style.border = "1px solid blue"
      document.getElementById(applyBorder).style.borderRadius = "4px";
      get_roof_inclination_borderedElement = document.getElementById(applyBorder);
      get_roof_inclination_borderedElement.classList.remove("otherclass");
    }
  }
}

var leaseRooftop = false;
var rentRooftop = false;
var buyRooftop = false;
interestedConcept()
function interestedConcept() {
  ;

  const checkleaseRooftop = document.querySelector('#leaseRooftop');
  const checkrentRooftop = document.querySelector('#rentRooftop');
  const checkbuyRooftop = document.querySelector('#buyRooftop');

  leaseRooftop = checkleaseRooftop.checked
  rentRooftop = checkrentRooftop.checked
  buyRooftop = checkbuyRooftop.checked

}


function displayRadioValue() {
  ;
  var ele = document.getElementsByName('roofing');

  for (i = 0; i < ele.length; i++) {
    if (ele[i].checked)
      getRoofing = ele[i].value;
  }
}

document.getElementById("submit_button").disabled = true;

$(document).ready(function () {
  $("input").change(function () {
    ;
    var ManualInput = document.getElementById("manualInput").value
    var getBuildingHeight = document.getElementById("building_height").value
    var getUserFirstName = document.getElementById("firstname").value
    var getUserLastName = document.getElementById("lastname").value
    var getUserCompany = document.getElementById("company").value
    var getUserAdress = document.getElementById("adress").value
    var getUserCountry = document.getElementById("country").value
    var getUserEmail = document.getElementById("email").value
    var getUserPhoneNumber = document.getElementById("phonenumber").value
    var getUserAnulPowerConsumption = document.getElementById("anual_power_consumption").value
    var getUserInputStates = document.getElementById("inputState").value
    var getUserNotes = document.getElementById("notes_and_details").value
    var getUserPrivacyCheck = document.getElementById("privacycheck").value

    var obj = {
      leaseRooftop: leaseRooftop,
      rentRooftop: rentRooftop,
      buyRooftop: buyRooftop,
      area: ManualInput,
      roofType: rooftype,
      roofInclination: Roofinclination,
      roofing: getRoofing,
      buildingHeight: getBuildingHeight,
      firstName: getUserFirstName,
      lastName: getUserLastName,
      company: getUserCompany,
      address: getUserAdress,
      country: getUserCountry,
      email: getUserEmail,
      phoneNumber: getUserPhoneNumber,
      consumption: getUserAnulPowerConsumption,
      notes: getUserNotes,
      privacyCheck: getUserPrivacyCheck,
      attachment: attachment,
      locations: []

    }

    var validationArray = []
    validationArray.push(obj)

    for (let valObj of validationArray) {
      if (valObj.firstName == "" || valObj.lastName == "" ||
        valObj.email == "" || valObj.address == "" || valObj.phoneNumber == "") {
        document.getElementById("submit_button").disabled = true;
      } else {
        document.getElementById("submit_button").disabled = false;
      }
    }


  });
});

//attach file
var selectedFile;
var attachment;

$(document).ready(function () {
  $("#formFile").change(function () {
    // debugger;
    var fileInput = document.getElementById('formFile');
    var fileName = document.getElementById('formFile').value;
    var idxDot = fileName.lastIndexOf(".") + 1;
    var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
    if (extFile == "jpg" || extFile == "jpeg" || extFile == "png") {

      selectedFile = new FormData();
      for (const file of fileInput.files) {
        selectedFile.append("image", file)
      }

      var xhr = new XMLHttpRequest();              // create XMLHttpRequest
      xhr.onload = function () {
        (this.responseText); // whatever the server returns
        attachment = this.responseText
      }

      xhr.open("post", `${baseUrl}` + "/image");      // open connection
      xhr.send(selectedFile);
    } else {
      alert("Only jpg/jpeg and png files are allowed!");
    var fileName = document.getElementById('formFile').value="";
    }


  });
});


function submit() {
  var ManualInput = document.getElementById("manualInput").value
  var getBuildingHeight = document.getElementById("building_height").value
  var getUserFirstName = document.getElementById("firstname").value
  var getUserLastName = document.getElementById("lastname").value
  var getUserCompany = document.getElementById("company").value
  var getUserAdress = document.getElementById("adress").value
  var getUserCountry = document.getElementById("country").value
  var getUserEmail = document.getElementById("email").value
  var getUserPhoneNumber = document.getElementById("phonenumber").value
  var getUserAnulPowerConsumption = document.getElementById("anual_power_consumption").value
  var getUserInputStates = document.getElementById("inputState").value
  var getUserNotes = document.getElementById("notes_and_details").value
  var getUserPrivacyCheck = document.getElementById("privacycheck").value


  if (getUserPrivacyCheck == "on") {
    getUserPrivacyCheck = true
  } else {
    getUserPrivacyCheck = false
  }


  ;

  attachment

  var obj = {
    leaseRooftop: leaseRooftop,
    rentRooftop: rentRooftop,
    buyRooftop: buyRooftop,
    area: ManualInput,
    roofType: rooftype,
    roofInclination: Roofinclination,
    roofing: getRoofing,
    buildingHeight: getBuildingHeight,
    firstName: getUserFirstName,
    lastName: getUserLastName,
    company: getUserCompany,
    address: getUserAdress,
    country: getUserCountry,
    email: getUserEmail,
    phoneNumber: getUserPhoneNumber,
    consumption: getUserAnulPowerConsumption,
    notes: getUserNotes,
    privacyCheck: getUserPrivacyCheck,
    attachment: attachment,
    locations: []
  }

  obj.locations = createLocationObjects();
  //  ("object", obj.locations);

  ("OBJECT", JSON.stringify(obj));
  (async () => {
    //http://localhost:8081/api/solar-form
    //http://localhost:3000/solarForm
    const rawResponse = await fetch(`${baseUrl}`, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(obj)
    });
    const content = await rawResponse.json();
    console.log(content);
    (content);
  })();

  location.replace("thankyou.html");
  
  cleanAllAfterSave();
}

// mapSection

let allAreas = []
var map; // Global declaration of the map
var iw = new google.maps.InfoWindow(); // Global declaration of the infowindow
var lat_longs = new Array();
var markers = new Array();
var drawingManager;
var lat = 40.9403762
var lng = -74.1318096
var mapZoom = 13


function getUserLocation() {
  mapZoom = 18;
  this.flag = true
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(position => {


      this.lat = position.coords.latitude;
      this.lng = position.coords.longitude;

      initialize()

    });
  } else {
    ("User didn't allow")
  }
}

function initialize() {
  (lat + " " + lng);
  var myLatlng = new google.maps.LatLng(lat, lng);
  var myOptions = {
    zoom: mapZoom,
    center: myLatlng,
    mapTypeId: google.maps.MapTypeId.HYBRID
  }
  map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);
  drawingManager = new google.maps.drawing.DrawingManager({
    drawingMode: google.maps.drawing.OverlayType.POLYGON,
    drawingControl: true,
    drawingControlOptions: {
      position: google.maps.ControlPosition.TOP_CENTER,
      drawingModes: [google.maps.drawing.OverlayType.POLYGON]
    },
    polygonOptions: {
      editable: true
    }
  });
  drawingManager.setMap(map);

  google.maps.event.addListener(drawingManager, "overlaycomplete", function (event) {
    var newShape = event.overlay;
    newShape.type = event.type;
  });

  google.maps.event.addListener(drawingManager, "overlaycomplete", function (event) {
    overlayClickListener(event.overlay);
    $('#vertices').val(event.overlay.getPath().getArray());
    getLongLat()
  });

}

function cities() {
  var input = document.getElementById('autocomplete');
  var autocomplete = new google.maps.places.Autocomplete(input);
  google.maps.event.addListener(autocomplete, 'place_changed', function () {
    var place = autocomplete.getPlace();

    lat = place.geometry.location.lat(),
      lng = place.geometry.location.lng();

    initialize()


  })
}

function overlayClickListener(overlay) {
  google.maps.event.addListener(overlay, "mouseup", function (event) {
    $('#vertices').val(overlay.getPath().getArray());

  });
}
google.maps.event.addDomListener(window, 'load', initialize);


// area
function sumOfArea() {
  const sum = allAreas.reduce(add, 0);
  function add(accumulator, a) {
    return accumulator + a;
  }
  (sum);
  document.getElementById('manualInput').value = parseInt(sum)
  // int(sum)
  document.getElementById("markedroof").innerHTML = parseInt(sum)
}

function GetArea(polygon) {
  const length = polygon.length;

  let sum = 0;

  for (let i = 0; i < length; i += 2) {
    sum +=
      polygon[i] * polygon[(i + 3) % length] -
      polygon[i + 1] * polygon[(i + 2) % length];
  }

  return (Math.abs(sum) * 0.5)
}

function latlontocart(latlon) {
  //  
  let latAnchor = latlon[0][0];
  let lonAnchor = latlon[0][1];
  let x = 0;
  let y = 0;
  let R = 6378137; //radius of earth

  let pos = [];

  for (let i = 0; i < latlon.length; i++) {
    let xPos =
      (latlon[i][1] - lonAnchor) * ConvertToRadian(R) * Math.cos(latAnchor);
    let yPos = (latlon[i][0] - latAnchor) * ConvertToRadian(R);

    pos.push(xPos, yPos);
  }
  return pos;
}

function ConvertToRadian(input) {
  return (input * Math.PI) / 180;
}

var all_lonlat = [];
var alllatlon = {}
var objs


function getLongLat() {
  let latlon = []
    ;
  const getlon_lan = document.getElementById('vertices').value;
  x = getlon_lan.replace(/[{()}]/g, '');
  var array = JSON.parse("[" + x + "]");
  while (array.length > 0) {
    chunk = array.splice(0, 2);
    latlon.push(chunk)
  }
  allAreas.push(GetArea(latlontocart(latlon)));
  all_lonlat.push(latlon)

  sumOfArea()
}
var locArr = [];

function createLocationObjects() {

  all_lonlat
  for (let shape = 0; shape < all_lonlat.length; shape++) {
    const eachShapeArr = all_lonlat[shape];
    const shapeObject = eachShapeArr.map(x => ({
      longitude: x[0],
      latitude: x[1],
      element: `shape ${shape + 1}`,
    }));
    locArr.push(...shapeObject);
    ("locArr", locArr);
  }

  return locArr
}

function cleanAllAfterSave() {
  ;
  clearMarkedArea();

  document.getElementById("autocomplete").value = ''
  document.getElementById("manualInput").value = ''
  document.getElementById("building_height").value = ''
  document.getElementById("firstname").value = ''
  document.getElementById("lastname").value = ''
  document.getElementById("company").value = ''
  document.getElementById("adress").value = ''
  document.getElementById("country").value = ''
  document.getElementById("email").value = ''
  document.getElementById("phonenumber").value = ''
  document.getElementById("anual_power_consumption").value = ''
  document.getElementById("inputState").value = ''
  document.getElementById("notes_and_details").value = ''
  document.getElementById("privacycheck").value = ''

  clearRoofType();
}

function clearRoofType() {

  rooftype = null
  getRoofType()
}
function clearMarkedArea() {
  allAreas = []
  allLongLat = []
  all_lonlat = []
  sumOfArea()
  initialize()
}

// google.maps.event.addDomListener(window, 'load', initialize);

// var input = document.getElementById('autocomplete');
// var autocomplete = new google.maps.places.Autocomplete(input);

