
# WeatherApp

The purpose of this document is to provide developers the overview of the Android project.


## Systems Requirement

- Support from **Android 6.0** and later 
- Target **Android 11**


## SOLID Principles
- *Single responsibility principle: using data class for **Response**, **Entity**,**UI Model** that has only one responsibility.
- Open / closed principle: **WeatherSearchItem** is base class for items used in Weather search screen that correspond to view holder.
- Interface segregation principle: like **RoomTransactionExecutor** interface 
- Dependency inversion principle: dependency injection by using **Dagger**
 
## App Architecture

![alt text](https://i.ibb.co/qrckp2N/Screenshot-2021-07-17-at-8-00-24-PM.png)

- Activity - **MainActivity** is search screen. 
- ViewModel - **MainViewModel** contains the Flow, LiveData… for activity
- Repository - **Repository** takes charge of getting data for ViewModel, the data will be fetched from API then stored in the database, ViewModel will listener to database’s data and update UI whenever data changed.

## Database structure

![alt text](https://i.ibb.co/hfs8K15/216879453-328171228977845-8232015354134060022-n.png)

- Using **Room** to cache response to support caching mechanism so as to prevent the app from generating a bunch of API requests.
- Database will be clear every 10 minutes.

## Code folder structure 
### Project Structure
![alt text](https://i.ibb.co/wyfSstr/Screenshot-2021-07-18-at-2-11-02-AM.png)
- **content** - contains data model includes in **response**, **rntity** and **UI model**, **repository** for getting data from server
- **di** - for **dagger** implementation
- **manager** - contains **AppManager** is used to save some information using **SharedPreference** 
- **ui** - includes in all child packages that corresponds to each screen. In this assignment, we have just one Search screen so I don't create child package **MainActivity** and **MainViewModel**.
- **utils** - contains **date** abd **dialog** helper class and **constant field**

### Unit Test
![alt text](https://i.ibb.co/Rz3PHsj/Screenshot-2021-07-18-at-1-55-37-AM.png)
- **test_util** folder contains helper class or extension function to running unit test.

## Dependencies 
- gradle_plugin (4.2.1)
- kotlin_version (1.5.0)
- dagger (2.3): For dependencies injection
- retrofit (2.9.0): For calling APIs
- coroutine (1.5.1): For using lightweight thread
- room (2.3.0): For store data
- timber (4.7.1): For logging 
- safetynet (17.0.1): For checking rooted device
- rootbeer (0.1.0): For checking rooted device

## Requirement steps to run application 
- Using **Android Studio 4.2.0**
- Choosing **devRelease** Build Variant to check rooted device feature
- For unit test, check **MainViewModelTest** 

## Checklist of requirements has done. 
1. Using Kotlin 
2. Implementing MVVM 
3. Apply Live Data mechanism
4. UI looks like in attachment
5. Write UnitTests - applied for **MainViewModel**
6. Acceptance Tests
7. Exception handing - show **Dialog** when call API fail
8. Cache handling - using **Room** to cache
9. Secure Android app from 
* Decompile APK - using **proguardFiles** 
* Rooted device - using **SafetyNet** and **Rootbeer**
10. Accessibility for Disability Supports - WeatherApp supported for **talkback** and **scalling text** automatically
11. Entity relationship diagram, app architecture diagram. 
12. Readme file includes
* Software development principles, patterns & practices being
applied
* Code folder structure and key Kotlin libraries and framework being used
* Required steps to run application on local computer
* Checklist has done 

