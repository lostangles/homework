/*
  Blank Simple Project.c
  http://learn.parallax.com/propeller-c-tutorials 
*/
#include "simpletools.h"                      // Include simple tools
#include "ping.h"      
#include "servo.h"                            // Include servo header
 

int leds(int ping) {
  int number = -1; // how many led's to light

  if ( 256 >=ping && ping >= 129) number = 1;
  else if ( 256 >=ping && ping >= 129) number = 2;
  else if ( 128 >= ping && ping >= 65) number = 3;
  else if ( 64 >= ping && ping >= 33) number = 4;
  else if ( 32 >= ping && ping >= 17) number = 5;
  else if ( 16 >= ping && ping >= 9) number = 6;
  else if ( ping <= 8) number = 7;
  
  return number;
  
}  

void display(int number) {
  for (int i = 0; i<9 ; i++)
    low(i);
  for(int i = number; i > -1; i--)
    high(i);
}

void servo_move()
 {
   while(1)
   {
   servo_setramp(16, 8);
   servo_angle(16, 0);
   pause(3500);
   servo_angle(16, 1500);
   pause(3500);
   }
 }   
 
void blink() {
 while(1) {
 int button = input(15);
 if (button == 1) {
   high(26);
   pause(223);
   low(26);
   pause(223);
 }   
 else low(26);
 }    
}  

void pinger() {
    int cmDist;
    simpleterm_open();
   while(1)
  {
    cmDist = ping_cm_hcsr04(9,10);
    putChar(HOME); 
    print("Distance = %d         \n", cmDist);
    display(leds(cmDist));
    pause(100);
  }  
}  
int main()                                    // Main function
{
  // Add startup code here.
  simpleterm_close();
  cog_run(pinger, 128);
  cog_run(servo_move, 128);
  cog_run(blink, 128);
}
