#include "simpletools.h"

int ping(int pin)
{
  low(pin);
  pulse_out(pin, 10);
  return pulse_in(pin, 1);
}

int ping_cm(int pin)
{
  long tEcho = ping(pin);
  int cmDist = tEcho / 58;
  return cmDist;
}

int ping_inches(int pin) 
{
  long tEcho = ping(pin);
  int inDist = tEcho / 148;
  return inDist;
}

int ping_hcsr04(int echopin, int triggerpin)
{
  low(triggerpin);
  pulse_out(triggerpin, 10);
  return pulse_in(echopin, 1);
}

int ping_cm_hcsr04(int echopin, int triggerpin)
{
  long tEcho = ping_hcsr04(echopin, triggerpin);
  int cmDist = tEcho / 58;
  return cmDist;
}

int ping_inches_hcsr04(int echopin, int triggerpin) 
{
  long tEcho = ping_hcsr04(echopin, triggerpin);
  int inDist = tEcho / 148;
  return inDist;
}
