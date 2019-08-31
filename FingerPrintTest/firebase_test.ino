#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#define FIREBASE_HOST "hacksumitaaruush.firebaseio.com"
#define FIREBASE_AUTH "78jIAqdMaSziQAa8SpvI1iPq7tTjlfJlJbs7lpRE"  // check for updates
#define WIFI_SSID "don't look here"
#define WIFI_PASSWORD "asdfghjklp"

void setup()
{


  Serial.begin(9600);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println("Connected to internet");

Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);  // don't forget to include 
}
void loop(){
int a = 2;
Firebase.setInt("users id/Matched", a);  // check for slternatives

  if (Firebase.failed())
  {
    Serial.println(Firebase.error());
  }
  Serial.println("send to onlinedata base");
 
}
