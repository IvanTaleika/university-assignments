#pragma comment (lib, "Setupapi.lib")
#include <Windows.h>
#include <SetupAPI.h>
#include <regstr.h >
#include <QString>
#include <QTextStream>

void printDeviceName(HDEVINFO& hDevInfo, SP_DEVINFO_DATA* deviceInfoData);
void printDeviceIds(HDEVINFO& hDevInfo, SP_DEVINFO_DATA* deviceInfoData);
void printVenDev(PBYTE bytes);

int main(int argc, char* argv[]) {
  HDEVINFO hDevInfo;    //handle to information set

  //returns a handle to a device information set that contains all installed devices
  if ((hDevInfo = SetupDiGetClassDevs(nullptr, REGSTR_KEY_PCIENUM, nullptr,  DIGCF_ALLCLASSES)) == INVALID_HANDLE_VALUE) {
    exit(GetLastError());
  }
  SP_DEVINFO_DATA deviceInfoData;   //defines a device instance that is a member of a device information set
  deviceInfoData.cbSize = sizeof(SP_DEVINFO_DATA);
  for (DWORD i = 0; SetupDiEnumDeviceInfo(hDevInfo, i, &deviceInfoData); i++) { //return struct DeviceInfoData
    printf("Devise %ld: ", i);
    printDeviceName(hDevInfo, &deviceInfoData);
    printDeviceIds(hDevInfo, &deviceInfoData);
    printf("\n");
  }

  SetupDiDestroyDeviceInfoList(hDevInfo);
  system("pause");
  return 0;
}

void printVenDev(PBYTE bytes) {
  QString str((QChar*)bytes);
  QTextStream cout(stdout);
  str.remove("PCI\\");
  QStringList list =  str.split('&', QString::SkipEmptyParts);
  cout << list[0] << "\t" << list[1] << endl;
}

void printDeviceName(HDEVINFO& hDevInfo, SP_DEVINFO_DATA* deviceInfoData) {
  PBYTE buffer = nullptr;
  DWORD bufferSize = 0;
  SetupDiGetDeviceRegistryProperty(hDevInfo,    //etrieves a specified Plug and Play device property
                                   deviceInfoData,
                                   SPDRP_DEVICEDESC,   //retrieves device description string
                                   nullptr,
                                   buffer,
                                   bufferSize,
                                   &bufferSize);
  buffer = new BYTE[bufferSize];
  if (!SetupDiGetDeviceRegistryProperty(hDevInfo, deviceInfoData, SPDRP_DEVICEDESC,  nullptr, buffer, bufferSize, &bufferSize)) {
    delete (buffer);
    exit(GetLastError());
  }
  printf("%ls\n", buffer);
  delete (buffer);
}

void printDeviceIds(HDEVINFO& hDevInfo, SP_DEVINFO_DATA* deviceInfoData) {
  PBYTE buffer = nullptr;
  DWORD bufferSize = 0;
  SetupDiGetDeviceRegistryProperty(hDevInfo,    //etrieves a specified Plug and Play device property
                                   deviceInfoData,
                                   SPDRP_HARDWAREID,   //retrieves device description string
                                   nullptr,
                                   buffer,
                                   bufferSize,
                                   &bufferSize);
  buffer = new BYTE[bufferSize];
  if (!SetupDiGetDeviceRegistryProperty(hDevInfo, deviceInfoData, SPDRP_HARDWAREID,  nullptr, buffer, bufferSize, &bufferSize)) {
    delete (buffer);
    exit(GetLastError());
  }
  printVenDev(buffer);
  delete (buffer);
}
