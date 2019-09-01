#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <Adafruit_Fingerprint.h>
#include <SoftwareSerial.h>

#define FIREBASE_HOST "medidatanew.firebaseio.com"  // appdata base
#define FIREBASE_AUTH "kWPFyPsuVScvDQdJEdcA8C9wvKjksuydqxm0J4mW"
#define WIFI_SSID "don't look here"
#define WIFI_PASSWORD "asdfghjklp"


uint8_t getFingerprintEnroll(int id);
SoftwareSerial mySerial(12, 13); // 12 - D6 - yellow 
                                 // 13 - d7 - blue
Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);

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

  while (!Serial);
  delay(100);
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Serial.println("Finger print identification");

  // set the data rate for the sensor serial port
  finger.begin(57600);

  if (finger.verifyPassword()) {
    Serial.println("Found fingerprint sensor!");
  } else {
    Serial.println("Not found fingerprint sensor :(");
    while (1) {
      delay(1);
    }
  }

  finger.getTemplateCount();
  Serial.print("Sensor contains "); Serial.print(finger.templateCount); Serial.println(" templates");
  Serial.println("Waiting for valid finger...");
}

void loop()
{
  getFingerprintIDez();
  delay(50);
}

uint8_t getFingerprintID() {
  uint8_t p = finger.getImage();
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image taken");
      break;
    case FINGERPRINT_NOFINGER:
      Serial.println("No finger detected");
      return p;
    case FINGERPRINT_PACKETRECIEVEERR:
      Serial.println("Communication error");
      return p;
    case FINGERPRINT_IMAGEFAIL:
      Serial.println("Imaging error");
      return p;
    default:
      Serial.println("Unknown error");
      return p;
  }

  p = finger.image2Tz();
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image converted");
      break;
    case FINGERPRINT_IMAGEMESS:
      Serial.println("Image too messy");
      return p;
    case FINGERPRINT_PACKETRECIEVEERR:
      Serial.println("Communication error");
      return p;
    case FINGERPRINT_FEATUREFAIL:
      Serial.println("Could not find fingerprint features");
      return p;
    case FINGERPRINT_INVALIDIMAGE:
      Serial.println("Could not find fingerprint features");
      return p;
    default:
      Serial.println("Unknown error");
      return p;
  }

  p = finger.fingerFastSearch();
  if (p == FINGERPRINT_OK) {
    Serial.println("Found a print match!");
  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
    Serial.println("Communication error");
    return p;
  } else if (p == FINGERPRINT_NOTFOUND) {
    Serial.println("Did not find a match");
    return p;
  } else {
    Serial.println("Unknown error");
    return p;
  }

  Serial.print("Found ID #"); Serial.print(finger.fingerID);
  Serial.print(" with confidence "); Serial.println(finger.confidence);

  return finger.fingerID;
}

int getFingerprintIDez() {
  uint8_t p = finger.getImage();
  if (p != FINGERPRINT_OK)  return -1;

  p = finger.image2Tz();
  if (p != FINGERPRINT_OK)  return -1;

  p = finger.fingerFastSearch();
  if (p != FINGERPRINT_OK)  return -1;

  // found a match!
  Serial.print("Found ID #"); Serial.print(finger.fingerID);
  Serial.print(" with confidence "); Serial.println(finger.confidence);
  
  int a = finger.fingerID;
  
  String Enroll = String("Users") + String(a); // adding these two everytime creates a new child in fire base
  
  Firebase.pushString("fingerprints Identified" ,(Enroll));  // To get multiple entries in the database
  if (Firebase.failed())
  {
    Serial.println(Firebase.error());
  }
  Serial.println("send to onlinedata base");
  return finger.fingerID;

}
