#include <Windows.h>
#include <QTextStream>

int main() {
  QTextStream cout(stdout);

  SYSTEM_POWER_STATUS batteryStatus;

  while (true) {
    if (!GetSystemPowerStatus(&batteryStatus)) {
      cout << GetLastError();
      system("pause");
      return -1;
    }
    switch (batteryStatus.ACLineStatus) {
      case 0:
        cout << "AC power status: offline" << endl;
        break;
      case 1:
        cout << "AC power status: online" << endl;
        break;
      default:
        cout << "AC power status: unknown" << endl;
        break;
    }
    switch (batteryStatus.BatteryFlag) {
      case 0:
        cout << "Battery charge status: battery is not being charged and the battery capacity is between low and high [33, 66] " << endl;
        break;
      case 1:
        cout << "Battery charge status: High - the battery capacity is at more than 66 percent " << endl;
        break;
      case 2:
        cout << "Battery charge status: Low - the battery capacity is at less than 33 percent " << endl;
        break;
      case 4:
        cout << "Battery charge status: Critical - the battery capacity is at less than five percent " << endl;
        break;
      case 8:
        cout << "Battery charge status: Charging " << endl;
        break;
      case 128:
        cout << "Battery charge status: No system battery " << endl;
        break;
      default:
        cout << "Battery charge status: Unknown status - unable to read the battery flag information " << endl;
        break;
    }
    cout << "The percentage of full battery charge remaining " << (int)batteryStatus.BatteryLifePercent << " %" << endl;
    switch (batteryStatus.SystemStatusFlag) {
      case 0:
        cout << "Battery saver is off. " << endl;
        break;
      case 1:
        cout << "Battery saver on. Save energy where possible. " << endl;
        break;
    }
    if (batteryStatus.BatteryLifeTime != ULONG_MAX) {
      int hours = batteryStatus.BatteryLifeTime / 60 / 60;
      int minutes = batteryStatus.BatteryLifeTime % 3600 / 60;
      cout << "Battery life: " << hours << " hours, " << minutes << " minutes" << endl;
    } else {
      cout << "Remaining seconds are unknown or the device is connected to AC power" << endl;
    }
    Sleep(1000);
    system("cls");
  }
}
