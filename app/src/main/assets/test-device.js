var vibration_candevicevibrate = document.getElementById("Vibration.canDeviceVibrate");

if (Natives) {
    if(Natives.canDeviceVibrate()) {
        vibration_candevicevibrate.value = "online";
    } else {
        vibration_candevicevibrate.value = "offline";
    }
}