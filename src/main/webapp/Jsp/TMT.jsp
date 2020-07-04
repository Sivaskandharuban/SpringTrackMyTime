<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang = 'en-US'>

<head>
    <link rel="stylesheet" href="Style-TMT.css">
</head>

<body class="BodyStyle">
<p id = "message" style = "text-align : center"></p>
    <div>
<aside class="sidenav">
    <div class="side-nav-content">
        <h2>Track My Time</h2>
        <img src="dummy-profile-pic-300x300.jpg" />
        <p><b id = "timer">00:00:00</b></p>
        <label class="switch">
            <input type="checkbox" id = "check" onclick="set()" >
            <span class="slider round"></span>
          </label>
          
          <div id = "Signout">
<form action="\Signout">
<input type = "submit" value = "Sign out">
</form>
</div>
         
        <!-- <div class="toggle-btn"> -->
            <!-- <div class="inner-circle">
              <span>Clockin</span>
            </div> -->
            
        <!-- </div> -->
    </div>
</aside>

 

<div>
    <h1 class="Style">Welcome to TMT</h1>
    <select name="Timezone" id="Timezone" style= "float: right; display: block">
    <option value="Time Zone">Indian Time Zone</option>
    <option value="Time Zone">Detect Time Zone</option>
    </select>
    <label for="Timezone" style ="color: black; text-align: right; float: right; padding-right: 13px;">
        Choose Timezone
    </label>
</div>

<div class="Log">
<section >
    <table id = "clockTable">
        
        <tr> 
        <td>            
            <select name="Date" id="Date">
                <option value="Current_Week">Select date</option>
                <option value="Last_Week">Last Week</option>
                </select>  
                </td>
                
                <td>
                <button onclick = "showEntry()">
                Show Entries
                </button>
                </td>          
            </tr>
            
            <tr id = "todayDate">
            <td>
            Today's date
            </td>
            </tr>
            

            <tr>
                <td>
                Task description
            </td>
            <td>
                Project Working
            </td>

            <td>
                Time from 
            </td>

            <td>
                Time to 
            </td>

            <td>
                Total working hours
            </td>
            </tr>
            
             <tr id = "initial">
                <td>
                Add task description
            </td>
            <td>
                Project Working
            </td>

            <td id = "timeStarted">
                
            </td>

            <td id = "timeEnded">
                
            </td>

            <td id = "totalTime">
                
            </td>
            </tr>
            
            
    
    </table>
</section>
</div>
</div>
<script src="Timer.js"></script>

</body>
</html>