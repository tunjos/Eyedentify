


Eyedentify
==========
###### Email Identification for your Eyes only...

<img src="images/cover.jpg" alt="Cover"/>  

[![Build Status](https://app.bitrise.io/app/4aee046d11ffe00d/status.svg?token=6lBBrAkmYlnzp8Ufl3M0qg&branch=master)](https://app.bitrise.io/app/4aee046d11ffe00d)

Info
----------
The Gravatar Logo and Gravatar icons are a property of <a href="https://automattic.com/" title="Automattic">Automattic</a>.

Screenshots
----------

<img src="images/scr_main.jpg" width="250"> <img src="images/scr_search.jpg" width="250"> <img src="images/scr_result.jpg" width="250">


Architecture
----------
This app is built using the RX-MPVVM - Reactive Model Presenter View  ViewModel Pattern.  
Communication between different layers is done with the use of interfaces in order to hide internal logic.

<img src="images/android_rx_mpvvm.png" alt="RX-MPVVM"/>

APIs Used
----------
- [**Gravatar - JSON Profile Data**](https://en.gravatar.com/site/implement/profiles/json/)
- [**Gravatar - Image Requests**](https://en.gravatar.com/site/implement/images/)


Libraries used
----------
- [**JUnit5**](https://junit.org/junit5/): **The next generation of JUnit.**
- [**KOIN**](https://insert-koin.io/): **A pragmatic lightweight dependency injection framework for Kotlin.**  
- [**Retrofit**](https://square.github.io/retrofit/): **A type-safe HTTP client for Android and Java.**
- [**Timber**](https://github.com/JakeWharton/timber): **A logger with a small, extensible API which provides utility on top of Android's normal Log class.**


Building
----------

To build a debug version, run this from the root of the project:

    ./gradlew app:assembleDebug
A Debug .apk file will be created in the **app/build/outputs/apk/** folder.

Quality Control
----------

To run the unit tests:

	./gradlew app:testDebugUnitTest
Test Summary will be generated in the **app/build/reports/tests/testDebugUnitTest/index.html** file.

To run instrumentation tests:

	./gradlew app:connectedAndroidTest
Test Summary will be generated in the **app/build/reports/androidTests/connected/index.html** file.

  License
----------

    Copyright THIS YEAR Eyedentify

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
