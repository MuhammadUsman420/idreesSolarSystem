setFirstPaginationIconColor()
function setFirstPaginationIconColor(){
    document.getElementById('first_page_for_color').style.backgroundColor= "#b9b6b6";
    document.getElementById('second_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('third_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('forth_page_for_color').style.backgroundColor= "#eaeaea";
}
function goToFirstPage(){
    document.getElementById('first_page').style.display= "block";
    document.getElementById('second_page').style.display= "none";
    document.getElementById('third_page').style.display= "none";
    document.getElementById('forth_page').style.display= "none";

    document.getElementById('first_page_for_color').style.backgroundColor= "#b9b6b6";
    document.getElementById('second_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('third_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('forth_page_for_color').style.backgroundColor= "#eaeaea";
    

}

function GoToSecondPage(){
    document.getElementById('second_page').style.display= "block";
    document.getElementById('first_page').style.display= "none";
    document.getElementById('third_page').style.display= "none";
    document.getElementById('forth_page').style.display= "none";

    document.getElementById('second_page_for_color').style.backgroundColor= "#b9b6b6";
    document.getElementById('first_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('third_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('forth_page_for_color').style.backgroundColor= "#eaeaea";

}

function goToThirdPage(){
    document.getElementById('first_page').style.display= "none";
    document.getElementById('second_page').style.display= "none";
    document.getElementById('forth_page').style.display= "none";
    document.getElementById('third_page').style.display= "block";

    document.getElementById('third_page_for_color').style.backgroundColor= "#b9b6b6";
    document.getElementById('second_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('first_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('forth_page_for_color').style.backgroundColor= "#eaeaea";

}

function goToFifthPage(){
    document.getElementById('first_page').style.display= "none";
    document.getElementById('second_page').style.display= "none";
    document.getElementById('third_page').style.display= "none";
    document.getElementById('forth_page').style.display= "block";

    document.getElementById('third_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('second_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('first_page_for_color').style.backgroundColor= "#eaeaea";
    document.getElementById('forth_page_for_color').style.backgroundColor= "#b9b6b6";

}

function myFunction() {

    var increase_height = document.getElementById("apply_height")
    if(increase_height.className === "main-header__inner"){
        increase_height.className += " increase_height"
    }else{
        increase_height.className = "main-header__inner"
    }

    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += "responsive";
    } else {
      x.className = "topnav";
    }
  }

  count=1
  localStorage.setItem('imageslide',(count))
  function changeBackground(){
 
    var imageOne;
    var imageTwo;
    var getcount;
        getcount = localStorage.getItem('imageslide')
        if(getcount%2 == 0){
            imageOne=true;
            imageTwo=false
        }else{
            imageOne=false;
            imageTwo=true
        }

    if(imageOne == true){
        document.getElementById('img_two').style.display= "none";
        document.getElementById('img_one').style.display = "block";
        incresaseCount = localStorage.getItem('imageslide')
        incresaseCount++
        localStorage.setItem('imageslide',(incresaseCount))
      } else if(imageTwo == true){
        document.getElementById('img_one').style.display= "none";
        document.getElementById('img_two').style.display = "block";
        incresaseCount = localStorage.getItem('imageslide')
        incresaseCount++
        localStorage.setItem('imageslide',(incresaseCount))
      }

    // if(imageOne == true){
    //     document.getElementById('img_one').style.display= "none";
    // } else if(imageOne == false) {
    //     document.getElementById('img_one').style.display= "none";
    //     document.getElementById('img_two').style.display = "block";
    // }


  }