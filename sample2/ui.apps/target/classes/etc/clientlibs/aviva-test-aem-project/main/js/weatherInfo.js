 $(document).ready(function(){  
              $("#submit").click(function(){  
                  var city = $('#city').val();  
                       $.ajax({  
                          type: "GET",  
                          url: "/bin/get/weatherinfo",  
                          data: "city="+ city,  
                           error: function(data){  
                           $('.results').html("Please try later");
                          }  ,
                          success: function(data){  
						  var weather = JSON.parse(data.weatherData);
                           var location = weather.location;
                           var current = weather.current;
                           $('.cityname').html("City Name : " + location.name);
                           $('.localtime').html("Local Time: " + location.localtime);
                           $('.temp').html("Temp in Celcius: " + current.temp_c);
                           $('.tempf').html("Temp in Farenheit:" + current.temp_f);

                          }  
                      });   

              });  
          });  




