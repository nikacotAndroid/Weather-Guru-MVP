# Weather Guru MVP

Sample Android weather application that allows you to select а city and browse forecast for the next 5 days.
The application is built with MVP architectural approach and uses [World Weather Online API](https://www.worldweatheronline.com/) for fetching data.

<p align="center">
  <img src="https://github.com/nikacotAndroid/Weather-Guru-MVP/blob/master/art/1.png" width="280">
  <img src="https://github.com/nikacotAndroid/Weather-Guru-MVP/blob/master/art/2.png" width="280">
  <img src="https://github.com/nikacotAndroid/Weather-Guru-MVP/blob/master/art/3.png" width="280">
</p>
<p align="center">
  <img src="https://github.com/nikacotAndroid/Weather-Guru-MVP/blob/master/art/5.png" width="280">
  <img src="https://github.com/nikacotAndroid/Weather-Guru-MVP/blob/master/art/6.png" width="280">
</p>


## Project Structure
### data 
It contains all the data(Shared Preferences, Local DB and Remote API) accessing and manipulating components
### injection
Dependency providing classes using Dagger2
### events
EventBus events classes that simplifies communication between Activitie and Fragments
### ui
View classes along with their corresponding Presenters
### utils
Utility classes


## Code Style
This sample project uses the IntelliJ IDEA [code style settings for Square's Java and Android projects](https://github.com/square/java-code-styles).

## Library reference resources
- Support library
- [RxJava2](https://github.com/ReactiveX/RxJava), [RxAndroid](https://github.com/ReactiveX/RxAndroid) and [RxBindings](https://github.com/JakeWharton/RxBinding)
- [Dagger2](https://google.github.io/dagger/)
- [Retrofit2](http://square.github.io/retrofit/)
- [GreenDao](http://greenrobot.org/greendao/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Greenrobot EventBus](http://greenrobot.org/eventbus/)
- [Lottie-Android](https://github.com/airbnb/lottie-android)
- [Timber](https://github.com/JakeWharton/timber)

# Contributing
Make pull request and you are IN!

# License
```
The MIT License (MIT)

Copyright (c) 2017 Nikola Petrovski <nikola.petrovski6@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
