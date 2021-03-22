1. Common Tools :


- Install Intellij community edition  - https://www.jetbrains.com/idea/download/#section=mac
- Install JDK 8 - https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Solve certificate issue - SSL Decryption Problems and Solutions
- Set JAVA_HOME and PATH - e.xsamplebash_profile
- Install node and npm - https://nodejs.org/en/download/
- Install appium - Appium Setup
- Install appium desktop - https://appium.io/downloads.html



2. iOS :


- Install HomeBrew for MAC - https://brew.sh/ 
- Create Apple ID with diconium email - https://appleid.apple.com/account
- Download XCode from AppStore
- Install XCode command line tools ( Open terminal and run xcode-select --install )
- Add the created Apple ID in XCode ( XCode - Preferences - Account - Add Apple ID )
- Install Carthage - https://github.com/Carthage/Carthage#installing-carthage ( brew install Carthage )
- Open WebDriverAgent.xcodeproj in Xcode
- For WebDriverAgentLib and WebDriverAgentRunner targets, go to general tab and select "Automatically manage signing", and then select your Development Team


3. Android:


- Setup Android Emulator
- Set ANDROID_HOME, PATH, ANDROID_SDK_ROOT, ANDROID_AVD_HOME path on the bash_profile



e.x sample bash_profile
- export M2_HOME=/Users/karupsek/apache-maven-3.6.3
- export PATH=$PATH:$M2_HOME/bin
- export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_281.jdk/Contents/Home
- export PATH=$JAVA_HOME/bin:$PATH
- export ANDROID_HOME=/Users/karupsek/Library/Android/sdk
- export PATH=/usr/local/Caskroom/android-sdk/4333796$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools:$PATH
- export ANDROID_SDK_ROOT=~/Library/Android/sdk
- export ANDROID_AVD_HOME=~/.android/avd
- export PATH=~/Documents/flutter/bin:$PATH
- export PATH="$PATH":"$HOME/Documents/flutter/.pub-cache/bin"+



4. Project Setup:

- Clone the project https://git.diconium.com/diconium/testmgmt/appium-template

