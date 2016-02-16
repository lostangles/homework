/*
  Piezo Beep.c

  Beep a piezo speaker connected to Propeller I/O pin P4.
  
  http://learn.parallax.com/propeller-c-simple-circuits/piezo-beep
*/

#include "simpletools.h"                      // Include simpletools                   
#include "adcDCpropab.h"                      // Include adcDCpropab
#include "servo.h"                            // Include servo header

int main()                                    // main function             
{

  set_directions(7, 0, 0b11111111);          // Initiate 7-seg
  adc_init(21, 20, 19, 18);                  // Initiate ADC

  float v1;                               // Voltage variables for ADC
  int t;                                  // RC time constant for photo sensor
  
  while(1)                                    // Loop repeats indefinitely
  {
    int button = input(13);                   //Get button status
    v1 = (int)adc_volts(3);                   // Check A/D value                
    dac_ctr(26, 0, 51*v1);                    //Light up red LED based on 
                                              //pot voltage divider
        
    if (button == 1) {                        //Control tone + servo
      freqout(10, 1000, 3000); 
      servo_angle(16, 1800); 
      }    
      else
      {
       servo_angle(16, 0);     
      }        

    putChar(HOME);                            // Cursor -> top-left "home"
    print("A/D1 = %f V%c\n", v1, CLREOL);     // Display volts
                                              //Display ADC value on 7-seg
    if ( v1 == 0 ) set_outputs(7, 0, 0b01111110);  //Display 0
    if ( v1 == 1 ) set_outputs(7, 0, 0b01001000);  //Display 1
    if ( v1 == 2 ) set_outputs(7, 0, 0b00111101);  //Display 2
    if ( v1 == 3 ) set_outputs(7, 0, 0b01101101);  //Display 3
    if ( v1 == 4 ) set_outputs(7, 0, 0b01001011);  //Display 4 
    if ( v1 == 5 ) set_outputs(7, 0, 0b01100111);  //Display 5    
    
    //Photo resistor stuff
    high(15);
    pause(1);
    t = rc_time(15,1);
    print("t = %d    ", t);
    dac_ctr(27, 1, 255*(t/1100));                  //Light up LED based on photo
                                                   //Resistor time constant
    print("\nButton = %d\n", button);
    pause(100);
  }                  
}
